package com.xwh.front.controller;

import com.xwh.common.constants.RedisKey;
import com.xwh.common.enums.ERRORCode;
import com.xwh.common.exception.InfoException;
import com.xwh.common.util.CommonUtil;
import com.xwh.front.view.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // 发送邮件
    @ApiOperation(value = "发送验证码", notes = "通过邮箱发送验证")
    @GetMapping("/email/code/register")
    public R sendEmail(@RequestParam("email") String email) throws Exception {
        if (!CommonUtil.checkEmail(email)) {
            throw new InfoException(CommonUtil.generateJSON(ERRORCode.EMAIL_CHECK_ERROR.getCode(), ERRORCode.EMAIL_CHECK_ERROR.getMessage()));
        }
        String key = RedisKey.KEY_EMAIL_CODE_REG + email;
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
            throw new InfoException(CommonUtil.generateJSON(ERRORCode.EMAIL_CODE_CAN_USE.getCode(), ERRORCode.EMAIL_CODE_CAN_USE.getMessage()));
        }
        infoService.sendEmail(email);
        return R.ok();
    }

}
