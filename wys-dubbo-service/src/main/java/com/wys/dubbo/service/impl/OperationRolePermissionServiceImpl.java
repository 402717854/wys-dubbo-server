package com.wys.dubbo.service.impl;

import com.wys.dubbo.common.constant.RedisConstant;
import com.wys.dubbo.common.mybatis.BaseServiceImpl;
import com.wys.dubbo.dao.OperationRolePermissionMapper;
import com.wys.dubbo.model.OperationRolePermission;
import com.wys.dubbo.service.OperationRolePermissionService;
import com.wys.dubbo.util.RedisUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description: Implementation:OperationRolePermission
 * @Author: boris
 * @Date: 2020/12/08
 */
@Service
public class OperationRolePermissionServiceImpl extends BaseServiceImpl<OperationRolePermission, Integer,
        OperationRolePermissionMapper> implements OperationRolePermissionService {


    /**
     * @return List<Integer>
     * @Description 根据菜单Id获取所有拥有该菜单的角色
     * @Param menuIdList
     **/
    @Override
    public List<Integer> queryRoleIdByMenuIdS(List<Integer> menuIdList) {


        return mapper.selectRoleIdSByMenuList(menuIdList);
    }

    /**
     * @return List<Integer>
     * @Description 根据角色Id查找全部菜单Id
     * @Param roleIdList
     **/
    @Override
    public List<Integer> queryMenuIdByRoleIdS(List<Integer> roleIdList) {
        return mapper.selectMenuIdSByRoleIdList(roleIdList);
    }

    /**
     * @param menuIdList
     * @return ExecuteResult<String>
     * @Description 添加或修改角色的菜单
     * @Param roleId
     **/
    @Transactional
    @Override
    public Boolean addOrEnidRoleMenu(Integer roleId, List<Integer> menuIdList) {

        //查看菜单id是否存在(对列表进行过滤出可用的)后台暂不考虑

        //根据角色ID删除之前的对应关系
        mapper.deletePermissionByRoleId(roleId);
        //批量添加
        insertRoleMenu(roleId, menuIdList);

        //删除缓存对应关系
        RedisUtils.remove(RedisConstant.OPERATION_ROLE_VALID_MENU + roleId);
        return true;
    }

    /**
     * @return List<String>
     * @Description 根据角色Id获取菜单id
     * @Param roleId
     **/
    @Override
    public List<Integer> queryMenuIdByRoleId(Integer roleId) {
        List<Integer> validMenu = (List<Integer>) RedisUtils.get(RedisConstant.OPERATION_ROLE_VALID_MENU + roleId);
        if (validMenu == null) {
            validMenu = mapper.selectValidMenuIdByRoleId(roleId);
            RedisUtils.set(RedisConstant.OPERATION_ROLE_VALID_MENU + roleId, validMenu);
        }

        return validMenu;
    }

    /**
     *
     * @Description 重置用户角色信息
     * @Param userId
     * @return void
     **/
    @Override
    public void resetUserRole(Integer userId) {

        //删除已有角色信息
        RedisUtils.remove(RedisConstant.OPERATION_USER_VALID_ROLE+userId);
        //重新设置
        queryMenuIdByRoleId(userId);

    }

    /**
     * @param menuIdList
     * @return int
     * @Description 新增角色菜单信息
     * @Param roleId
     **/
    public int insertRoleMenu(Integer roleId, List<Integer> menuIdList) {
        int rows = 1;
        // 新增用户与角色管理
        List<OperationRolePermission> list = new ArrayList<OperationRolePermission>();

        for (Integer menuId : menuIdList) {
            OperationRolePermission rm = new OperationRolePermission();
            rm.setOperationRoleId(roleId);
            rm.setOperationMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0) {
            rows = mapper.insertBatch(list);
        }
        return rows;
    }
}
