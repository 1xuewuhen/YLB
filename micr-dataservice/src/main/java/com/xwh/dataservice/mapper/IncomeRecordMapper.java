package com.xwh.dataservice.mapper;

import com.xwh.api.model.IncomeRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IncomeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IncomeRecord record);

    int insertSelective(IncomeRecord record);

    IncomeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IncomeRecord record);

    int updateByPrimaryKey(IncomeRecord record);

    // 查询充值记录
    List<IncomeRecord> selectByUid(@Param("uid") Integer uid, @Param("offset") int offset, @Param("rows") Integer rows);
}