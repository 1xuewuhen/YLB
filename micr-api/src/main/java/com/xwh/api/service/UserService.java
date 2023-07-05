package com.xwh.api.service;

import com.xwh.api.model.User;
import com.xwh.api.vo.UserRegister;

/**
 * 作者:陈方银
 * 时间:2023/6/28
 */
public interface UserService {

    // 根据手机号查询数据
    User queryByPhone(String phone);


    User queryByEmail(String email);

    // 用户注册
    void userRegister(UserRegister u);
}
