package com.wys.dubbo.service;

import com.wys.dubbo.common.mybatis.BaseService;
import com.wys.dubbo.model.OperationUserRole;

import java.util.List;

/**
 * <p>
 * 管理员与角色对应关系 服务类
 * </p>
 * @author lizy
 * @since 2020-12-14
 */
public interface OperationUserRoleService extends BaseService<OperationUserRole, Integer> {

    /**
     * 查询用户所有角色
     */
    List<Integer> queryUserAllRoleByUserId(Integer userId);

    /**
     * 查询用户可用角色
     */
    List<Integer> queryUserValidRoleByUserId(Integer userId);

    /**
     * 为多个用户分配角色
     */
    Boolean saveUserRolesList(List<OperationUserRole> userRolesList);


}
