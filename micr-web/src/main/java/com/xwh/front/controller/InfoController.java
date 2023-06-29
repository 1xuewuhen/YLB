package com.xwh.front.controller;

import com.xwh.front.view.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈方银
 * @date 2023/6/29
 * @since 1.0
 */

@Api(tags = "信息服务")
@RestController
@RequestMapping("/v1")
public class InfoController extends BaseController {

    @GetMapping("/email/code/register")
    public R sendEmail(String email){
        infoService.sendEmail(email);
        return R.error();
    }

}
