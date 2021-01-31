package com.wys.dubbo.service;

import com.wys.dubbo.common.mybatis.BaseService;
import com.wys.dubbo.dto.OperationRoleDto;
import com.wys.dubbo.dto.request.OperationRolePageReq;
import com.wys.dubbo.dto.request.OperationRoleReq;
import com.wys.dubbo.dto.response.OperationRoleDetailsRes;
import com.wys.dubbo.dto.response.OperationRoleEasyRes;
import com.wys.dubbo.dto.response.OperationRolePageRes;
import com.wys.dubbo.model.OperationRole;

import java.util.List;

/**
 * @Description: Interface:OperationRole
 * @Author: boris
 * @Date: 2020/12/08
 */
public interface OperationRoleService extends BaseService<OperationRole, Integer> {
    /**
     * 添加或修改角色
     */
    Boolean addOrEditRole(OperationRoleReq request);

    /**
     * 查询角色分页数据
     */
    OperationRolePageRes queryRolePage(OperationRolePageReq request);

    /**
     * 查询角色详情
     */
    OperationRoleDetailsRes queryRoleDetails(Integer roleId);

    /**
     * 根据管理员Id查询所有角色
     */
    List<OperationRoleDto> queryUserRoleVOByUserId(Integer userId);

    /**
     * 获取角色列表并附带用户是否选中
     */
    OperationRoleEasyRes queryRoleEasyAllList(Integer userId);

    /**
     * 根据角色ID获取角色信息
     */
    OperationRole queryRoleVOCache(Integer roleId);
}