package com.xwh.api.service;

import com.xwh.api.model.User;
import com.xwh.api.pojo.UserAccountInfo;
import com.xwh.api.vo.RealNameVo;
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

    // 用户登录
    User userLogin(UserRegister userRegister);

    // 更新实名认证信息
    void modifyRealName(RealNameVo realNameVo);

    // 获取用户资金信息
    UserAccountInfo queryUserAllInfo(Integer uid);

    // 查询用户
    User queryById(Integer uid);
}
