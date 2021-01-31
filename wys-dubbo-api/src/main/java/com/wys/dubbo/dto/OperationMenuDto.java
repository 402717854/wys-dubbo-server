package com.wys.dubbo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname OperationMenuVO
 * @Description 菜单数据VO
 * @Date 2020/12/15 10:10
 * @Created by 20113370
 */
@Data
public class OperationMenuDto implements Serializable {
    private static final long serialVersionUID = -7448476198513569619L;
    /**
     * id
     */
    private Integer id;

    /**
     * 上级菜单id
     */
    private Integer parentId;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 排序值
     */
    private Integer sortBy;

    /**
     * 菜单类型 1:左侧导航 2:页面按钮 3:列表页按钮
     */
    private Integer type;

    /**
     * 按钮图标
     */
    private String icon;

    /**
     * 菜单链接
     */
    private String url;

    /**
     * 菜单权限
     */
    private String permissionDetail;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 是否禁用 0:否 1:是
     */
    private Integer state;


}
