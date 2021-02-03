package com.wys.dubbo.service.dubbo;

import com.wys.dubbo.dto.DemoResultDto;
import com.wys.dubbo.result.RpcExecuteResult;
import com.wys.dubbo.service.DubboDemoServiceClient;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.concurrent.CompletableFuture;

/**
 * @Description: dubbo demo
 * @Author: 86133
 * @Date: 2021/01/25
 */
@DubboService(version = "2.0.0")
public class DubboDemoServiceClientImpl2 implements DubboDemoServiceClient {
    @Override
    public RpcExecuteResult get() {
        DemoResultDto resultDto = new DemoResultDto();
        resultDto.setId(1);
        resultDto.setDescription("description------123456789");
        return RpcExecuteResult.ok(resultDto);
   }

    @Override
    public CompletableFuture<RpcExecuteResult> get2() {
        return null;
    }

    @Override
    public CompletableFuture<RpcExecuteResult> asyncGet() {
        return null;
    }
}
