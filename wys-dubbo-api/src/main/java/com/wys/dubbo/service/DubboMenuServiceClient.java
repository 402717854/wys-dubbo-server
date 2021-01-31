package com.wys.dubbo.service;

import com.wys.dubbo.dto.OperationMenuDto;
import com.wys.dubbo.dto.request.OperationMenuPageReq;
import com.wys.dubbo.dto.request.OperationMenuReq;
import com.wys.dubbo.dto.response.OperationMenuPageRes;
import com.wys.dubbo.dto.response.OperationValidMenuTreeRes;
import com.wys.dubbo.result.RpcExecuteResult;

import javax.validation.Valid;
import java.util.Set;

/**
 * @ClassName DubboUserServiceClient
 * @Description: TODO Dubbo服务接口
 * @Author wys
 * @Date 2021/1/25-10:08
 * @Version V1.0
 **/
public interface DubboMenuServiceClient {

    /**
     * 分页查询全部菜单
     * @param request
     * @return
     */
    RpcExecuteResult<OperationMenuPageRes> queryMenuPage(OperationMenuPageReq request);

    /**
     * 获取菜单详情
     * @param menuId
     * @return
     */
    RpcExecuteResult<OperationMenuDto> queryMenuDetails(Integer menuId);

    /**
     * 获取可用菜单树
     * @return
     */
    RpcExecuteResult<OperationValidMenuTreeRes> queryValidMenuTree();

    /**
     * 添加或修改菜单
     * @param request
     * @return
     */
    RpcExecuteResult<Boolean> addOrEditMenu(OperationMenuReq request);

    /**
     * 根据用户Id获取拥有的菜单
     * @param userId
     * @return
     */
    RpcExecuteResult<Set<String>> queryPermsMenuByUserId(Integer userId);
}
