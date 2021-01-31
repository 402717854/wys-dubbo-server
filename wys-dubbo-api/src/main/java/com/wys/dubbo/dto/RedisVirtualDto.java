package com.wys.dubbo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname RedisVirtualVO
 * @Description 虚拟禁用的数据
 * @Date 2020/12/18 11:14
 * @Created by 20113370
 */
@Data
public class RedisVirtualDto implements Serializable {
    private static final long serialVersionUID = 6742221688670574791L;
    /**
     * 主键Id
     */
    private Integer id;
    /**
     * 状态
     */
    private Integer state;

    public RedisVirtualDto() {
    }

    public RedisVirtualDto(Integer id) {
        this.id = id;
        this.state = 1;
    }
}
