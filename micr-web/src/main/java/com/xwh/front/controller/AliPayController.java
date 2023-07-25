package com.xwh.front.controller;

import com.alipay.api.AlipayApiException;
import com.xwh.front.config.AlipayTemplate;
import com.xwh.front.vo.PayVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author 血无痕
 * @date 2023/7/25
 * @since 1.0
 */

@Api(tags = "支付功能")
@Controller
@RequestMapping("/v1")
public class AliPayController {

    @Resource
    private AlipayTemplate alipayTemplate;

    /**
     * @description: 通过支付宝充值金额
     * @param: uid
     * @param: money
     * @author 血无痕
     * @date 2023/7/25 21:10
     * @version 1.0
     */
    @ApiOperation(value = "充值金额",notes = "通过支付宝充值金额")
    @ResponseBody
    @GetMapping(value = "/pay/recharge", produces = "text/html")
    public String recharge(@RequestParam("money") BigDecimal money) throws AlipayApiException {
        PayVo payVo = PayVo.builder()
                .out_trade_no(UUID.randomUUID().toString().replaceAll("-", ""))
                .subject("充值账户")
                .body("充钱")
                .total_amount(money.toString())
                .build();
        return alipayTemplate.pay(payVo);
    }
}
