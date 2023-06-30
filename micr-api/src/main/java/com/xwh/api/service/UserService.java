package com.xwh.api.service;

import com.xwh.api.model.User;

/**
 * 作者:陈方银
 * 时间:2023/6/28
 */
public interface UserService {

    // 根据手机号查询数据
    User queryByPhone(String phone);

    // 根据邮箱查询数据
    User queryByEmail(String email);
}
