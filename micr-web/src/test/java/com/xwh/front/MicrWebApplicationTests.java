package com.xwh.front;

import com.xwh.common.util.CommonUtil;
import com.xwh.front.service.AliyunRealNameService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MicrWebApplicationTests {

    @Resource
    private AliyunRealNameService aliyunRealNameService;

    @Test
    void contextLoads() {
        aliyunRealNameService.realName();
    }

}
