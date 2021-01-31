package com.wys.dubbo.common.mybatis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 86133
 * @Date: 2021/01/25
 */
public class CommonPage<T> implements Serializable {
    private static final long serialVersionUID = -2053800594583879853L;
    private final List<T> content = new ArrayList();
    private final long total;
    private final Pageable pageable;

    public CommonPage() {
        this.total = 0L;
        this.pageable = new Pageable();
 }

    public CommonPage(List<T> content, long total, Pageable pageable) {
        this.content.addAll(content);
        this.total = total;
        this.pageable = pageable;
 }

    public int getPageNumber() {
        return this.pageable.getPageNumber();
 }

    public int getPageSize() {
        return this.pageable.getPageSize();
 }

    public List<Order> getOrders() {
        return this.pageable.getOrders();
 }

    public List<Filter> getFilters() {
        return this.pageable.getFilters();
 }

    public int getTotalPages() {
        return (int) Math.ceil((double)getTotal() / (double)getPageSize());
 }

    public List<T> getContent() {
        return this.content;
 }

    public long getTotal() {
        return this.total;
 }

    public Pageable getPageable() {
        return this.pageable;
 }
}