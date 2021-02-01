package com.wys.dubbo.service.dubbo;

import com.alibaba.fastjson.JSONObject;
import com.wys.dubbo.common.constant.BusinessOpportunityConstant;
import com.wys.dubbo.common.exception.DubboBusinessException;
import com.wys.dubbo.common.exception.DubboExceptionEnum;
import com.wys.dubbo.dto.request.OperationAssignRoleReq;
import com.wys.dubbo.dto.request.OperationLoginReq;
import com.wys.dubbo.dto.request.OperationUserReq;
import com.wys.dubbo.dto.response.OperationTokenRes;
import com.wys.dubbo.model.OperationUser;
import com.wys.dubbo.result.RpcExecuteResult;
import com.wys.dubbo.service.DubboUserServiceClient;
import com.wys.dubbo.service.OperationRolePermissionService;
import com.wys.dubbo.service.OperationUserService;
import com.wys.dubbo.util.Md5Util;
import com.wys.dubbo.util.RedisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @ClassName DubboUserServiceClientImpl
 * @Description: TODO 用户服务提供者
 * @Author wys
 * @Date 2021/1/25-10:31
 * @Version V1.0
 **/
@DubboService
public class DubboUserServiceClientImpl implements DubboUserServiceClient {

    @Autowired
    private OperationUserService userService;
    @Autowired
    private OperationRolePermissionService rolePermissionService;

    @Override
    public RpcExecuteResult<OperationTokenRes> login(@Valid OperationLoginReq request) {
        //先行判断是否存在于当前系统
        OperationUser operationUser = userService.checkLogin(request.getAccount());
        if(operationUser==null){
            throw new DubboBusinessException(DubboExceptionEnum.USER_NOT_EXISTS);
        }
        String password = request.getPassword();
        String md5SaltPassword = Md5Util.getMD5SaltPassword(password, BusinessOpportunityConstant.PASSWORD_SALT);
        if(!md5SaltPassword.equals(operationUser.getPassword())){
            throw new DubboBusinessException(DubboExceptionEnum.WRONG_PASSWORD);
        }
        String token = Md5Util.getMd5String(JSONObject.toJSONString(operationUser));

        //重新配置用户角色
        rolePermissionService.resetUserRole(operationUser.getId());
        OperationTokenRes operationTokenRes = new OperationTokenRes();
        operationTokenRes.setAccessToken(token);
        operationTokenRes.setLoginName(request.getAccount());
        operationTokenRes.setId(operationUser.getId());

        RedisUtils.set(token,JSONObject.toJSONString(operationTokenRes),60l, TimeUnit.MINUTES);

        return RpcExecuteResult.ok(operationTokenRes);
    }

    @Override
    public RpcExecuteResult<Boolean> logout(String authToken) {
        RedisUtils.remove(authToken);
        return RpcExecuteResult.ok();
    }

    @Override
    public RpcExecuteResult<Boolean> assignRoleBatch(@Valid OperationAssignRoleReq request) {
        //过滤重复
        if(CollectionUtils.isNotEmpty(request.getSysUserIdList())){
            List<Integer> collect = request.getSysUserIdList().stream().distinct().collect(Collectors.toList());
            request.setSysUserIdList(collect);
        }
        if(CollectionUtils.isNotEmpty(request.getRoleIdList())){
            List<Integer> collect1 = request.getRoleIdList().stream().distinct().collect(Collectors.toList());
            request.setRoleIdList(collect1);
        }
        return RpcExecuteResult.ok(userService.assignRoleBatch(request));
    }

    @Override
    public RpcExecuteResult<Boolean> addOrUpdateOperationUser(@Valid OperationUserReq request) {
        return RpcExecuteResult.ok(userService.addOrUpdateOperationUser(request));
    }
}
