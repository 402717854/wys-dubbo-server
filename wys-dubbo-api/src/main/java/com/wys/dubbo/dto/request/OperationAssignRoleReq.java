package com.wys.dubbo.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Classname OperationAssignRoleReq
 * @Description 为管理员分配角色
 * @Date 2020/12/17 14:30
 * @Created by 20113370
 */
@Data
public class OperationAssignRoleReq extends BasicRequest {

    private static final long serialVersionUID = -978777276200412323L;
    @NotNull(message = "用户编号不能为空")
    List<Integer> sysUserIdList;

    @NotNull(message = "角色编号不能为空")
    List<Integer> roleIdList;
}
