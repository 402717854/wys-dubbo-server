package com.wys.dubbo.dao;

import com.wys.dubbo.common.mybatis.IMBaseDAO;
import com.wys.dubbo.dto.OperationSysUserDto;
import com.wys.dubbo.dto.request.OperationSysUserPageReq;
import com.wys.dubbo.dto.response.OperationSysUserPageRes;
import com.wys.dubbo.model.OperationUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: dao层 Interface:OperationUser
 * @Author: boris
 * @Date: 2020/12/08
 */
public interface OperationUserMapper extends IMBaseDAO<OperationUser, Integer> {

    /**
     * 根据账号获取用户信息
     */
    OperationUser queryOperationUserByAccount(@Param("account") String account);

    /**
     * 获取最大Id和总数
     */
    OperationSysUserPageRes queryCountMax(@Param("pageReq") OperationSysUserPageReq request);

    /**
     * 获取管理员列表数据
     */
    List<OperationSysUserDto> selectSysUserPage(@Param("pageReq") OperationSysUserPageReq request);
}