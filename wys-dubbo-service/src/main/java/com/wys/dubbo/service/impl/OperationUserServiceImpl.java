package com.wys.dubbo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wys.dubbo.common.constant.RedisConstant;
import com.wys.dubbo.common.exception.DubboBusinessException;
import com.wys.dubbo.common.exception.DubboExceptionEnum;
import com.wys.dubbo.common.mybatis.BaseServiceImpl;
import com.wys.dubbo.dao.OperationUserMapper;
import com.wys.dubbo.dto.OperationSysUserDto;
import com.wys.dubbo.dto.request.OperationAssignRoleReq;
import com.wys.dubbo.dto.request.OperationSysUserPageReq;
import com.wys.dubbo.dto.request.OperationUserReq;
import com.wys.dubbo.dto.response.OperationSysUserPageRes;
import com.wys.dubbo.model.OperationRole;
import com.wys.dubbo.model.OperationUser;
import com.wys.dubbo.model.OperationUserRole;
import com.wys.dubbo.service.OperationRoleService;
import com.wys.dubbo.service.OperationUserRoleService;
import com.wys.dubbo.service.OperationUserService;
import com.wys.dubbo.util.CheckUtil;
import com.wys.dubbo.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * @Description: Implementation:OperationUser
 * @Author: boris
 * @Date: 2020/12/08
 */
@Service
public class OperationUserServiceImpl extends BaseServiceImpl<OperationUser, Integer, OperationUserMapper> implements OperationUserService {

    @Autowired
    OperationUserRoleService operationUserRoleService;

    @Autowired
    OperationRoleService roleService;

    /**
     * @return ExecuteResult
     * @Description 添加运营端账户
     * @Param request
     **/
    @Override
    public Boolean addOrUpdateOperationUser(OperationUserReq request){
        int count = 0;
        Date curDate = new Date();

        OperationUser operationUser = new OperationUser();
        BeanUtil.copyProperties(request, operationUser);
        operationUser.setUpdateUser(request.getUserId());
        operationUser.setUpdateTime(curDate);
        //根据账号判断是否存在
        OperationUser sysUser = mapper.queryOperationUserByAccount(request.getAccount());
        if (request.getId() != null && request.getId() > 0) {
            //判断是否已存在
            if (sysUser != null && sysUser.getId() != request.getId()) {
                throw new DubboBusinessException(DubboExceptionEnum.OPERATION_USER_EXISTS);
            }

            //判断主键Id是否存在
            if (mapper.selectPk(request.getId()) == null) {
                throw new DubboBusinessException(DubboExceptionEnum.SYS_USER_NOT_EXIST);
            }
            count = mapper.update(operationUser);
        } else {
            if (sysUser != null) {
                throw new DubboBusinessException(DubboExceptionEnum.OPERATION_USER_EXISTS);
            }

            //设置创建人
            operationUser.setCreateUser(request.getUserId());
            operationUser.setCreateTime(curDate);

            //不存在则进行inset.
            count = mapper.insert(operationUser);
        }


        return count > 0 ? true:false;
    }

    /**
     * @return ExecuteResult<LoginTokenVo>
     * @Description 登录
     * @Param access
     **/
    @Override
    public OperationUser checkLogin(String access) {
        //判断账号是否存在于当前系统中
        Map<String, Object> param = new HashMap<>(10);
        if (CheckUtil.isPhone(access)) {
            param.put("mobile", access);
        } else {
            param.put("account", access);
        }
        OperationUser operationUser = mapper.selectOne(param);
        if (operationUser == null) {
            throw new DubboBusinessException(DubboExceptionEnum.USER_NOT_EXISTS);
        }

        //状态不是有效则都返回被禁用
        if (operationUser.getState() != 0) {
            throw new DubboBusinessException(DubboExceptionEnum.FORBIDEN_USER);

        }
        return operationUser;
    }

    /**
     * @return List<Integer>
     * @Description 获取用户所有角色
     * @Param userId
     **/
    @Override
    public List<Integer> queryUserRoleId(Integer userId){
        OperationUser operationUser = mapper.selectPk(userId);

        List<Integer> roleIdList = operationUserRoleService.queryUserAllRoleByUserId(operationUser.getId());

        return roleIdList;
    }

    /**
     * @return OperationSysUserPageRes
     * @Description 获取管理员分页列表
     * @Param request
     **/
    @Override
    public OperationSysUserPageRes querySysUserPage(OperationSysUserPageReq request) {

        //获取总条数和最大Id数
        OperationSysUserPageRes sysUserPageRes = mapper.queryCountMax(request);

        //判断是否超过最大页超过则直接返回
        if (request.getPageSize() > (sysUserPageRes.getTotal() / request.getLimit())) {
            return sysUserPageRes;
        }

        //获取列表数据
        List<OperationSysUserDto> sysUserVOList = mapper.selectSysUserPage(request);
        sysUserPageRes.setSysUserVOList(sysUserVOList);

        return sysUserPageRes;
    }

    /**
     * @return ExecuteResult<String>
     * @Description 批量添加用户角色
     * @Param request
     **/
    @Transactional
    @Override
    public Boolean assignRoleBatch(OperationAssignRoleReq request){
        //批量插入的数据
        List<OperationUserRole> userRolesList = new ArrayList<>();
        //用户Id
        List<Integer> sysUserIdList = request.getSysUserIdList();

        for (Integer susUserId : sysUserIdList) {
            //判断用户是否存在
            OperationUser operationUser =
                    (OperationUser) RedisUtils.queryAndSetSync(RedisConstant.OPERATION_USER_INIT, mapper, susUserId,
                            new OperationUser());
            if (operationUser == null || operationUser.getId() != susUserId || operationUser.getState() != 0) {
                throw new DubboBusinessException(DubboExceptionEnum.SYS_USER_DISABLE, susUserId);
            }

        }


        //角色信息
        List<Integer> roleIdList = request.getRoleIdList();
        for (Integer roleId : roleIdList) {
            //判断角色Id是否存在使用Redis缓存
            OperationRole operationRole = roleService.queryRoleVOCache(roleId);
            if (operationRole == null || operationRole.getId() != roleId || operationRole.getState() != 0) {
                throw new DubboBusinessException(DubboExceptionEnum.ROLE_DISABLE, roleId);
            }

            for (Integer userId : sysUserIdList) {
                OperationUserRole operationUserRole = new OperationUserRole();
                operationUserRole.setOperationRoleId(roleId);
                operationUserRole.setOperationUserId(userId);
                userRolesList.add(operationUserRole);
            }


        }

        return operationUserRoleService.saveUserRolesList(userRolesList);
    }

    /**
     * @return OperationUser
     * @Description 获取运营端用户信息(附带缓存)
     * @Param userId
     **/
    @Override
    public OperationUser querySysUserCacheByUserId(Integer userId){
        OperationUser operationUser = (OperationUser) RedisUtils.queryAndSetSync(RedisConstant.OPERATION_USER_INIT,
                mapper, userId, new OperationUser());

        return operationUser;
    }


}
