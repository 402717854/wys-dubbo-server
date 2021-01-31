package com.wys.dubbo.service;

import com.wys.dubbo.common.mybatis.BaseService;
import com.wys.dubbo.dto.request.OperationAssignRoleReq;
import com.wys.dubbo.dto.request.OperationSysUserPageReq;
import com.wys.dubbo.dto.request.OperationUserReq;
import com.wys.dubbo.dto.response.OperationSysUserPageRes;
import com.wys.dubbo.model.OperationUser;

import java.util.List;

/**
 * @Description: Interface:OperationUser
 * @Author: boris
 * @Date: 2020/12/08
 */
public interface OperationUserService extends BaseService<OperationUser, Integer> {

    /**
     * @return ExecuteResult
     * @Description 添加运营端账户
     * @Param request
     **/
    Boolean addOrUpdateOperationUser(OperationUserReq request);

    /**
     * 登录
     * @return ExecuteResult<LoginTokenVo>
     * @Description 在此处仅用于判断其在当前系统状态是否可以登录
     * @Param access
     **/
    OperationUser checkLogin(String access);

    /**
     * @return List<Integer>
     * @Description 获取用户所有角色
     * @Param userId
     **/
    List<Integer> queryUserRoleId(Integer userId);

    /**
     * 获取管理员分页列表
     */
    OperationSysUserPageRes querySysUserPage(OperationSysUserPageReq request);

    /**
     * 批量分配角色
     */
    Boolean assignRoleBatch(OperationAssignRoleReq request);

    /**
     * 根据userid获取用户信息
     */
    OperationUser querySysUserCacheByUserId(Integer userId);
}