package com.wys.dubbo.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Classname OperationCreateAccountReq
 * @Description 注册新账号
 * @Date 2020/12/9 16:41
 * @Created by 20113370
 */
@Data
public class OperationCreateAccountReq extends BasicRequest {
    private static final long serialVersionUID = -6926249326316744662L;
    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    @Length(max = 16, message = "账号不能超过16个字符")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,16}$", message = "账号必须由6至16位字母或数字组成")
    private String account;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 验证码(手机或邮箱)
     */
    private String verificationCode;

}
