package com.xwh.dataservice.service;

import com.xwh.api.pojo.BaseInfo;
import com.xwh.api.service.PlatBaseInfoService;
import com.xwh.dataservice.mapper.BidInfoMapper;
import com.xwh.dataservice.mapper.ProductInfoMapper;
import com.xwh.dataservice.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author 陈方银
 * @date 2023/6/22
 * @since 1.0
 */

@DubboService(interfaceClass = PlatBaseInfoService.class, version = "1.0")
public class PlatBaseInfoServiceImpl implements PlatBaseInfoService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private ProductInfoMapper productInfoMapper;
    @Resource
    private BidInfoMapper bidInfoMapper;

    /**
     * @description: 平台基本信息
     * @author 陈方银
     * @date 2023/6/22 9:33
     * @version 1.0
     */
    @Override
    public BaseInfo queryPlatBaseInfo() {
        // 获取注册人数，收益平均值，累计成交金额
        Integer registerUser = userMapper.selectCountUser();
        BigDecimal avgRate = productInfoMapper.selectAvgRate();
        BigDecimal subBidMoney = bidInfoMapper.selectSubBidMoney();
        return BaseInfo.builder().sumBigMoney(subBidMoney).registerUsers(registerUser).historyAvgRate(avgRate).build();
    }
}
