package com.xwh.dataservice.service;

import com.alibaba.fastjson.JSON;
import com.xwh.api.model.User;
import com.xwh.api.service.UserService;
import com.xwh.common.constants.RedisKey;
import com.xwh.common.exception.InfoException;
import com.xwh.common.util.CommonUtil;
import com.xwh.dataservice.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
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

    @Override
    public User queryByEmail(String email) {
        User user = null;
        if (CommonUtil.checkEmail(email)) {
            user = JSON.parseObject(stringRedisTemplate.opsForValue().get(RedisKey.KEY_EMAIL_USER + email), User.class);
            if (Objects.isNull(user)){
                user = userMapper.selectByEmail(email);
                if (Objects.nonNull(user)) {
                    stringRedisTemplate.opsForValue().set(RedisKey.KEY_EMAIL_USER + email, JSON.toJSONString(user), 10L, TimeUnit.MINUTES);
                }
            }
        }
        return user;
    }
}
