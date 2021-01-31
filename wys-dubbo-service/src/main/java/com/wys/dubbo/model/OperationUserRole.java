package com.wys.dubbo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * POJO:管理员与角色对应关系
 *
 * @Author: 86133
 * @Date: 2021/01/25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperationUserRole implements Serializable{

    private static final long serialVersionUID = -5849225044827249145L;
    /**
     * 主键id
     */
    private Long id;

    /**
     * 管理员id
     */
    private Integer operationUserId;

    /**
     * 角色id
     */
    private Integer operationRoleId;

}
