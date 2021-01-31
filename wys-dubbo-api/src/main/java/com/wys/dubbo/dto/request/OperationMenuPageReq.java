package com.wys.dubbo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Classname OperationMenuPageReq
 * @Description 查询菜单列表入参
 * @Date 2020/12/15 16:16
 * @Created by 20113370
 */
@Data
public class OperationMenuPageReq extends PageReq {

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTimeStart;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTimeEnd;

    private Integer state;

    private String searchKey;
}
