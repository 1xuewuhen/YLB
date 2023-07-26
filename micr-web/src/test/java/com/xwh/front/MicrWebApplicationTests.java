package com.xwh.front;

import com.xwh.api.service.alipay.AlipayOrderSn;
import com.xwh.front.model.User;
import com.xwh.front.myFramework.XFramework;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class MicrWebApplicationTests {

    @Resource
    AlipayOrderSn alipayOrderSn;

    @Test
    public void test() {
        XFramework xFramework = new XFramework();
        User user = xFramework.getBean(User.class);
        System.out.println(user);
    }

    @Test
    void test02() {
        Map<String,String> map = new HashMap<>();
        map.put("uid","1");
        map.put("recharge_no","201708181755346741");
        map.put("recharge_money","222");
        map.put("recharge_desc","使用支付宝充值");
        map.put("channel","alipay");
        alipayOrderSn.addRecharge(map);
    }
}
