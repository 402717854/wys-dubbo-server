package com.wys.dubbo.common.mybatis;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: 86133
 * @Date: 2021/01/25
 */
public class Pageable implements Serializable {
    private static final long serialVersionUID = -3930180379790344299L;
    private static final int DEFAULT_MIN_PAGE_SIZE = 1;
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_MAX_PAGE_SIZE = 1000;
    private int pageNumber = 1;
    private int pageSize = 20;
    private Map<String, Object> params;
    private List<Filter> filters = new ArrayList();
    private List<Order> orders = new ArrayList();

    public Pageable() {
  }

    public Pageable(Integer pageNumber, Integer pageSize) {
        if ((pageNumber != null) && (pageNumber.intValue() >= 1)) {
            this.pageNumber = pageNumber.intValue();
      }
        if ((pageSize != null) && (pageSize.intValue() >= 1)
                && (pageSize.intValue() <= 1000)) {
            this.pageSize = pageSize.intValue();
      }
  }

    public int getPageNumber() {
        return this.pageNumber;
  }

    public void setPageNumber(int pageNumber) {
        if (pageNumber < 1) {
            pageNumber = 1;
      }
        this.pageNumber = pageNumber;
  }

    public int getPageSize() {
        return this.pageSize;
  }

    public void setPageSize(int pageSize) {
        if ((pageSize < DEFAULT_MIN_PAGE_SIZE)
                || (pageSize > DEFAULT_MAX_PAGE_SIZE)) {
            pageSize = DEFAULT_PAGE_SIZE;
      }
        this.pageSize = pageSize;
  }

    public Map<String, Object> getParams() {
        return params;
  }

    public void setParams(Map<String, Object> params) {
        this.params = params;
  }

    public List<Filter> getFilters() {
        return this.filters;
  }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
  }

    public List<Order> getOrders() {
        return this.orders;
  }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
  }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
      }
        if (getClass() != obj.getClass()) {
            return false;
      }
        if (this == obj) {
            return true;
      }
        Pageable localPageable = (Pageable) obj;
        return new EqualsBuilder()
                .append(getPageNumber(), localPageable.getPageNumber())
                .append(getPageSize(), localPageable.getPageSize())
                .append(getFilters(), localPageable.getFilters())
                .append(getOrders(), localPageable.getOrders()).isEquals();
  }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getPageNumber())
                .append(getPageSize()).append(getFilters())
                .append(getOrders()).toHashCode();
  }
}
