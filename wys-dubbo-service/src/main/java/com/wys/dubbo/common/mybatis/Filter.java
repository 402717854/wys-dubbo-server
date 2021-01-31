package com.wys.dubbo.common.mybatis;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 86133
 * @Date: 2021/01/25
 */
public class Filter implements Serializable {
    public enum Operator {
        eq, ne, gt, lt, ge, le, like, in, isNull, isNotNull;

        public static Operator fromString(String value) {
            return valueOf(value.toLowerCase());
      }
  }

    public enum Link {
        or, and;

        public static Link fromString(String value) {
            return valueOf(value.toLowerCase());
      }
  }

    private static final long serialVersionUID = -8712382358441065075L;
    private static final boolean DEFAULT_IGNORE_CASE = false;
    private String property;
    private Operator operator;
    private Object value;
    private Boolean ignoreCase = Boolean.valueOf(false);

    private List<Filter> groupFilter = null;

    public Filter() {
  }

    public Filter(String property, Operator operator, Object value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
  }

    public Filter(String property, Operator operator, Object value, List<Filter> groupFilter) {
        this.property = property;
        this.operator = operator;
        this.value = value;
        this.groupFilter = groupFilter;
  }

    public Filter(String property, Operator operator, Object value,
                  boolean ignoreCase) {
        this.property = property;
        this.operator = operator;
        this.value = value;
        this.ignoreCase = Boolean.valueOf(ignoreCase);
  }

    public Filter(String property, Operator operator, Object value,
                  boolean ignoreCase, List<Filter> groupFilter) {
        this.property = property;
        this.operator = operator;
        this.value = value;
        this.ignoreCase = Boolean.valueOf(ignoreCase);
        this.groupFilter = groupFilter;
  }

    public static Filter eq(String property, Object value) {
        return new Filter(property, Operator.eq, value);
  }

    public static Filter eq(String property, Object value, boolean ignoreCase) {
        return new Filter(property, Operator.eq, value, ignoreCase);
  }

    public static Filter ne(String property, Object value) {
        return new Filter(property, Operator.ne, value);
  }

    public static Filter ne(String property, Object value, boolean ignoreCase) {
        return new Filter(property, Operator.ne, value, ignoreCase);
  }

    public static Filter gt(String property, Object value) {
        return new Filter(property, Operator.gt, value);
  }

    public static Filter lt(String property, Object value) {
        return new Filter(property, Operator.lt, value);
  }

    public static Filter ge(String property, Object value) {
        return new Filter(property, Operator.ge, value);
  }

    public static Filter le(String property, Object value) {
        return new Filter(property, Operator.le, value);
  }

    public static Filter like(String property, Object value) {
        return new Filter(property, Operator.like, value);
  }

    public static Filter in(String property, Object value) {
        return new Filter(property, Operator.in, value);
  }

    public static Filter isNull(String property) {
        return new Filter(property, Operator.isNull, null);
  }

    public static Filter isNotNull(String property) {
        return new Filter(property, Operator.isNotNull, null);
  }

    public Filter ignoreCase() {
        this.ignoreCase = Boolean.valueOf(!DEFAULT_IGNORE_CASE);
        return this;
  }

    public String getProperty() {
        return this.property;
  }

    public void setProperty(String property) {
        this.property = property;
  }

    public Operator getOperator() {
        return this.operator;
  }

    public void setOperator(Operator operator) {
        this.operator = operator;
  }

    public Object getValue() {
        return this.value;
  }

    public void setValue(Object value) {
        this.value = value;
  }

    public Boolean getIgnoreCase() {
        return this.ignoreCase;
  }

    public void setIgnoreCase(Boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
  }

    public List<Filter> getGroupFilter() {
        return groupFilter;
  }

    public void setGroupFilter(List<Filter> groupFilter) {
        this.groupFilter = groupFilter;
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
        Filter localFilter = (Filter) obj;
        return new EqualsBuilder()
                .append(getProperty(), localFilter.getProperty())
                .append(getOperator(), localFilter.getOperator())
                .append(getValue(), localFilter.getValue()).isEquals();
  }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getProperty())
                .append(getOperator()).append(getValue()).toHashCode();
  }
}
