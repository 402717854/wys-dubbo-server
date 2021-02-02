package com.wys.dubbo.service;

import com.wys.dubbo.result.RpcExecuteResult;

public interface LoadBalanceDemoServiceClient {
    /**
     * dubbo demo
     * @return
     */
    RpcExecuteResult get();
}
