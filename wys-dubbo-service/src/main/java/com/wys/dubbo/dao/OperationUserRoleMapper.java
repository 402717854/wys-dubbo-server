package com.wys.dubbo.dao;

import com.wys.dubbo.common.mybatis.IMBaseDAO;
import com.wys.dubbo.model.OperationUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 管理员与角色对应关系 Mapper 接口
 * </p>
 * @author lizy
 * @since 2020-12-14
 */
public interface OperationUserRoleMapper extends IMBaseDAO<OperationUserRole, Integer> {

    /**
     * 根据管理员Id查询所拥有的角色
     */
    List<Integer> selectUserRoleByUserId(@Param("userId") Integer userId);

    /**
     * 根据管理员Id查询可用角色
     */
    List<Integer> selectUserValidRoleByUserId(@Param("userId") Integer userId);

    /**
     * 根据角色Id查询所有管理员Id
     */
    List<Integer> selectUserRoleByRoleId(@Param("roleId") Integer roleId);

    /**根据用户Id删除对应的关系*/
    Integer deleteBatchByUserId(List<Integer> userId);

}
