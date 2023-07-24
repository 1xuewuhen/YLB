package com.xwh.front;

import com.xwh.front.model.User;
import com.xwh.front.myFramework.XFramework;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MicrWebApplicationTests {

    @Test
    public void test(){
        XFramework xFramework = new XFramework();
        User user = xFramework.getBean(User.class);
        System.out.println(user);
    }


}
