package com.wys.dubbo.dto.request;

import lombok.Data;

/**
 * @Classname OperationRoleQueryReq
 * @Description 查询角色列表入参
 * @Date 2020/12/14 14:28
 * @Created by 20113370
 */
@Data
public class OperationRolePageReq extends PageReq {

    private static final long serialVersionUID = -4639253307346112433L;
    /**
     * 角色名
     */
    private String name;

    /**
     * 是否禁用 0:否 1:是
     */
    private Integer state;
}
