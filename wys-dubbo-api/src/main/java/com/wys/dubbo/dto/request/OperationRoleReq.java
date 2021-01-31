package com.wys.dubbo.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Classname OperationRoleReq
 * @Description 添加修改角色入参
 * @Date 2020/12/14 10:38
 * @Created by 20113370
 */
@Data
public class OperationRoleReq extends BasicRequest {
    /**
     * id
     */
    private Integer id;

    /**
     * 角色名
     */
    @NotBlank(message = "角色名不能为空")
    @Length(max = 64, message = "角色名不能超过64个字符")
    private String name;

    /**
     * 备注
     */
    @Length(max = 128, message = "备注信息不能超过128个字符")
    private String remark;

    /**
     * 是否禁用 0:否 1:是
     */
    @NotNull(message = "是否禁用不能为空")
    private Integer state;

    /**
     * 菜单Id list
     **/
    private List<Integer> menuList;
}
