package com.wys.dubbo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname OperationSysUserVO
 * @Description 管理员包装数据
 * @Date 2020/12/16 14:23
 * @Created by 20113370
 */
@Data
public class OperationSysUserDto implements Serializable {

    private static final long serialVersionUID = 4909183781849702618L;
    /**
     * id
     */
    private Integer id;

    /**
     * 账号
     */
    private String account;

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
     * 拥有角色信息
     */
    private List<OperationRoleEasyDto> roleList;

}
