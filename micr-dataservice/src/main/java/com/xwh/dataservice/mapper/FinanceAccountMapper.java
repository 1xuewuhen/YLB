package com.xwh.dataservice.mapper;

import com.xwh.api.model.FinanceAccount;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface FinanceAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FinanceAccount record);

    int insertSelective(FinanceAccount record);

    FinanceAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FinanceAccount record);

    int updateByPrimaryKey(FinanceAccount record);

    // 给uid的记录上锁
    FinanceAccount selectByUidUpdate(@Param("uid") Integer uid);

    // 扣除账户资金(更新)
    void updateAvailableMoneyByInvest(@Param("uid") Integer uid, @Param("money") BigDecimal money);

    // 把每个收益，进行返还，本金+利息
    void updateAvailableMoneyByIncome(@Param("uid") Integer uid, @Param("bidMoney") BigDecimal bidMoney, @Param("incomeMoney") BigDecimal incomeMoney);

    // 充值金额
    void addMoneyByUid(@Param("uid") String uid, @Param("money") BigDecimal money);
}