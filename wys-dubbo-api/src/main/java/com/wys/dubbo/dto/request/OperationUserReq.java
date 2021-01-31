package com.wys.dubbo.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @Classname AddOperationUserReq
 * @Description 添加运营端账户
 * @Date 2020/12/9 15:05
 * @Created by 20113370
 */
@Data
public class OperationUserReq extends BasicRequest {

    private static final long serialVersionUID = 978516723150165245L;

    private Integer id;

    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    @Length(max = 16, message = "账号不能超过16个字符")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,16}$", message = "账号必须由6至16位字母或数字组成")
    private String	account;
    //姓名
    @NotNull(message = "员工姓名不能为空")
    private String name;
    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    private String mobile;
    /**
     * 邮箱
     */
    @Email(message = "邮箱格式错误")
    private String email;
    /**
     * 备注
     */
    @Size(max = 500, message = "备注")
    private String remark;
    /**
     * 是否禁用 0否1是
     */
    @NotNull(message = "是否禁用不能为空")
    private Integer state;

}
