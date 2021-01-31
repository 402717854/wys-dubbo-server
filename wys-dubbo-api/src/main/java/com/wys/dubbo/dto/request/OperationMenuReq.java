package com.wys.dubbo.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @Classname OperationAddMenuReq
 * @Description 添加或修改菜单入参
 * @Date 2020/12/11 16:19
 * @Created by 20113370
 */
@Data
public class OperationMenuReq extends BasicRequest {

    /**
     * iD
     */
    private Integer id;         /* 父级ID */

    /**
     * 父级ID
     */
    private Integer parentId;         /* 父级ID */

    @NotBlank(message = "菜单名不能为空")
    @Length(max = 64, message = "菜单名不能超过64个字符")
    private String name;         /* 菜单名 */

    @NotNull(message = "排序值不能为空")
    @Range(min = 1, max = 100000, message = "排序值必须为1到100000之间的整数")
    private Integer sortBy;         /* 排序值 */

    @NotNull(message = "菜单类型不能为空")
    private Integer type;         /* 菜单类型 1: 横向导航 2:左侧导航 3:页面按钮 4:列表页按钮 */

    private String icon;         /* 按钮图标 */

    @Length(max = 200, message = "菜单url不能超过200个字符")
    private String url;         /* 菜单链接 */

    @Length(max = 64, message = "菜单权限代码不能超过64个字符")
    private String permissionDetail;         /* 菜单权限 */

    @Length(max = 128, message = "备注信息不能超过128个字符")
    private String remark;         /* 备注信息 */

    @NotNull(message = "是否禁用不能为空")
    private Integer state;         /* 是否禁用 0:否 1:是 */


}
