package com.wys.dubbo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname OperationRoleEasyCheckedVO
 * @Description 角色简易数据
 * @Date 2020/12/16 16:54
 * @Created by 20113370
 */
@Data
public class OperationRoleEasyCheckedDto implements Serializable {
    private static final long serialVersionUID = 453181684363775745L;
    /**
     * id
     */
    private Integer id;

    /**
     * 角色名
     */
    private String name;


    /**
     * 是否勾选
     */
    private boolean checked = false;
}
