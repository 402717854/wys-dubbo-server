package com.wys.dubbo.dto.response;

import com.wys.dubbo.dto.OperationRoleDto;
import lombok.Data;

import java.util.List;

/**
 * @Classname OperationRolePageRes
 * @Description 角色分页数据
 * @Date 2020/12/14 16:56
 * @Created by 20113370
 */
@Data
public class OperationRolePageRes extends PageRes {

    private static final long serialVersionUID = -9124960733410452505L;

    private List<OperationRoleDto> roleVOList;
}
