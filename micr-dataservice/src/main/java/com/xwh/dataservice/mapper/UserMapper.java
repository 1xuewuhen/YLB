package com.xwh.dataservice.mapper;

import com.xwh.api.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    // 统计注册人数
    Integer selectCountUser();

    // 根据手机号查询数据
    User selectByPhone(@Param("phone") String phone);
}