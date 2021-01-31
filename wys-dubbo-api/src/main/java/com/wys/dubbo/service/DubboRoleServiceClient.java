package com.wys.dubbo.service;

import com.wys.dubbo.dto.request.OperationRolePageReq;
import com.wys.dubbo.dto.request.OperationRoleReq;
import com.wys.dubbo.dto.response.OperationRoleDetailsRes;
import com.wys.dubbo.dto.response.OperationRoleEasyRes;
import com.wys.dubbo.dto.response.OperationRolePageRes;
import com.wys.dubbo.result.RpcExecuteResult;

import javax.validation.Valid;

/**
 * @ClassName DubboUserServiceClient
 * @Description: TODO Dubbo服务接口
 * @Author wys
 * @Date 2021/1/25-10:08
 * @Version V1.0
 **/
public interface DubboRoleServiceClient {

    /**
     * 分页查询全部角色
     * @param request
     * @return
     */
    RpcExecuteResult<OperationRolePageRes> queryRolePage(OperationRolePageReq request);

    /**
     * 根据id查询单个角色详情
     * @param roleId
     * @return
     */
    public RpcExecuteResult<OperationRoleDetailsRes> queryRoleDetails(Integer roleId);

    /**
     * 添加或修改角色
     * @param request
     * @return
     */
    public RpcExecuteResult<Boolean> addOrEditRole(OperationRoleReq request);

    public RpcExecuteResult<OperationRoleEasyRes> queryRoleEasyAllList(Integer userId);
}
