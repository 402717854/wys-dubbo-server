package com.wys.dubbo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname OperationRoleVO
 * @Description 角色vo
 * @Date 2020/12/14 17:33
 * @Created by 20113370
 */
@Data
public class OperationRoleDto implements Serializable {
    private static final long serialVersionUID = -8289972776354635994L;
    /**
     * id
     */
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否禁用 0:否 1:是
     */
    private Integer state;
}
