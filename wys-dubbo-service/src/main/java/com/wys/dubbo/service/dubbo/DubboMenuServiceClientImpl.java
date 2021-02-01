package com.wys.dubbo.service.dubbo;

import com.wys.dubbo.dto.OperationMenuDto;
import com.wys.dubbo.dto.OperationMenuTreeDto;
import com.wys.dubbo.dto.request.OperationMenuPageReq;
import com.wys.dubbo.dto.request.OperationMenuReq;
import com.wys.dubbo.dto.response.OperationMenuPageRes;
import com.wys.dubbo.dto.response.OperationValidMenuTreeRes;
import com.wys.dubbo.result.RpcExecuteResult;
import com.wys.dubbo.service.DubboMenuServiceClient;
import com.wys.dubbo.service.OperationMenuService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * @ClassName DubboMenuServiceClientImpl
 * @Description: TODO 菜单服务提供者
 * @Author wys
 * @Date 2021/1/25-10:33
 * @Version V1.0
 **/
@DubboService
public class DubboMenuServiceClientImpl implements DubboMenuServiceClient {
    @Autowired
    private OperationMenuService operationMenuService;

    @Override
    public RpcExecuteResult<OperationMenuPageRes> queryMenuPage(OperationMenuPageReq request) {
       return RpcExecuteResult.ok(operationMenuService.queryMenuPage(request));
    }

    @Override
    public RpcExecuteResult<OperationMenuDto> queryMenuDetails(Integer menuId) {
        return RpcExecuteResult.ok(operationMenuService.queryMenuDetails(menuId));
    }

    @Override
    public RpcExecuteResult<OperationValidMenuTreeRes> queryValidMenuTree() {
        List<OperationMenuTreeDto> operationMenuTreeVOList = operationMenuService.queryValidMenuTree();
        OperationValidMenuTreeRes validMenuTreeRes = new OperationValidMenuTreeRes();
        validMenuTreeRes.setValidMenuTree(operationMenuTreeVOList);
        return RpcExecuteResult.ok(validMenuTreeRes);
    }

    @Override
    public RpcExecuteResult<Boolean> addOrEditMenu(@Valid OperationMenuReq request) {
        return RpcExecuteResult.ok(operationMenuService.addOrEditMenu(request));
    }

    @Override
    public RpcExecuteResult<Set<String>> queryPermsMenuByUserId(Integer userId) {
        Set<String> strings = operationMenuService.queryPermsMenuByUserId(userId);
        return RpcExecuteResult.ok(strings);
    }
}
