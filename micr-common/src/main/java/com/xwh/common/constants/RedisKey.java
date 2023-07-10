package com.xwh.common.constants;

/**
 * @author 陈方银
 * @date 2023/6/22
 * @since 1.0
 */
public interface RedisKey {
    // 投资排行榜
    String KEY_INVEST_RANK = "INVEST:RANK";
    String KEY_PHONE_USER = "PHONE:USER:";
    String KEY_EMAIL_USER = "EMAIL:USER:";

    //注册时邮箱验证码
    String KEY_EMAIL_CODE_REG = "EMAIL:REG:";
    // 登录时邮箱验证码
    String KEY_EMAIL_CODE_LOGIN = "EMAIL:LOGIN:";
    // 认证使用的验证码
    String KEY_EMAIL_CODE_AUTHENTICATION = "EMAIL:AUTHENTICATION:";
}
