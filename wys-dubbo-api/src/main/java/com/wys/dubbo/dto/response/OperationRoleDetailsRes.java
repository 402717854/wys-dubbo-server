package com.wys.dubbo.dto.response;

import com.wys.dubbo.dto.OperationMenuTreeDto;
import lombok.Data;

import java.util.List;

/**
 * @Classname OperationRoleDetailsRes
 * @Description 角色详情出参
 * @Date 2020/12/14 14:34
 * @Created by 20113370
 */
@Data
public class OperationRoleDetailsRes extends BasicResponse {

    private static final long serialVersionUID = 9017483784135387160L;
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

    /**
     * 角色菜单 list
     **/
    private List<OperationMenuTreeDto> roleMenuList;

}
