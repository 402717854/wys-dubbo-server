package com.wys.dubbo.common.mybatis;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author: 86133
 * @Date: 2021/01/25
 */
public interface IMBaseDAO<T, PK extends Serializable>  {

    T selectPk(@Param("pk") PK pk);

    List<T> selectPks(List<PK> pks);

    T selectOne(Map param);

    List<T> select(Map param);

    com.github.pagehelper.Page<T> selectPage(Map param);

    /**
     * 新增实体
     * @param entity
     * @return 影响记录条数
     */
    int insert(T entity);

    /**
     * 批量插入
     * @param list
     */
    int insertBatch(final List<T> list);

    /**
     * 修改一个实体对象（UPDATE一条记录）
     * @param entity 实体对象
     * @return 修改的对象个数，正常情况=1
     */
    int update(T entity);

    /**
     * 批量修改
     * @param list
     */
    int updateBatch(final List<T> list);

    /**
     * 按主键删除记录
     * @param primaryKey 主键对象
     * @return 删除的对象个数，正常情况=1
     */
    int delete(@Param("pk") PK primaryKey);

    /**
     * 批量删除
     * @param list
     */
    int deleteBatch(final List<PK> list);

    /**
     * 查询整表总记录数
     * @return 整表总记录数
     */
    int count();

    /**
     * 查询符合条件的记录数
     * @param param 查询条件参数，包括WHERE条件（其他参数内容不起作用）。此参数设置为null，则相当于count()
     * @return
     */
    int count(Map param);

}
