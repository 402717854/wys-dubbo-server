package com.wys.dubbo.dto.response;

import com.wys.dubbo.dto.OperationSysUserDto;
import lombok.Data;

import java.util.List;

/**
 * @Classname OperationSysUserPageRes
 * @Description 管理员列表数据
 * @Date 2020/12/16 14:22
 * @Created by 20113370
 */
@Data
public class OperationSysUserPageRes extends PageRes {

    private static final long serialVersionUID = 1945759238686880086L;
    /**管理员列表*/
    List<OperationSysUserDto> sysUserVOList;
}
