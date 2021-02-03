package com.wys.dubbo.service;

import com.wys.dubbo.dto.DemoResultDto;
import com.wys.dubbo.result.RpcExecuteResult;

import java.util.concurrent.CompletableFuture;

/**
 * @Description: 异常捕获
 * @Author: 86133
 * @Date: 2021/01/25
 */
public interface DubboDemoServiceClient {
    /**
     * dubbo demo
     * @return
     */
    RpcExecuteResult get();

    CompletableFuture<RpcExecuteResult> get2();

    CompletableFuture<RpcExecuteResult> asyncGet();
}
