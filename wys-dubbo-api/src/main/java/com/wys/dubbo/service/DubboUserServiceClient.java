package com.wys.dubbo.service;

import com.wys.dubbo.dto.request.OperationAssignRoleReq;
import com.wys.dubbo.dto.request.OperationLoginReq;
import com.wys.dubbo.dto.request.OperationUserReq;
import com.wys.dubbo.dto.response.OperationTokenRes;
import com.wys.dubbo.result.RpcExecuteResult;

import javax.validation.Valid;

/**
 * @ClassName DubboUserServiceClient
 * @Description: TODO Dubbo服务接口
 * @Author wys
 * @Date 2021/1/25-10:08
 * @Version V1.0
 **/
public interface DubboUserServiceClient {

    /**
     * 登录
     * @param request
     * @return
     */
    RpcExecuteResult<OperationTokenRes> login(@Valid OperationLoginReq request);

    /**
     * 退出登录
     * @param authToken
     */
    RpcExecuteResult<Boolean> logout(String authToken);

    /**
     * 为多个管理员分配角色
     * @param request
     * @return
     */
    RpcExecuteResult<Boolean> assignRoleBatch(OperationAssignRoleReq request);

    /**
     * 增加/修改运营端账户
     * @return
     */
    RpcExecuteResult<Boolean> addOrUpdateOperationUser(OperationUserReq request);
}
