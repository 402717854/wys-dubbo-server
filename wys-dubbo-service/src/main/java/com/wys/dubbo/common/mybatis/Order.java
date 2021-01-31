package com.wys.dubbo.common.mybatis;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * @Author: 86133
 * @Date: 2021/01/25
 */
public class Order implements Serializable {
    public enum Direction {
        asc, desc;

        public static Direction fromString(String value) {
            return valueOf(value.toLowerCase());
      }
  }

    private static final long serialVersionUID = -3078342809727773232L;
    private static final Direction DEFAULT_OD_DRC = Direction.desc;
    private String property;
    private Direction direction = DEFAULT_OD_DRC;

    public Order() {
  }

    public Order(String property, Direction direction) {
        this.property = property;
        this.direction = direction;
  }

    public static Order asc(String property) {
        return new Order(property, Direction.asc);
  }

    public static Order desc(String property) {
        return new Order(property, Direction.desc);
  }

    public String getProperty() {
        return this.property;
  }

    public void setProperty(String property) {
        this.property = property;
  }

    public Direction getDirection() {
        return this.direction;
  }

    public void setDirection(Direction direction) {
        this.direction = direction;
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
        Order localOrder = (Order) obj;
        return new EqualsBuilder()
                .append(getProperty(), localOrder.getProperty())
                .append(getDirection(), localOrder.getDirection()).isEquals();
  }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getProperty())
                .append(getDirection()).toHashCode();
  }
}
