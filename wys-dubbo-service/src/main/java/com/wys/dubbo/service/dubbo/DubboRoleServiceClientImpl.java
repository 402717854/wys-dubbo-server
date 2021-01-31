package com.wys.dubbo.service.dubbo;

import com.wys.dubbo.dto.request.OperationRolePageReq;
import com.wys.dubbo.dto.request.OperationRoleReq;
import com.wys.dubbo.dto.response.OperationRoleDetailsRes;
import com.wys.dubbo.dto.response.OperationRoleEasyRes;
import com.wys.dubbo.dto.response.OperationRolePageRes;
import com.wys.dubbo.result.RpcExecuteResult;
import com.wys.dubbo.service.DubboRoleServiceClient;
import com.wys.dubbo.service.OperationRoleService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

/**
 * @ClassName DubboRoleServiceClient
 * @Description: TODO 角色服务提供者
 * @Author wys
 * @Date 2021/1/25-10:34
 * @Version V1.0
 **/
@Service
public class DubboRoleServiceClientImpl implements DubboRoleServiceClient {

    @Autowired
    OperationRoleService operationRoleService;

    @Override
    public RpcExecuteResult<OperationRolePageRes> queryRolePage(OperationRolePageReq request) {
        return RpcExecuteResult.ok(operationRoleService.queryRolePage(request));
    }

    @Override
    public RpcExecuteResult<OperationRoleDetailsRes> queryRoleDetails(Integer roleId) {
        return RpcExecuteResult.ok(operationRoleService.queryRoleDetails(roleId));
    }

    @Override
    public RpcExecuteResult<Boolean> addOrEditRole(OperationRoleReq request) {
        return RpcExecuteResult.ok(operationRoleService.addOrEditRole(request));
    }

    @Override
    public RpcExecuteResult<OperationRoleEasyRes> queryRoleEasyAllList(Integer userId) {
        return RpcExecuteResult.ok(operationRoleService.queryRoleEasyAllList(userId));
    }


}
