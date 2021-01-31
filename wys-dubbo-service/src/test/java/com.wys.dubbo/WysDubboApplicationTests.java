package com.wys.dubbo;

import com.alibaba.fastjson.JSON;
import com.wys.dubbo.model.OperationUser;
import com.wys.dubbo.service.OperationUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WysDubboApplicationTests {

    @Autowired
    OperationUserService userService;

    @Test
    public void contextLoads() {
        OperationUser operationUser = userService.get(1);
        System.out.println(JSON.toJSON(operationUser));
    }

}
