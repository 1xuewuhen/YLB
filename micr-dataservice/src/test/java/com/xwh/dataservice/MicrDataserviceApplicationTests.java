package com.xwh.dataservice;

import com.xwh.api.pojo.BaseInfo;
import com.xwh.api.service.PlatBaseInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MicrDataserviceApplicationTests {

    @Autowired
    private PlatBaseInfoService platBaseInfoService;

    @Test
    void contextLoads() {
        BaseInfo baseInfo = platBaseInfoService.queryPlatBaseInfo();

        System.out.println(baseInfo);
    }

}
