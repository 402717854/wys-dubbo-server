package com.wys.dubbo.common.exception;

/**
 * @Author: 86133
 * @Date: 2021/01/25
 * @Description:: 异常定义
 */
public enum DubboExceptionEnum {

    SUCCESS("200","成功"),

    BAD_REQUEST("400", "Bad Request!"),
    NOT_AUTHORIZATION("401", "用户无访问权限"),
    NOT_FOUND_REQUEST("404", "Not Found Request Path"),
    METHOD_NOT_ALLOWED("405", "Method Not Allowed"),
    NOT_ACCEPTABLE("406", "Not Acceptable"),
    INTERNAL_SERVER_ERROR("500", "Internal Server Error"),

    LOGIN_FIRST("999", "[服务器]请登录"),
    RUNTIME_EXCEPTION("1000", "[服务器]运行时异常"),
    NULL_POINTER_EXCEPTION("1001", "[服务器]空值异常"),
    CLASS_CAST_EXCEPTION("1002", "[服务器]数据类型转换异常"),
    IO_EXCEPTION("1003", "[服务器]IO异常"),
    NO_SUCH_METHOD_EXCEPTION("1004", "[服务器]未知方法异常"),
    INDEX_OUT_OF_BOUNDS_EXCEPTION("1005", "[服务器]数组越界异常"),
    CONNECT_EXCEPTION("1006", "[服务器]网络异常"),
    ERROR_MEDIA_TYPE("1007", "[服务器]Content-type错误，请使用application/json"),
    EMPTY_REQUEST_BOYD("1008", "[服务器]request请求body不能为空"),
    ERROR_REQUEST_BOYD("1009", "[服务器]request请求body非json对象"),

    ERROR_VERSION("2000", "[服务器]版本号错误"),
    ERROR_FORMAT_PARAMETER("2001", "[服务器]参数格式错误"),

    USER_NOT_EXISTS("4010", "用户不存在"),
    WRONG_USERNAME("4011", "用户名输入错误"),
    WRONG_PASSWORD("4012", "密码输入错误"),
    UN_ALLOW_USER_TYPE("4013", "不被允许的账户类型"),
    FORBIDEN_USER("4015", "账户已被禁用"),
    UUC_FIRST_LOGIN("4016", "首次登录请使用手机号登录"),
    LOGIN_FAIL5("4017", "账户连续登录失败5次"),
    LOGIN_CAPTCHA("4018", "登录验证码输入错误"),
    LOGIN_ACCOUNT_LOCKED("4019", "账号登录失败10次，冻结24小时"),
    UUC_PASSWORD_UNUSED_1("4020", "密码由8-16位，小写字母、大写字母、数字、特殊符号的两种及以上组合"),
    UUC_PASSWORD_UNUSED_2("4021", "不能包含6位及以上的相同字符"),
    UUC_PASSWORD_UNUSED_3("4022", "不能包含6位及以上的连续字符"),

    REGISTER_ERROR("4030", "注册失败"),
    TONKER_EXPIRED("4031", "登录失效,请重新登录"),
    OPERATION_USER_EXISTS("4032", "当前账户已存在"),
    ADD_OPERATION_USER_FAIL("4033", "添加失败,请联系管理员"),
    ACCOUNT_NOT_VALID("4034", "用户名不合法"),
    LOGOUT_FAIL("4035", "退出登录失败"),
    SEND_VERIFICATION_CODE_ERROR("4036", "退出登录失败"),
    PERMISSION_DENIED("4037", "无访问权限"),
    PERMISSION_DETAIL_FORMAT_ERROR("4038", "权限代码必须以operate:开头"),
    PARENT_MENU_ERROR("4039", "父级菜单不可用"),


    ADD_OR_EDIT_MENU_ERROR("4040", "添加或修改菜单失败"),
    ROLE_NOT_EXIST("4041", "角色不存在无法修改"),
    MENU_NOT_EXIST("4042", "菜单不存在无法修改"),
    SYS_USER_NOT_EXIST("4043", "管理员信息不存在"),
    ACCOUNT_NOT_EXIST("4044", "账户不存在"),
    ADD_OR_UPDATE_SYS_USER_ERROR("4045", "添加或修改管理员失败"),

    SYS_USER_DISABLE("4046", "id为{0}的管理员不可用"),
    ROLE_DISABLE("4047", "id为{0}的角色不可用"),

    DATABASE_SYSTEM_BUILD_ERROR("9997", "组装数据库参数异常"),
    DATABASE_SYSTEM_ERROR("9998", "数据库操作异常"),

    UNKNOWN_ERROR("9996","未知异常"),
    SYSTEM_ERROR("9999", "系统异常");


    /**
     * code
     */
    private final String code;

    /**
     * message
     */
    private final String message;

    private DubboExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
   }

    public static String getNameByValue(String val) {
        if (val != null) {
            String value = val;
            for (DubboExceptionEnum constant : DubboExceptionEnum.values()) {
                if (constant.code == value) {
                    return constant.message;
               }
           }
       }
        return "";
   }

    public DubboExceptionEnum getTypeByValue(String value) {
        for (DubboExceptionEnum constant : DubboExceptionEnum.values()) {
            if (constant.code == value) {
                return constant;
           }
       }
        return null;
   }

    public String getCode() {
        return code;
   }

    public String getMessage() {
        return message;
   }

}
