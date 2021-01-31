package com.wys.dubbo.dao;

import com.wys.dubbo.common.mybatis.IMBaseDAO;
import com.wys.dubbo.dto.OperationRoleDto;
import com.wys.dubbo.dto.OperationRoleEasyCheckedDto;
import com.wys.dubbo.dto.request.OperationRolePageReq;
import com.wys.dubbo.dto.response.OperationRolePageRes;
import com.wys.dubbo.model.OperationRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: dao层 Interface:OperationRole
 * @Author: boris
 * @Date: 2020/12/08
 */
public interface OperationRoleMapper extends IMBaseDAO<OperationRole, Integer> {

    /**
     * 分页查询角色信息
     */
    List<OperationRoleDto> selectRolePage(@Param("roleReq") OperationRolePageReq request);

    /**
     * 查询最大id和总条数
     */
    OperationRolePageRes selectCountMaxRole(@Param("roleReq") OperationRolePageReq request);

    /**
     * 根据管理员Id查询所有角色
     */
    List<OperationRoleDto> selectUserRoleVOByUserId(@Param("userId") Integer userId);

    /**
     * 根据管理员Id查询所有角色这个将sql放到终端去执行会失败但在程序中是可以的,为了降低风险还是不用为好,应该是mybatis做了优化导致
     */
    List<OperationRoleEasyCheckedDto> selectRoleEasyAllVOByUserId(@Param("userId") Integer userId);

    /**
     * 查询全部可用角色
     */
    List<OperationRoleEasyCheckedDto> selectRoleEasyAllVO();


}