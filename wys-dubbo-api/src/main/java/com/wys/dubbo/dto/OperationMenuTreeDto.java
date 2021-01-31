package com.wys.dubbo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname MenuTreeVO
 * @Description 菜单树状图
 * @Date 2020/12/15 10:37
 * @Created by 20113370
 */
@Data
public class OperationMenuTreeDto implements Serializable {

    private static final long serialVersionUID = -8928640392152359376L;
    private Integer id;

    private Integer parentId;

    private String name;

    private String icon;

    private String url;

    private boolean open = false;

    private boolean checked = false;


    private List<OperationMenuTreeDto> childMenus;

}
