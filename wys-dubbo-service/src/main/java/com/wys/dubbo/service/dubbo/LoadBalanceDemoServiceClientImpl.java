package com.wys.dubbo.service.dubbo;

import com.wys.dubbo.dto.DemoResultDto;
import com.wys.dubbo.result.RpcExecuteResult;
import com.wys.dubbo.service.LoadBalanceDemoServiceClient;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class LoadBalanceDemoServiceClientImpl implements LoadBalanceDemoServiceClient {
    @Override
    public RpcExecuteResult get() {
        DemoResultDto resultDto = new DemoResultDto();
        resultDto.setId(1);
        resultDto.setDescription("description--20880");
        return RpcExecuteResult.ok(resultDto);
    }
}
