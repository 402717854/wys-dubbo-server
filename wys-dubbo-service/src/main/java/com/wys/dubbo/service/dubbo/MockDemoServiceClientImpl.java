package com.wys.dubbo.service.dubbo;

import com.wys.dubbo.dto.DemoResultDto;
import com.wys.dubbo.result.RpcExecuteResult;
import com.wys.dubbo.service.MockDemoServiceClient;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;

@DubboService(executes = 10,delay = 0)
public class MockDemoServiceClientImpl implements MockDemoServiceClient {
    @Override
    public RpcExecuteResult get() {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DemoResultDto resultDto = new DemoResultDto();
        resultDto.setId(1);
        resultDto.setDescription("description");
        return RpcExecuteResult.ok(resultDto);
    }
}
