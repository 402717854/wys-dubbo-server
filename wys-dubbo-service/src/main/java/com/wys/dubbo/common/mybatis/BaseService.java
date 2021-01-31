package com.wys.dubbo.common.mybatis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author: 86133
 * @Date: 2021/01/25
 */
public interface BaseService<T, PK extends Serializable> {
    /**
     *
     * @Description: 新增SysResource
     * @return int    返回类型  条数
     * @Date: 2021/01/25
     * @Author: 86133
     */
    int add(T bean);

    /**
     *
     * @Description: 批量新增SysResource
     * @return void    返回类型
     * @Date: 2021/01/25
     * @Author: 86133
     */
    void add(List<T> list);

    /**
     *
     * @Description: 编辑SysResource
     * @return int    返回类型 更新条数
     * @Date: 2021/01/25
     * @Author: 86133
     */
    int update(T bean);

    /**
     *
     * @Description: 批量修改SysResource
     * @param list    返回类型
     * @Date: 2021/01/25
     * @Author: 86133
     */
    void update(List<T> list);

    /**
     *
     * @Description: 根据id删除SysResource
     * @param pk
     * @return int    返回类型  删除条数
     * @Date: 2021/01/25
     * @Author: 86133
     */
    int deleteByPk(PK pk);

    /**
     *
     * @Description: 根据id批量删除SysResource
     * @param pks
     * @return void    返回类型
     * @Date: 2021/01/25
     * @Author: 86133
     */
    void deleteByPks(PK[] pks);


    /**
     *
     * @Description: 根据条件查询一条结果
     * @param param
     * @return SysResource    返回类型  实体
     * @Date: 2021/01/25
     * @Author: 86133
     */
    T findUnique(Map<String, Object> param);

    /**
     *
     * @Description: 根据id查询
     * @param pk
     * @return SysResource    返回类型  实体
     * @Date: 2021/01/25
     * @Author: 86133
     */
    T get(PK pk);


    /**
     *
     * @Description: 根据id集合查询
     * @param pks
     * @return List<SysResource>   返回类型  实体
     * @Date: 2021/01/25
     * @Author: 86133
     */
    List<T> get(PK[] pks);

    /**
     *
     * @Description: 分页查询
     * @return List<SysResource>    返回类型  实体list
     * @Date: 2021/01/25
     * @Author: 86133
     */
    <T> CommonPage<T> selectPage(Map<String, Object> param, int pageNum, int pageSize);

    /**
     *
     * @Description: 根据参数查询
     * @param param
     * @return List<SysResource>    返回类型  实体list
     * @Date: 2021/01/25
     * @Author: 86133
     */
    List<T> selectByMap(Map<String, Object> param);
}