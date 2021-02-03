package com.wys.dubbo.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

/**
 * @Author: 86133
 * @Date: 2021/01/27
 * @Description:: 业务异常
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper=true)
public class DubboBusinessException extends RuntimeException {

    private String errorCode;

    public DubboBusinessException(String message) {
        super(message);
   }

    /**
     * @param errorCode
     * @param message
     */
    public DubboBusinessException(String errorCode, String message) {
        super(message);
        this.setErrorCode(errorCode);
   }

    public DubboBusinessException(DubboExceptionEnum dubboExceptionEnum) {
        super(dubboExceptionEnum.getMessage());
        this.setErrorCode(dubboExceptionEnum.getCode());
   }

    public DubboBusinessException(DubboExceptionEnum dubboExceptionEnum, Object... obj) {
        super(MessageFormat.format(dubboExceptionEnum.getMessage(),obj));
        this.setErrorCode(dubboExceptionEnum.getCode());
   }
}
