package com.wys.dubbo.service;

import com.wys.dubbo.result.RpcExecuteResult;

public interface ProtocolDemoServiceClient {
    /**
     * dubbo demo
     * @return
     */
    RpcExecuteResult get();
}
