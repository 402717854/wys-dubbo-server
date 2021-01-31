package com.wys.dubbo.result;

import com.wys.dubbo.common.exception.DubboExceptionEnum;

import java.io.Serializable;

public class RpcExecuteResult<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1160805539905186816L;


    /**
     * success
     */
    private boolean success;

    /**
     * T <>
     */
    private T data;


    /**
     * message
     */
    private String message;

    /**
     * code 错误码
     */
    private String code;

    /**
     * url
     * @return
     */
    private String url;

    private DubboExceptionEnum exceptionEnum;

    public void setExecuteResultEnum(DubboExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
   }
    public boolean isSuccess() {
        return success;
  }
    public void setSuccess(boolean success) {
        this.success = success;
  }

    public T getData() {
        return data;
  }
    public void setData(T data) {
        this.data = data;
  }

    public String getMessage() {
        return exceptionEnum==null?message:exceptionEnum.getMessage();
  }
    public void setMessage(String message) {
        this.message = message;
  }

    public String getCode() {
        return exceptionEnum==null?code:exceptionEnum.getCode();
  }
    public void setCode(String code) {
        this.code = code;
  }

    public String getUrl() {
        return url;
  }
    public void setUrl(String url) {
        this.url = url;
  }

    /**
     *
     * @Title: ok
     * @param @param data
     * @param @return
     * @return ExecuteResult<T>
     * @throws
     */
    public static <T> RpcExecuteResult<T> ok(T data) {
        RpcExecuteResult<T> result = new RpcExecuteResult<T>();
        result.setData(data);
        result.setSuccess(true);
        result.setExecuteResultEnum(DubboExceptionEnum.SUCCESS);
        return result;
  }

    /**
     *
     * @param message
     * @return
     */
    public static <T> RpcExecuteResult<T> ok(String message) {
        RpcExecuteResult<T> result = new RpcExecuteResult<T>();
        result.setData(null);
        result.setSuccess(true);
        result.setCode(DubboExceptionEnum.SUCCESS.getCode());
        result.setMessage(message);
        return result;
  }

    /**
     *
     * @Title: ok
     * @param @param data
     * @param @param message
     * @param @return
     * @return RpcExecuteResult<T>
     * @throws
     */
    public static <T> RpcExecuteResult<T> ok(T data, String message) {
        RpcExecuteResult<T> result = new RpcExecuteResult<T>();
        result.setData(data);
        result.setSuccess(true);
        result.setCode(DubboExceptionEnum.SUCCESS.getCode());
        result.setMessage(message);
        return result;
  }

    /**
     *
     * @Title: ok
     * @param @return
     * @return RpcExecuteResult<T>
     * @throws
     */
    public static <T> RpcExecuteResult<T> ok() {
        return ok(null);
  }


    public static <T> RpcExecuteResult<T> fail(String message) {
        RpcExecuteResult<T> result = new RpcExecuteResult<T>();
        result.setMessage(message);
        result.setCode(DubboExceptionEnum.UNKNOWN_ERROR.getCode());
        result.setSuccess(false);
        return result;
   }
    /**
     *
     * @Title: failure
     * @param @param message
     * @param @param data
     * @param @return
     * @return RpcExecuteResult<T>
     * @throws
     */
    public static <T> RpcExecuteResult<T> fail(String errorCode, String message) {
        RpcExecuteResult<T> result = new RpcExecuteResult<T>();
        result.setMessage(message);
        result.setCode(errorCode);
        result.setSuccess(false);
        return result;
  }

    /**
     *
     * @Title: failure
     * @param @param errorCode
     * @param @param message
     * @param @param data
     * @param @return
     * @return RpcExecuteResult<T>
     * @throws
     */
    public static <T> RpcExecuteResult<T> fail(String errorCode, String message, T data) {
        RpcExecuteResult<T> result = new RpcExecuteResult<T>();
        result.setCode(errorCode);
        result.setMessage(message);
        result.setData(data);
        result.setSuccess(false);
        return result;
  }

    public static <T> RpcExecuteResult<T> fail(DubboExceptionEnum exceptionEnum, T data) {
        RpcExecuteResult<T> result = new RpcExecuteResult<T>();
        result.setExecuteResultEnum(exceptionEnum);
        result.setSuccess(false);
        result.setData(data);
        return result;
   }

    public static <T> RpcExecuteResult<T> fail(DubboExceptionEnum exceptionEnum) {
        return fail(exceptionEnum,null);
   }

}

