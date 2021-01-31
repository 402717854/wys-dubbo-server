package com.wys.dubbo.service;


import com.wys.dubbo.common.mybatis.BaseService;
import com.wys.dubbo.model.OperationRolePermission;

import java.util.List;

/**
 * @Description: Interface:OperationRolePermission
 * @Author: boris
 * @Date: 2020/12/08
 */
public interface OperationRolePermissionService extends BaseService<OperationRolePermission, Integer> {
    /**
     * 根据菜单Id查询所有角色
     */
    List<Integer> queryRoleIdByMenuIdS(List<Integer> menuIdList);

    /**
     * 根据角色Id列表获取角色所有菜单Id
     */
    List<Integer> queryMenuIdByRoleIdS(List<Integer> roleIdList);

    /**
     * 添加或修改角色所拥有的菜单id
     */
    Boolean addOrEnidRoleMenu(Integer roleId, List<Integer> menuIdList);

    /**
     * 根据角色Id列表获取角色所有菜单Id
     */
    List<Integer> queryMenuIdByRoleId(Integer roleId);

    /**重置用户角色信息*/
    void resetUserRole(Integer userId);
}