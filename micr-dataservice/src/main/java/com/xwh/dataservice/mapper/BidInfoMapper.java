package com.xwh.dataservice.mapper;

import com.xwh.api.model.BidInfo;
import com.xwh.api.pojo.BidInfoProduct;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BidInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BidInfo record);

    int insertSelective(BidInfo record);

    BidInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BidInfo record);

    int updateByPrimaryKey(BidInfo record);

    // 累计成交金额
    BigDecimal selectSubBidMoney();

    // 查询某个产品的投注记录
    List<BidInfoProduct> selectByProductId(@Param("productId") Integer productId, @Param("offset") Integer offset, @Param("rows") Integer rows);

    // 充值记录
    List<BidInfo> selectByUid(@Param("uid") Integer uid, @Param("offset") int offset, @Param("rows") Integer rows);

    // 某个产品的投资记录
    List<BidInfo> selectByProdId(@Param("productId") Integer productId, @Param("status") Integer status);
}