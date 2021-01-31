package com.wys.dubbo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname OperationRoleEasyVO
 * @Description 角色简易信息
 * @Date 2020/12/16 15:22
 * @Created by 20113370
 */
@Data
public class OperationRoleEasyDto implements Serializable {

    private static final long serialVersionUID = 2723341533610575659L;
    /**
     * id
     */
    private Integer id;

    /**
     * 角色名
     */
    private String name;

}
