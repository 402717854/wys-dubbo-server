package com.wys.dubbo.dao;

import com.wys.dubbo.common.mybatis.IMBaseDAO;
import com.wys.dubbo.model.OperationRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: dao层 Interface:OperationRolePermission
 * @Author: boris
 * @Date: 2020/12/08
 */
public interface OperationRolePermissionMapper extends IMBaseDAO<OperationRolePermission, Integer> {

    /**
     * 根据菜单Id查询全部角色id
     */
    List<Integer> selectRoleIdSByMenuList(@Param("list") List<Integer> menuList);

    /**
     * 根据角色id查询菜单Id
     */
    List<Integer> selectMenuIdSByRoleIdList(@Param("list") List<Integer> roleIdList);

    /**
     * 根据角色Id删除所有关联菜单
     */
    int deletePermissionByRoleId(Integer roleId);

    /**
     * 根据角色id查询可用菜单权限代码
     */
    List<Integer> selectValidMenuIdByRoleId(@Param("roleId") Integer roleId);
}