package com.wys.dubbo.service.impl;


import com.wys.dubbo.common.constant.RedisConstant;
import com.wys.dubbo.common.mybatis.BaseServiceImpl;
import com.wys.dubbo.dao.OperationUserRoleMapper;
import com.wys.dubbo.model.OperationUserRole;
import com.wys.dubbo.service.OperationUserRoleService;
import com.wys.dubbo.util.RedisUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 管理员与角色对应关系 服务实现类
 * </p>
 * @author lizy
 * @since 2020-12-14
 */
@Service
public class OperationUserRoleServiceImpl extends BaseServiceImpl<OperationUserRole, Integer,
        OperationUserRoleMapper> implements OperationUserRoleService {

    /**
     * @return List<Integer>
     * @Description 根据用户Id获取其拥有的角色信息
     * @Param userId
     **/
    @Override
    public List<Integer> queryUserAllRoleByUserId(Integer userId) {

        return mapper.selectUserRoleByUserId(userId);
    }

    /**
     * @return List<Integer>
     * @Description 根据用户Id获取其拥有的角色信息(可用的)
     * @Param userId
     **/
    @Override
    public List<Integer> queryUserValidRoleByUserId(Integer userId) {

        List<Integer> userValidRoleList =
                (List<Integer>) RedisUtils.get(RedisConstant.OPERATION_USER_VALID_ROLE + userId);

        if (userValidRoleList == null) {
            userValidRoleList = mapper.selectUserValidRoleByUserId(userId);
            RedisUtils.set(RedisConstant.OPERATION_USER_VALID_ROLE + userId, userValidRoleList);
        }

        return userValidRoleList;
    }

    /**
     * @return ExecuteResult<String>
     * @Description 为多个用户分配角色
     * @Param userRolesList
     **/
    @Transactional
    @Override
    public Boolean saveUserRolesList(List<OperationUserRole> userRolesList) {
        //批量删除
        List<Integer> userIdList =
                userRolesList.stream().filter(userRoles -> userRoles != null).map(OperationUserRole::getOperationUserId).distinct().collect(Collectors.toList());
        mapper.deleteBatchByUserId(userIdList);

        //批量插入
        mapper.insertBatch(userRolesList);
        //删除缓存
        userIdList.forEach(userId -> {
            RedisUtils.remove(RedisConstant.OPERATION_USER_VALID_ROLE + userId);
        });
        return true;
    }


}
