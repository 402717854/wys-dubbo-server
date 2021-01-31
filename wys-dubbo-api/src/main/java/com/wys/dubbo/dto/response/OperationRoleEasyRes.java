package com.wys.dubbo.dto.response;

import com.wys.dubbo.dto.OperationRoleEasyCheckedDto;
import lombok.Data;

import java.util.List;

/**
 * @Classname OperationRoleEasyRes
 * @Description 角色简易信息
 * @Date 2020/12/16 16:48
 * @Created by 20113370
 */
@Data
public class OperationRoleEasyRes extends BasicResponse {

    /**角色信息*/
    List<OperationRoleEasyCheckedDto> roleEasyVOList;
}
