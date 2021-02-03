package com.wys.dubbo.service.dubbo;

import com.wys.dubbo.dto.DemoResultDto;
import com.wys.dubbo.result.RpcExecuteResult;
import com.wys.dubbo.service.DubboDemoServiceClient;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @Description: dubbo demo
 * @Author: 86133
 * @Date: 2021/01/25
 */
@DubboService(version = "1.0.0")
public class DubboDemoServiceClientImpl implements DubboDemoServiceClient {
    @Override
    public RpcExecuteResult get() {
        long start = System.currentTimeMillis();

        DemoResultDto resultDto = new DemoResultDto();
        resultDto.setId(1);
        resultDto.setDescription("description");

        sleep("get",start);
        return RpcExecuteResult.ok(resultDto);
   }

    @Override
    public CompletableFuture<RpcExecuteResult> get2() {
        long start = System.currentTimeMillis();
        CompletableFuture<RpcExecuteResult> future = CompletableFuture.supplyAsync(new Supplier<RpcExecuteResult>() {
            @Override
            public RpcExecuteResult get() {
                DemoResultDto resultDto = new DemoResultDto();
                resultDto.setId(1);
                resultDto.setDescription("description");
                RpcExecuteResult<DemoResultDto> rpcExecuteResult = RpcExecuteResult.ok(resultDto);
                sleep("get2",start);
                return rpcExecuteResult;
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<RpcExecuteResult> asyncGet() {
        long start = System.currentTimeMillis();
        DemoResultDto resultDto = new DemoResultDto();
        resultDto.setId(1);
        resultDto.setDescription("description");
        RpcExecuteResult<DemoResultDto> rpcExecuteResult = RpcExecuteResult.ok(resultDto);
        CompletableFuture<RpcExecuteResult> completedFuture = CompletableFuture.completedFuture(rpcExecuteResult);
        sleep("get",start);
        return completedFuture;
    }

    private void sleep(String methodName,Long start){
       try {
           TimeUnit.SECONDS.sleep(3);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       long end = System.currentTimeMillis();
       long use=end-start;
       System.out.println(methodName+"方法执行用时："+use);
   }
}
