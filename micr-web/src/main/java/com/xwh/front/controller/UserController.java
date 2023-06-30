package com.xwh.front.controller;

import com.alibaba.fastjson.JSONObject;
import com.xwh.api.model.User;
import com.xwh.common.enums.ERRORCode;
import com.xwh.common.enums.RCode;
import com.xwh.common.exception.InfoException;
import com.xwh.common.util.CommonUtil;
import com.xwh.front.view.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 作者:陈方银
 * 时间:2023/6/28
 */

@Api(tags = "用户功能")
@RestController
@RequestMapping("/v1/user")
public class UserController extends BaseController {


    @ApiOperation(value = "手机号是否注册", notes = "在注册功能中手机号是否可以注册")
    @ApiImplicitParam(name = "phone", value = "手机号", required = true)
    @GetMapping("/phone/exists")
    public R phoneExists(@RequestParam("phone") String phone) {
        R r = R.builder().code(RCode.PHONE_EXISTS.getCode()).msg(RCode.PHONE_EXISTS.getMessage()).build();
        // 检查参数是否符合要求
        if (CommonUtil.checkPhone(phone)) {
            User user = userService.queryByPhone(phone);
            if (Objects.isNull(user)) {
                r = R.ok().setMsg("可以注册");
            }
            // TODO 把查询到的数据放入redis中
        } else {
            r.setCode(RCode.PHONE_FORMAT_ERROR.getCode()).setMsg(RCode.PHONE_FORMAT_ERROR.getMessage());
        }
        return r;
    }


    @ApiOperation(value = "邮箱是否注册", notes = "在注册功能中邮箱是否可以注册")
    @GetMapping("/email/exists")
    public R emailExists(@RequestParam("email") String email) throws InfoException {
        if (CommonUtil.checkEmail(email)) {
            User user = userService.queryByEmail(email);
            if (Objects.isNull(user)) {
                return R.ok().setMsg("可以注册");
            }
            throw new InfoException(CommonUtil.generateJSON(RCode.EMAIL_EXISTS.getCode(), RCode.EMAIL_EXISTS.getMessage()));
        }
        throw new InfoException(CommonUtil.generateJSON(ERRORCode.EMAIL_CHECK_ERROR.getCode(), ERRORCode.EMAIL_CHECK_ERROR.getMessage()));
    }
}
