package com.wys.dubbo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * POJO:角色权限对应关系
 *
 * @Author: 86133
 * @Date: 2021/01/25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperationRolePermission implements Serializable{

    private static final long serialVersionUID = -3496355263429108624L;
    /**
     * id
     */
    private Integer id;

    /**
     * 角色id
     */
    private Integer operationRoleId;

    /**
     * 菜单id
     */
    private Integer operationMenuId;

}
