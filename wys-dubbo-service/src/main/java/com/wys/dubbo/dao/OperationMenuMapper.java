package com.wys.dubbo.dao;

import com.wys.dubbo.common.mybatis.IMBaseDAO;
import com.wys.dubbo.dto.OperationMenuDto;
import com.wys.dubbo.dto.OperationMenuTreeDto;
import com.wys.dubbo.dto.request.OperationMenuPageReq;
import com.wys.dubbo.dto.response.OperationMenuPageRes;
import com.wys.dubbo.model.OperationMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: dao层 Interface:OperationMenu
 * @Author: boris
 * @Date: 2020/12/08
 */
public interface OperationMenuMapper extends IMBaseDAO<OperationMenu, Integer> {

    /**
     * 根据菜单权限查询菜单Id
     */
    List<Integer> selectMenuListByPermissionDetailS(@Param("list") List<String> PermissionDetailList);

    /**
     * 查询全部菜单并根据父节点排序
     */
    List<OperationMenu> selectMenuAll();

    /**
     * 查询全部菜单并根据父节点排序
     */
    List<OperationMenuDto> selectValidMenuVOAll();


    /**
     * 查询用户所有菜单
     */
    List<OperationMenu> selectMenuAllByUserId(@Param("userId") Integer userId);

    /**
     * 根据用户Id查询所有权限
     */
    List<String> selectPermsByUserId(@Param("userId") Integer userId);

    /**
     * 根据用户Id查询菜单Id
     */
    List<Integer> selectMenuByUserId(@Param("userId") Integer userId);

    /**
     * 查询全部菜单树
     */
    List<OperationMenuTreeDto> selectValidMenuTreeAll();

    /**
     * 根据角色Id查询拥有的菜单Id
     */
    List<Integer> selectMenuByRoleId(@Param("roleId") Integer roleId);

    /**
     * 分页查询角色信息
     */
    List<OperationMenuDto> selectMenuPage(@Param("menuReq") OperationMenuPageReq request);

    /**
     * 查询最大id和总条数
     */
    OperationMenuPageRes queryCountMaxMenu(@Param("menuReq") OperationMenuPageReq request);
}