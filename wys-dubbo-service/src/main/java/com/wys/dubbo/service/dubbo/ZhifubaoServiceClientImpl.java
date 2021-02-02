package com.wys.dubbo.service.dubbo;

import com.wys.dubbo.dto.DemoResultDto;
import com.wys.dubbo.result.RpcExecuteResult;
import com.wys.dubbo.service.DubboDemoServiceClient;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(group = "pay.zhifubao")
public class ZhifubaoServiceClientImpl implements DubboDemoServiceClient {
    @Override
    public RpcExecuteResult get() {
        DemoResultDto resultDto = new DemoResultDto();
        resultDto.setId(1);
        resultDto.setDescription("使用支付宝支付");
        return RpcExecuteResult.ok(resultDto);
    }
}
