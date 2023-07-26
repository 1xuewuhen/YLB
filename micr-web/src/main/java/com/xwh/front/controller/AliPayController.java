package com.xwh.front.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.xwh.api.model.User;
import com.xwh.front.config.AlipayTemplate;
import com.xwh.front.vo.PayVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * @author 血无痕
 * @date 2023/7/25
 * @since 1.0
 */

@Api(tags = "支付功能")
@Controller
@RequestMapping("/v1")
public class AliPayController extends BaseController{

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
    public String recharge(@RequestParam("uid")Integer uid, @RequestParam("money") BigDecimal money) throws AlipayApiException {
        User user = userService.queryById(uid);
        if (Objects.nonNull(user)){
            PayVo payVo = PayVo.builder()
                    .out_trade_no(UUID.randomUUID().toString().replaceAll("-", ""))
                    .subject("充值账户")
                    .body(uid.toString())
                    .total_amount(money.toString())
                    .build();
            return alipayTemplate.pay(payVo);
        }
        return "redirect:http://127.0.0.1:5173";
    }
}
