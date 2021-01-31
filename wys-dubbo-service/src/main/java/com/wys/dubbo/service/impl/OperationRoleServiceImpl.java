package com.wys.dubbo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wys.dubbo.common.constant.RedisConstant;
import com.wys.dubbo.common.exception.DubboBusinessException;
import com.wys.dubbo.common.exception.DubboExceptionEnum;
import com.wys.dubbo.common.mybatis.BaseServiceImpl;
import com.wys.dubbo.dao.OperationRoleMapper;
import com.wys.dubbo.dto.OperationMenuTreeDto;
import com.wys.dubbo.dto.OperationRoleDto;
import com.wys.dubbo.dto.OperationRoleEasyCheckedDto;
import com.wys.dubbo.dto.request.OperationRolePageReq;
import com.wys.dubbo.dto.request.OperationRoleReq;
import com.wys.dubbo.dto.response.OperationRoleDetailsRes;
import com.wys.dubbo.dto.response.OperationRoleEasyRes;
import com.wys.dubbo.dto.response.OperationRolePageRes;
import com.wys.dubbo.model.OperationRole;
import com.wys.dubbo.service.OperationMenuService;
import com.wys.dubbo.service.OperationRolePermissionService;
import com.wys.dubbo.service.OperationRoleService;
import com.wys.dubbo.service.OperationUserRoleService;
import com.wys.dubbo.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @Description: Implementation:OperationRole
 * @Author: boris
 * @Date: 2020/12/08
 */
@Service
public class OperationRoleServiceImpl extends BaseServiceImpl<OperationRole, Integer, OperationRoleMapper> implements OperationRoleService {

    @Autowired
    OperationMenuService operationMenuService;
    @Autowired
    OperationRolePermissionService operationRolePermissionService;
    @Autowired
    OperationUserRoleService roleService;

    /**
     * @return ExecuteResult<String>
     * @Description 添加或修改角色
     * @Param request
     **/
    @Transactional
    @Override
    public Boolean addOrEditRole(OperationRoleReq request) {
        OperationRole operationRole = new OperationRole();
        BeanUtil.copyProperties(request, operationRole);
        Integer roleId = request.getId();
        Date curDate = new Date();
        operationRole.setUpdateUser(request.getUserId());
        operationRole.setUpdateTime(curDate);

        //判断id是否存在,
        if (request.getId() != null && request.getId() > 0) {
            //根据id获取判断是否存在
            operationRole = mapper.selectPk(request.getId());
            if (operationRole == null || !operationRole.getId().equals(request.getId())) {
                throw new DubboBusinessException(DubboExceptionEnum.ROLE_NOT_EXIST);
            }
            this.update(operationRole);
        } else {
            request.setId(null);
            operationRole.setCreateUser(request.getUserId());
            operationRole.setCreateTime(curDate);
            roleId = this.add(operationRole);

        }

        //修改角色对应关系
        return operationRolePermissionService.addOrEnidRoleMenu(roleId, request.getMenuList());
    }

    /**
     * @return OperationRolePageRes
     * @Description 查询角色分页数据
     * @Param request
     **/
    @Override
    public OperationRolePageRes queryRolePage(OperationRolePageReq request) {

        OperationRolePageRes rolePageRes = new OperationRolePageRes();
        rolePageRes = mapper.selectCountMaxRole(request);

        //判断是否超过最大页超过则直接返回
        if (request.getPageSize() > (rolePageRes.getTotal() / request.getLimit())) {
            return rolePageRes;
        }

        List<OperationRoleDto> roleVOList = mapper.selectRolePage(request);
        rolePageRes.setRoleVOList(roleVOList);

        return rolePageRes;
    }


    /**
     * @return OperationRoleDetailsRes
     * @Description 查看角色详情
     * @Param request
     **/
    @Override
    public OperationRoleDetailsRes queryRoleDetails(Integer roleId) {
        OperationRoleDetailsRes roleDetailsRes = new OperationRoleDetailsRes();
        OperationRole operationRole = mapper.selectPk(roleId);
        BeanUtil.copyProperties(operationRole, roleDetailsRes);
        //获取角色菜单树
        List<OperationMenuTreeDto> operationMenuTreeVOList =
                operationMenuService.queryValidMenuListByRoleId(roleId);
        roleDetailsRes.setRoleMenuList(operationMenuTreeVOList);

        return roleDetailsRes;
    }

    /**
     * @return List<OperationRoleVO>
     * @Description 获取用户角色详情列表
     * @Param userId
     **/
    @Override
    public List<OperationRoleDto> queryUserRoleVOByUserId(Integer userId) {

        //TODO 是否使用redis缓存(数据量起来后可能需要)
        List<OperationRoleDto> operationRoleVOList = mapper.selectUserRoleVOByUserId(userId);

        return operationRoleVOList;
    }

    @Override
    public OperationRoleEasyRes queryRoleEasyAllList(Integer userId) {
        OperationRoleEasyRes roleEasyRes = new OperationRoleEasyRes();
        //获取全部
        List<OperationRoleEasyCheckedDto> roleList = mapper.selectRoleEasyAllVO();
        //获取用户拥有的角色Id
        List<Integer> userRoleIdList = roleService.queryUserAllRoleByUserId(userId);
        Set<Integer> userRoleIdSet = new HashSet<>();
        for (Integer roleId : userRoleIdList) {
            if (roleId > 0) {
                userRoleIdSet.add(roleId);
            }
        }
        //判断
        roleList.stream().filter(role -> userRoleIdSet.contains(role.getId())).forEach(role -> {
            role.setChecked(Boolean.TRUE);
        });
        roleEasyRes.setRoleEasyVOList(roleList);
        return roleEasyRes;
    }

    /**
     * @return OperationRole
     * @Description 根据角色Id获取角色信息(缓存)
     * @Param roleId
     **/
    @Override
    public OperationRole queryRoleVOCache(Integer roleId) {
        OperationRole roleInit = (OperationRole) RedisUtils.queryAndSetSync(RedisConstant.OPERATION_ROLE_INIT, mapper
                , roleId, new OperationRole());
        return roleInit;
    }
}
