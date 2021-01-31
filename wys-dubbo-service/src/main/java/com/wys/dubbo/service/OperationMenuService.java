package com.wys.dubbo.service;

import com.wys.dubbo.common.mybatis.BaseService;
import com.wys.dubbo.dto.OperationMenuDto;
import com.wys.dubbo.dto.OperationMenuTreeDto;
import com.wys.dubbo.dto.request.OperationMenuPageReq;
import com.wys.dubbo.dto.request.OperationMenuReq;
import com.wys.dubbo.dto.response.OperationMenuPageRes;
import com.wys.dubbo.model.OperationMenu;

import java.util.List;
import java.util.Set;

/**
 * @Description: Interface:OperationMenu
 * @Author: boris
 * @Date: 2020/12/08
 */
public interface OperationMenuService extends BaseService<OperationMenu, Integer> {

    /**
     * @return List<Integer>
     * @Description 根据菜单权限获取对应的菜单id并找到指定的角色
     * @Param permissionList
     **/
    List<Integer> queryRoleIdS(List<String> permissionList);


    /**
     * 添加或修改菜单
     */
    Boolean addOrEditMenu(OperationMenuReq request);

    /**
     * 获取用户所有权限
     */
    Set<String> queryPermsMenuByUserId(Integer userId);

    /**
     * 获取全部可用菜单
     */
    List<OperationMenuDto> queryValidMenuAll();

    /**
     * 获取全部可用菜单并勾选上当前用户的
     */
    List<OperationMenuTreeDto> queryValidMenuListByUser(Integer userId);

    /**
     * 获取全部可用菜单并勾选上当前角色的
     */
    List<OperationMenuTreeDto> queryValidMenuListByRoleId(Integer roleId);

    /**
     * 获取全部可用菜单
     */
    List<OperationMenuTreeDto> queryValidMenuTree();


    /**
     * 获取菜单详情
     */
    OperationMenuDto queryMenuDetails(Integer menuId);

    /**
     * 获取菜单列表
     */
    OperationMenuPageRes queryMenuPage(OperationMenuPageReq request);

}