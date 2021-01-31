package com.wys.dubbo.dto.request;

import lombok.Data;

/**
 * @Classname OperationSysUserPageReq
 * @Description 管理员列表
 * @Date 2020/12/16 14:15
 * @Created by 20113370
 */
@Data
public class OperationSysUserPageReq extends PageReq {

    private static final long serialVersionUID = 158645319643642768L;
    /**
     * 用户名
     */
    private String account;

    /**
     * 姓名
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

    private Integer roleId;

    /**
     * 当前状态0未禁用1已禁用
     */
    private Integer state;
}
