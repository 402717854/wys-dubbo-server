package com.wys.dubbo.service.dubbo;

import com.wys.dubbo.dto.DemoResultDto;
import com.wys.dubbo.result.RpcExecuteResult;
import com.wys.dubbo.service.DubboDemoServiceClient;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.concurrent.CompletableFuture;

@DubboService(group = "pay.weixin")
public class WeixinServiceClientImpl implements DubboDemoServiceClient {
    @Override
    public RpcExecuteResult get() {
        DemoResultDto resultDto = new DemoResultDto();
        resultDto.setId(1);
        resultDto.setDescription("使用微信支付");
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
