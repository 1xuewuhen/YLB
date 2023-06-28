package com.xwh.front.controller;

import com.xwh.api.service.InvestService;
import com.xwh.api.service.PlatBaseInfoService;
import com.xwh.api.service.ProductService;
import com.xwh.api.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @author 陈方银
 * @date 2023/6/22
 * @since 1.0
 */
//@CrossOrigin
public class BaseController {

    // 平台信息服务
    @DubboReference(interfaceClass = PlatBaseInfoService.class, version = "1.0")
    protected PlatBaseInfoService platBaseInfoService;

    // 产品服务
    @DubboReference(interfaceClass = ProductService.class, version = "1.0")
    protected ProductService productService;

    @Resource
    protected StringRedisTemplate stringRedisTemplate;

    //投资服务
    @DubboReference(interfaceClass = InvestService.class, version = "1.0")
    protected InvestService investService;

    // 用户服务
    @DubboReference(interfaceClass = UserService.class,version = "1.0")
    protected UserService userService;
}
