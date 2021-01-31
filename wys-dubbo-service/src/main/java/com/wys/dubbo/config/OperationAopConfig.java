package com.wys.dubbo.config;


import com.wys.dubbo.common.exception.DubboBusinessException;
import com.wys.dubbo.common.exception.DubboExceptionEnum;
import com.wys.dubbo.result.RpcExecuteResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: AopConfig 切面校验 捕获异常
 * @Author: lenovo
 * @Date: 2021/01/27
 */
@Configuration
@Aspect
@Slf4j
public class OperationAopConfig {

    /**
     * 切面路径地址凡是RestController注解所处理的
     */
    @Pointcut("@within(org.apache.dubbo.config.annotation.Service)")
    public void pointcut() {
   }

    //设置扫描所有切面并且被注解修饰的方法环绕通知
    @Around("pointcut()")
    public Object simpleAop(final ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName=joinPoint.getSignature().getName();
        String className=joinPoint.getSignature().getDeclaringTypeName();
        Long befor = System.currentTimeMillis();
        // 调用原有的方法
        try {
            Object o = joinPoint.proceed();
            Long after = System.currentTimeMillis();
            log.info("调用方法结束===================共耗时：" + (after - befor) + "毫秒");
            log.debug("方法返回：return:====================" + o);
            return o;
       }catch (DubboBusinessException e){
            log.error("[{}]调用方法[{}]出现运行时异常:",className,methodName,e);
            return RpcExecuteResult.fail(e.getErrorCode(), e.getMessage());
       }catch (MethodArgumentNotValidException e){
            log.error("[{}]调用方法[{}]出现参数验证异常:",className,methodName,e);
            BindingResult result = e.getBindingResult();
            StringBuilder errorMsg = new StringBuilder();
            if (result.hasErrors()) {
                List<FieldError> fieldErrors = result.getFieldErrors();
                fieldErrors.forEach(error -> {
                    log.debug("field" + error.getField() + ", msg:" + error.getDefaultMessage());
                    errorMsg.append(error.getDefaultMessage()).append("!");
                });

            }
            return RpcExecuteResult.fail(DubboExceptionEnum.ERROR_FORMAT_PARAMETER.getCode(),errorMsg.toString());
        }catch (Exception e){
            log.error("[{}]调用方法[{}]出现未知系统异常:",className,methodName,e);
            return RpcExecuteResult.fail(DubboExceptionEnum.SYSTEM_ERROR);
       }


   }

    /**
     * @return void
     * @Description 前置拦截
     * @Param joinPoint
     * @Date 12:47 2020/12/11
     * @Author lizy
     **/
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        log.debug("方法名 : " + joinPoint.getSignature().getName());
        log.debug("类路径 : " + joinPoint.getSignature().getDeclaringTypeName());
        log.debug("参数 : " + Arrays.toString(joinPoint.getArgs()));
        Long befor = System.currentTimeMillis();
        log.debug("方法开始时间：【{}】", befor);
   }

}
