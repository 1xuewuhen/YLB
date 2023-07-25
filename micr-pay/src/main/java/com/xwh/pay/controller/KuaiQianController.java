package com.xwh.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author 血无痕
 * @date 2023/7/25
 * @since 1.0
 */

@RequestMapping("/kq")
@Controller
public class KuaiQianController {

    @GetMapping("/rece/recharge")
    public String receFrontRechargeKq(Integer uid, BigDecimal rechargeMoney) {
        String view = "err";
        if (Objects.nonNull(uid) && Objects.nonNull(rechargeMoney) && rechargeMoney.compareTo(new BigDecimal("100")) > 0) {

        }
        return view;
    }
    @ResponseBody
    @GetMapping("/xxx")
    public String xx(){
        return "xxxxxxxx";
    }
}
