package com.wys.dubbo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * POJO:运营菜单
 *
 * @Author: 86133
 * @Date: 2021/01/25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperationMenu implements Serializable{

    private static final long serialVersionUID = -3990664529618778113L;
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

    /**
     * 创建人
     */
    private Integer createUser;

    /**
     * 修改人
     */
    private Integer updateUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
