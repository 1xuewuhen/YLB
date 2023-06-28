package com.xwh.front;

import com.xwh.common.util.CommonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MicrWebApplicationTests {

    @Test
    void contextLoads() {
        boolean b = CommonUtil.checkPhone("15998958393888");
        System.out.println(b);
    }

}
