package com.wys.dubbo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * POJO:运营用户
 *
 * @Author: 86133
 * @Date: 2021/01/25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperationUser implements Serializable{

    private static final long serialVersionUID = 7805359370526140083L;
    /**
     * id
     */
    private Integer id;

    /**
     * 账号
     */
    private String account;

    private String password;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否禁用 0:否 1:是
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private Integer createUser;

    /**
     * 修改人
     */
    private Integer updateUser;

}
