package com.wys.dubbo.dto.response;

import com.wys.dubbo.dto.OperationMenuDto;
import lombok.Data;

import java.util.List;

/**
 * @Classname OperationMenuPageRes
 * @Description 菜单分页数据
 * @Date 2020/12/15 16:16
 * @Created by 20113370
 */
@Data
public class OperationMenuPageRes extends PageRes {

    private static final long serialVersionUID = 1092739827779879614L;
    private List<OperationMenuDto> menuVOList;
}
