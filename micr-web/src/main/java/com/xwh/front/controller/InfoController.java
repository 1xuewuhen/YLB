package com.xwh.front.controller;

import com.xwh.common.constants.RedisKey;
import com.xwh.common.constants.YLBConstant;
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

import javax.servlet.http.HttpServletRequest;

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
    @GetMapping(value = {"/email/code/register", "/email/code/login"})
    public R sendEmail(HttpServletRequest request, @RequestParam("email") String email) throws Exception {
        if (!CommonUtil.checkEmail(email)) {
            throw new InfoException(CommonUtil.generateJSON(ERRORCode.EMAIL_CHECK_ERROR.getCode(), ERRORCode.EMAIL_CHECK_ERROR.getMessage()));
        }
        String key = "";
        if ("/api/v1/email/code/login".equals(request.getRequestURI())) {
            key = RedisKey.KEY_EMAIL_CODE_LOGIN + email;
            if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
                throw new InfoException(CommonUtil.generateJSON(ERRORCode.EMAIL_CODE_CAN_USE.getCode(), ERRORCode.EMAIL_CODE_CAN_USE.getMessage()));
            }
            infoService.sendEmail(email, YLBConstant.USER_LOGIN);
        } else if ("/api/v1/email/code/register".equals(request.getRequestURI())) {
            key = RedisKey.KEY_EMAIL_CODE_REG + email;
            if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
                throw new InfoException(CommonUtil.generateJSON(ERRORCode.EMAIL_CODE_CAN_USE.getCode(), ERRORCode.EMAIL_CODE_CAN_USE.getMessage()));
            }
            infoService.sendEmail(email,YLBConstant.USER_REGISTER);
        }
        return R.ok();
    }

}
