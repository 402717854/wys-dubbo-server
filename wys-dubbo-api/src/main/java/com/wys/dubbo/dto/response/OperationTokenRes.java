package com.wys.dubbo.dto.response;

import lombok.Data;

/**
 * @Classname OperationTokenRes
 * @Description 用户登录后返回的token数据
 * @Date 2020/12/10 17:07
 * @Created by 20113370
 */
@Data
public class OperationTokenRes extends BasicResponse {
    private static final long serialVersionUID = 7559651433147894040L;
    /**
     * 用户token
     */
    private String accessToken;

    /**
     * 登录用户名
     */
    private String loginName;

    private Integer id;
}
