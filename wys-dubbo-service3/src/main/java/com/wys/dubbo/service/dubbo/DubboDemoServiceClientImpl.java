package com.wys.dubbo.service.dubbo;

import com.wys.dubbo.dto.DemoResultDto;
import com.wys.dubbo.result.RpcExecuteResult;
import com.wys.dubbo.service.DubboDemoServiceClient;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Description: dubbo demo
 * @Author: 86133
 * @Date: 2021/01/25
 */
@DubboService
public class DubboDemoServiceClientImpl implements DubboDemoServiceClient {
    @Override
    public RpcExecuteResult get() {
        DemoResultDto resultDto = new DemoResultDto();
        resultDto.setId(1);
        resultDto.setDescription("description---20882");
        return RpcExecuteResult.ok(resultDto);
   }
}
