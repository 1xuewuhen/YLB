package com.xwh.api.service;

import com.xwh.api.pojo.BidInfoProduct;

import java.math.BigDecimal;
import java.util.List;

/**
 * 作者:陈方银
 * 时间:2023/6/27
 */
public interface InvestService {

    // 查询某个产品的投注记录
    List<BidInfoProduct> queryBidListByProductId(Integer productId, Integer pageNo, Integer pageSize);

    // 投资理财产品
    void investProduct(Integer uid, Integer productId, BigDecimal money);
}
