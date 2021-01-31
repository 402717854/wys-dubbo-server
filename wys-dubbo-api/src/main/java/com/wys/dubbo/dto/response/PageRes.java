package com.wys.dubbo.dto.response;

import lombok.Data;

/**
 * @Classname PageRes
 * @Description 返回分页数据的头
 * @Date 2020/1/1 10:57
 * @Created by Lizy
 */
@Data
public class PageRes extends BasicResponse {
    /**
     * 记录最大ID
     */
    private Integer maxId;

    /**
     * 总记录数
     */
    private Integer total;
}
