package com.wys.dubbo.common.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 86133
 * @Date: 2021/01/25
 */
public abstract class BaseServiceImpl<T, PK extends Serializable, Mapper extends IMBaseDAO> implements BaseService<T, PK> {

    @Autowired
    protected Mapper mapper;

    /**
     * @return int    返回类型  条数
     * @Description: 新增T
     * @Date: 2021/01/25
     * @Author: 86133
     */
    @Override
    @Transactional
    public int add(T bean) {
        return this.mapper.insert(bean);
 }

    /**
     * @return void    返回类型
     * @Description: 批量新增T
     * @Date: 2021/01/25
     * @Author: 86133
     */
    @Override
    @Transactional
    public void add(List<T> list) {
        this.mapper.insertBatch(list);
 }


    /**
     * @return int    返回类型 更新条数
     * @Description: 编辑T
     * @Date: 2021/01/25
     * @Author: 86133
     */
    @Override
    @Transactional
    public int update(T bean) {
        return this.mapper.update(bean);
 }

    /**
     * @param list 返回类型
     * @Description: 批量修改T
     * @Date: 2021/01/25
     * @Author: 86133
     */
    @Override
    @Transactional
    public void update(List<T> list) {
        this.mapper.updateBatch(list);
 }

    /**
     * @param pk
     * @return int    返回类型  删除条数
     * @Description: 根据id删除T
     * @Date: 2021/01/25
     * @Author: 86133
     */
    @Override
    @Transactional
    public int deleteByPk(PK pk) {
        return this.mapper.delete(pk);
 }

    /**
     * @param pks
     * @return void    返回类型
     * @Description: 根据id批量删除T
     * @Date: 2021/01/25
     * @Author: 86133
     */
    @Override
    @Transactional
    public void deleteByPks(PK[] pks) {
        this.mapper.deleteBatch(Arrays.asList(pks));
 }

    /**
     * @param param
     * @return void    返回类型  实体
     * @Description: 根据条件查询一条结果
     * @Date: 2021/01/25
     * @Author: 86133
     */
    @Override
    public T findUnique(Map<String, Object> param) {
        return (T) this.mapper.selectOne(param);
 }

    /**
     * @param pk
     * @return void    返回类型  实体
     * @Description: 根据id查询
     * @Date: 2021/01/25
     * @Author: 86133
     */
    @Override
    public T get(PK pk) {
        return (T) this.mapper.selectPk(pk);
 }

    /**
     * @param pks
     * @return void    返回类型  实体
     * @Description: 根据id查询
     * @Date: 2021/01/25
     * @Author: 86133
     */
    @Override
    public List<T> get(PK[] pks) {
        return this.mapper.selectPks(Arrays.asList(pks));
 }

    /**
     * @return List<T>    返回类型  实体list
     * @Description: 分页查询
     * @Date: 2021/01/25
     * @Author: 86133
     */
    @Override
    public <T> CommonPage<T> selectPage(Map<String, Object> param, int pageNum, int pageSize) {
        Pageable pageable = new Pageable(pageNum, pageSize);
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        Page<T> list = this.mapper.selectPage(param);
        CommonPage commonPage = new CommonPage(list, list.getTotal(), pageable);
        return commonPage;
 }

    /**
     * @return List<T>    返回类型  实体list
     * @Description: 根据参数查询
     * @Date: 2021/01/25
     * @Author: 86133
     */
    @Override
    public List<T> selectByMap(Map<String, Object> param) {
        return this.mapper.select(param);
 }

    public List<T> selectByProperty(String property, Object value) {
        HashMap param = new HashMap(2, 1.0F);
        return this.mapper.select(param);
 }

    public T selectOneByProperty(String property, Object value) {
        HashMap param = new HashMap(2, 1.0F);
        return (T) this.mapper.selectOne(param);
 }
}

