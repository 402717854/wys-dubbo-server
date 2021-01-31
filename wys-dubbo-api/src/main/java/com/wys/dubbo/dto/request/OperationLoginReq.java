package com.wys.dubbo.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Classname OperationLoginReq
 * @Description 用户登录入参
 * @Date 2020/12/9 13:57
 * @Created by 20113370
 */
@Data
public class OperationLoginReq extends BasicRequest {

    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    @Length(max = 16, message = "账号不能超过16个字符")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,16}$", message = "账号必须由6至16位字母或数字组成")
    private String	account;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;


}
