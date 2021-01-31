package com.wys.dubbo.dto.request;

import lombok.Data;

@Data
public class PageReq extends BasicRequest {

    private static final long serialVersionUID = -6941815576542187946L;
    //当前页最大的id
    private Integer maxId = 0 ;
    //当前页
    private Integer pageSize = 0;
    //每页显示数据
    private Integer limit = 10;


    public int getPage() {
        Integer tmp = pageSize * limit;
        return tmp < 0 ? 0 : tmp;
    }

}
