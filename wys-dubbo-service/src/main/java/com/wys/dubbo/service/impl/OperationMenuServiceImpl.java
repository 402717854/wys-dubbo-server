package com.wys.dubbo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wys.dubbo.common.constant.BusinessOpportunityConstant;
import com.wys.dubbo.common.constant.RedisConstant;
import com.wys.dubbo.common.exception.DubboBusinessException;
import com.wys.dubbo.common.exception.DubboExceptionEnum;
import com.wys.dubbo.common.mybatis.BaseServiceImpl;
import com.wys.dubbo.dao.OperationMenuMapper;
import com.wys.dubbo.dto.OperationMenuDto;
import com.wys.dubbo.dto.OperationMenuTreeDto;
import com.wys.dubbo.dto.request.OperationMenuPageReq;
import com.wys.dubbo.dto.request.OperationMenuReq;
import com.wys.dubbo.dto.response.OperationMenuPageRes;
import com.wys.dubbo.model.OperationMenu;
import com.wys.dubbo.service.OperationMenuService;
import com.wys.dubbo.service.OperationRolePermissionService;
import com.wys.dubbo.service.OperationUserRoleService;
import com.wys.dubbo.util.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @Description: Implementation:OperationMenu
 * @Author: boris
 * @Date: 2020/12/08
 */
@Service
public class OperationMenuServiceImpl extends BaseServiceImpl<OperationMenu, Integer, OperationMenuMapper> implements OperationMenuService {

    @Autowired
    OperationRolePermissionService operationRolePermissionService;

    @Autowired
    OperationUserRoleService userRoleService;

    /**
     * @return List<Integer>
     * @Description 根据菜单权限获取对应的菜单id并找到指定的角色
     * @Param permissionList
     **/
    @Override
    public List<Integer> queryRoleIdS(List<String> permissionList) {
        //根据菜单权限码反查菜单Id

        List<Integer> menuList = mapper.selectMenuListByPermissionDetailS(permissionList);
        if (menuList == null || menuList.size() < 0) {
            return null;
        }
        //根据菜单Id查询角色Id
        List<Integer> roleIdList = operationRolePermissionService.queryRoleIdByMenuIdS(menuList);

        return roleIdList;
    }

    /**
     * @return ExecuteResult<String>
     * @Description 添加/修改菜单
     * @Param request
     **/
    @Override
    public Boolean addOrEditMenu(OperationMenuReq request) {
        //父菜单如果不为空时需校验是否禁用如果禁用需要提示父菜单已禁用不可添加

        //校验菜单权限是否已存在

        if (Objects.isNull(request.getParentId())) {
            request.setParentId(BusinessOpportunityConstant.ZERO);
        } else {
            //校验父级菜单是否可用
            OperationMenu operationMenuByParent = mapper.selectPk(request.getParentId());
            if (operationMenuByParent == null || operationMenuByParent.getState() == BusinessOpportunityConstant.ONE) {
                throw new DubboBusinessException(DubboExceptionEnum.PARENT_MENU_ERROR);
            }

        }

        Date curDate = new Date();
        if (StringUtils.isNotEmpty(request.getPermissionDetail()) && !request.getPermissionDetail().startsWith(
                "operate:")) {
            throw new DubboBusinessException(DubboExceptionEnum.PERMISSION_DETAIL_FORMAT_ERROR);
        }

        OperationMenu operationMenu = new OperationMenu();
        BeanUtil.copyProperties(request, operationMenu);

        operationMenu.setUpdateUser(request.getUserId());
        operationMenu.setUpdateTime(curDate);

        int count = 0;
        if (Objects.isNull(operationMenu.getId())) {
            operationMenu.setCreateTime(curDate);
            operationMenu.setCreateUser(request.getUserId());
            count = this.add(operationMenu);
        } else {
            //删除对应菜单信息缓存
            RedisUtils.remove(RedisConstant.OPERATION_MENU_INIT + operationMenu.getId());

            count = this.update(operationMenu);
        }


        return count == 0 ? false:true;
    }

    /**
     * @return Set<String>
     * @Description 根据用户Id获取拥有的菜单
     * @Param userId
     **/
    @Override
    public Set<String> queryPermsMenuByUserId(Integer userId) {
        //根据userId获取全部角色Id
        List<Integer> userValidRoleList = userRoleService.queryUserValidRoleByUserId(userId);

        if (userValidRoleList == null || userValidRoleList.size() <= 0) {
            return null;
        }
        List<Integer> menuIdList = new ArrayList<>();
        List<String> validPermsDetailList = new ArrayList<>();
        //根据角色Id获取全部菜单Id
        for (Integer roleId : userValidRoleList) {
            menuIdList.addAll(operationRolePermissionService.queryMenuIdByRoleId(roleId));

        }
        if (menuIdList == null || menuIdList.size() <= 0) {
            return null;
        }
        for (Integer menuId : menuIdList) {
            OperationMenu operationMenu =
                    (OperationMenu) RedisUtils.queryAndSetSync(RedisConstant.OPERATION_MENU_INIT, mapper, menuId,
                            new OperationMenu());
            if (operationMenu != null && operationMenu.getState() == BusinessOpportunityConstant.ZERO) {
                validPermsDetailList.add(operationMenu.getPermissionDetail());
            }
        }


        if (validPermsDetailList == null || validPermsDetailList.size() <= 0) {
            return null;
        }
        Set<String> permsSet = new HashSet<>();
        for (String perm : validPermsDetailList) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.add(perm);
            }
        }

        return permsSet;
    }


    /**
     * @return List<OperationMenuVO>
     * @Description 获取有效的全部菜单
     * @Param
     **/
    @Override
    public List<OperationMenuDto> queryValidMenuAll() {

        List<OperationMenuDto> operationMenuDtoList = mapper.selectValidMenuVOAll();

        return operationMenuDtoList;
    }


    /**
     * @return List<OperationMenuTreeVO>
     * @Description 获取菜单树且勾选上用户已有的
     * @Param userId 用户Id
     **/
    @Override
    public List<OperationMenuTreeDto> queryValidMenuListByUser(Integer userId) {
        //获取全部可用菜单树
        List<OperationMenuTreeDto> operationMenuTreeVOList = mapper.selectValidMenuTreeAll();
        //获取用户所拥有的可用菜单Id
        List<Integer> MenuIdList = mapper.selectMenuByUserId(userId);
        Set<Integer> permsSet = new HashSet<>(MenuIdList);
        //0为根目录
        return buildMenuTree(operationMenuTreeVOList, BusinessOpportunityConstant.ZERO, permsSet);

    }

    @Override
    public List<OperationMenuTreeDto> queryValidMenuListByRoleId(Integer roleId) {
        //获取全部可用菜单树
        List<OperationMenuTreeDto> operationMenuTreeVOList = mapper.selectValidMenuTreeAll();
        //获取用户所拥有的可用菜单Id
        List<Integer> MenuIdList = mapper.selectMenuByRoleId(roleId);
        Set<Integer> permsSet = new HashSet<>();
        MenuIdList.forEach(menu -> {
            permsSet.add(menu);
        });
        //0为根目录
        return buildMenuTree(operationMenuTreeVOList, BusinessOpportunityConstant.ZERO, permsSet);

    }

    /**
     * @return List<OperationMenuTreeVO>
     * @Description 获取全部可用菜单树
     * @Param
     **/
    @Override
    public List<OperationMenuTreeDto> queryValidMenuTree() {
        //获取全部可用菜单树
        List<OperationMenuTreeDto> operationMenuTreeVOList = mapper.selectValidMenuTreeAll();
        //获取用户所拥有的可用菜单Id
        Set<Integer> permsSet = new HashSet<>();
        //0为根目录
        return buildMenuTree(operationMenuTreeVOList, BusinessOpportunityConstant.ZERO, permsSet);
    }

    /**
     * @return OperationMenuDetailsRes
     * @Description 获取菜单详情
     * @Param menuId
     **/
    @Override
    public OperationMenuDto queryMenuDetails(Integer menuId) {
        OperationMenu menu = mapper.selectPk(menuId);
        if (menu == null || !menu.getId().equals(menuId)) {
            throw new DubboBusinessException(DubboExceptionEnum.MENU_NOT_EXIST);
        }
        OperationMenuDto operationMenuDto = new OperationMenuDto();
        BeanUtil.copyProperties(menu, operationMenuDto);
        return operationMenuDto;
    }

    /**
     * @return OperationMenuPageRes
     * @Description 获取菜单列表
     * @Param request
     **/
    @Override
    public OperationMenuPageRes queryMenuPage(OperationMenuPageReq request) {
        OperationMenuPageRes rolePageRes = mapper.queryCountMaxMenu(request);

        //判断是否超过最大页超过则直接返回
        if (request.getPageSize() > (rolePageRes.getTotal() / request.getLimit())) {
            return rolePageRes;
        }

        List<OperationMenuDto> menuVOList = mapper.selectMenuPage(request);
        rolePageRes.setMenuVOList(menuVOList);

        return rolePageRes;
    }


    /**
     * @param parentId  父菜单Id
     * @param menuIdSet 用户拥有的菜单Id
     * @return List<OperationMenuTreeVO>
     * @Description 组装菜单树
     * @Param operationMenuList
     **/
    private List<OperationMenuTreeDto> buildMenuTree(List<OperationMenuTreeDto> operationMenuList, Integer parentId,
                                                    Set<Integer> menuIdSet) {
        List<OperationMenuTreeDto> treeVOList = new ArrayList<>();
        operationMenuList.forEach(menu -> {
            if (menuIdSet.contains(menu.getId())) {
                menu.setChecked(Boolean.TRUE);
            }
            if (parentId.equals(menu.getParentId())) {
                menu.setChildMenus(buildMenuTree(operationMenuList, menu.getId(), menuIdSet));
                treeVOList.add(menu);
            }
        });
        return treeVOList;
    }
}
