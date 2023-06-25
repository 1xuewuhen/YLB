package com.xwh.front.controller;

import com.xwh.api.pojo.BaseInfo;
import com.xwh.front.view.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈方银
 * @date 2023/6/22
 * @since 1.0
 */
@RequestMapping("/v1")
@RestController
//@CrossOrigin
@Api(tags = "平台信息功能")
public class PlatInfoController extends BaseController {

    /**
     * @description: 平台基本信息
     * @author 陈方银
     * @date 2023/6/22 9:56
     * @version 1.0
     */
    @ApiOperation(value = "平台三项基本信息",notes = "注册人数，收益平均值，累计成交金额")
    @GetMapping("/plat/info")
    public R queryPlatBaseInfo() {
        BaseInfo baseInfo = platBaseInfoService.queryPlatBaseInfo();
        return R.ok().setData(baseInfo);
    }
}
