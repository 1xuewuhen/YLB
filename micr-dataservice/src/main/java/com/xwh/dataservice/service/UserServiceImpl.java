package com.xwh.dataservice.service;

import com.alibaba.fastjson.JSON;
import com.xwh.api.model.FinanceAccount;
import com.xwh.api.model.User;
import com.xwh.api.service.UserService;
import com.xwh.api.vo.UserRegister;
import com.xwh.common.constants.RedisKey;
import com.xwh.common.enums.RCode;
import com.xwh.common.exception.InfoException;
import com.xwh.common.util.CommonUtil;
import com.xwh.dataservice.mapper.FinanceAccountMapper;
import com.xwh.dataservice.mapper.UserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 作者:陈方银
 * 时间:2023/6/28
 */

@DubboService(interfaceClass = UserService.class, version = "1.0")
public class UserServiceImpl implements UserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserMapper userMapper;

    @Resource
    private FinanceAccountMapper financeAccountMapper;

    @Value("${ylb.config.password-salt}")
    private String passwordSalt;

    // 根据手机号查询数据
    @Override
    public User queryByPhone(String phone) {
        User user = null;
        if (CommonUtil.checkPhone(phone)) {
            user = JSON.parseObject(stringRedisTemplate.opsForValue().get(RedisKey.KEY_PHONE_USER + phone), User.class);
            if (Objects.isNull(user)) {
                user = userMapper.selectByPhone(phone);
                if (Objects.nonNull(user)) {
                    stringRedisTemplate.opsForValue().set(RedisKey.KEY_PHONE_USER + phone, JSON.toJSONString(user), 10L, TimeUnit.MINUTES);
                }
            }
        }
        return user;
    }

    // 根据邮箱查询数据
    @Override
    public User queryByEmail(String email) {
        User user = null;
        if (CommonUtil.checkEmail(email)) {
            user = JSON.parseObject(stringRedisTemplate.opsForValue().get(RedisKey.KEY_EMAIL_USER + email), User.class);
            if (Objects.isNull(user)) {
                user = userMapper.selectByEmail(email);
                if (Objects.nonNull(user)) {
                    stringRedisTemplate.opsForValue().set(RedisKey.KEY_EMAIL_USER + email, JSON.toJSONString(user), 10L, TimeUnit.MINUTES);
                }
            }
        }
        return user;
    }

    // 用户注册
    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized void userRegister(UserRegister userRegister) {
        // 封装数据
        if (Objects.isNull(userMapper.selectByEmail(userRegister.getEmail()))) {
            // 二次加密密码数据，给原始数据加盐(salt)
            String md5Hex = DigestUtils.md5Hex(userRegister.getPassword() + passwordSalt);
            User user = new User().setEmail(userRegister.getEmail()).setLoginPassword(md5Hex).setAddTime(new Date());
            userMapper.insertSelective(user);
            FinanceAccount account = new FinanceAccount().setUid(user.getId()).setAvailableMoney(new BigDecimal("0"));
            financeAccountMapper.insertSelective(account);
            return;
        }
        throw new InfoException(CommonUtil.generateJSON(RCode.EMAIL_EXISTS.getCode(), RCode.EMAIL_EXISTS.getMessage()));
    }
}
