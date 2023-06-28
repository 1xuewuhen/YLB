package com.xwh.dataservice.service;

import com.alibaba.fastjson.JSON;
import com.xwh.api.model.User;
import com.xwh.api.service.UserService;
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
            user = JSON.parseObject(stringRedisTemplate.opsForValue().get(phone), User.class);
            if (Objects.isNull(user)) {
                user = userMapper.selectByPhone(phone);
                if (Objects.nonNull(user)){
                    stringRedisTemplate.opsForValue().set(phone, JSON.toJSONString(user),5L, TimeUnit.MINUTES);
                }
            }
        }
        return user;
    }
}
