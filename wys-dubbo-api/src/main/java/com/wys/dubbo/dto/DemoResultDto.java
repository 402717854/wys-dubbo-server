package com.wys.dubbo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: dubbo demo
 * @Date: 2021/01/25
 * @Description:: 异常捕获
 */
@Data
public class DemoResultDto implements Serializable {
    private static final long serialVersionUID = -2291930078923610406L;
    /**
     * id
     */
    private Integer id;

    /**
     * description
     */
    private String description;
}
