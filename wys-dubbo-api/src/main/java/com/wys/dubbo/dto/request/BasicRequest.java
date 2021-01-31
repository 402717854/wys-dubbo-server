package com.wys.dubbo.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname BasicRequest
 * @Description 请求基本入参
 * @Date 2020/12/3 17:13
 * @Created by 20113370
 */
@Data
public class BasicRequest implements Serializable {

    private static final long serialVersionUID = 8092188716457482270L;
    //用户id    @ApiModelProperty(value="userId用户ID",name="userId",hidden=true)
    private Integer userId;


}
