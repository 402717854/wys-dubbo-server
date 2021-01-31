package com.wys.dubbo.dto.response;

import com.wys.dubbo.dto.OperationMenuTreeDto;
import lombok.Data;

import java.util.List;

/**
 * @Classname OperationValidMenuTree
 * @Description 可用的菜单树
 * @Date 2020/12/15 15:05
 * @Created by 20113370
 */
@Data
public class OperationValidMenuTreeRes extends BasicResponse {
    private static final long serialVersionUID = 2399518858622934720L;
    private List<OperationMenuTreeDto> validMenuTree;
}
