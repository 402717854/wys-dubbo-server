package com.wys.dubbo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 86133
 * @Date: 2021/01/25
 * @Description:: mybatis config
 */
@Configuration
@MapperScan("com.wys.dubbo.dao")
public class MybatisConfig {

}
