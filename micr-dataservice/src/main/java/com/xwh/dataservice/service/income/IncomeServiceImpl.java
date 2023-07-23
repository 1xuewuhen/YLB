package com.xwh.dataservice.service.income;

import com.xwh.api.model.BidInfo;
import com.xwh.api.model.IncomeRecord;
import com.xwh.api.model.ProductInfo;
import com.xwh.api.service.income.IncomeService;
import com.xwh.common.constants.YLBConstant;
import com.xwh.dataservice.mapper.BidInfoMapper;
import com.xwh.dataservice.mapper.FinanceAccountMapper;
import com.xwh.dataservice.mapper.IncomeRecordMapper;
import com.xwh.dataservice.mapper.ProductInfoMapper;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author 血无痕
 * @date 2023/7/23
 * @since 1.0
 */

@DubboService(interfaceClass = IncomeService.class, version = "1.0")
public class IncomeServiceImpl implements IncomeService {

    @Resource
    private ProductInfoMapper productInfoMapper;
    @Resource
    private BidInfoMapper bidInfoMapper;
    @Resource
    private IncomeRecordMapper incomeRecordMapper;
    @Resource
    private FinanceAccountMapper financeAccountMapper;

    // 收益计划
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void generateIncomePlan() {
        // 获取要处理的理财产品记录
        Date currentDate = new Date();
        Date beginTime = DateUtils.truncate(DateUtils.addDays(currentDate, -1), Calendar.DATE);
        Date endTime = DateUtils.truncate(currentDate, Calendar.DATE);
        List<ProductInfo> productInfoList = productInfoMapper.selectFullTimeProducts(beginTime, endTime, YLBConstant.PRODUCT_STATUS_SELLED);
        // 查询每个理财产品的多个投资记录
        synchronized (this) {
            productInfoList.forEach(productInfo -> {
                BigDecimal dayRate = BigDecimal.ZERO;
                BigDecimal income = BigDecimal.ZERO;
                BigDecimal cycle = BigDecimal.ZERO;
                Date incomeDate = null;
                dayRate = productInfo.getRate().divide(new BigDecimal("360"), 10, RoundingMode.HALF_UP)
                        .divide(new BigDecimal("100"), 10, RoundingMode.HALF_UP);
                // 产品类型不同，周期不同 天，月
                if (Objects.equals(productInfo.getProductType(), YLBConstant.PRODUCT_TYPE_NOVICETREASURE)) {
                    cycle = new BigDecimal(productInfo.getCycle());
                    incomeDate = DateUtils.addDays(productInfo.getProductFullTime(), (1 + productInfo.getCycle()));
                } else {
                    cycle = new BigDecimal(productInfo.getCycle() * 30);
                    incomeDate = DateUtils.addDays(productInfo.getProductFullTime(), (1 + productInfo.getCycle() * 30));
                }
                List<BidInfo> bidInfoList = bidInfoMapper.selectByProdId(productInfo.getId(), YLBConstant.INVEST_STATUS_SUCCESS);
                // 计算每笔投资的利息和到期时间
                for (BidInfo bidInfo : bidInfoList) {
                    // 利息 = 本金* 周期*利率
                    income = bidInfo.getBidMoney().multiply(cycle).multiply(dayRate);
                    // 创建收益记录
                    IncomeRecord incomeRecord = IncomeRecord.builder()
                            .bidId(bidInfo.getId())
                            .bidMoney(bidInfo.getBidMoney())
                            .incomeDate(incomeDate)
                            .incomeStatus(YLBConstant.INCOME_STATUS_PLAN)
                            .prodId(productInfo.getId())
                            .incomeMoney(income)
                            .uid(bidInfo.getUid())
                            .build();
                    incomeRecordMapper.insertSelective(incomeRecord);
                }
                // 更新产品状态
                productInfoMapper.updateStatus(productInfo.getId(), YLBConstant.PRODUCT_STATUS_PLAN);
            });
        }
    }

    // 收益返还
    @Override
    @Transactional
    public void generateIncomeBack() {
        // 获取到期的收益记录
        Date currentDate = new Date();
        Date expiredDate = DateUtils.truncate(DateUtils.addDays(currentDate, -1), Calendar.DATE);
        List<IncomeRecord> incomeRecordList = incomeRecordMapper.selectExpiredIncome(expiredDate, YLBConstant.INCOME_STATUS_PLAN);
        // 把每个收益，进行返还，本金+利息
        synchronized (this) {
            incomeRecordList.forEach(incomeRecord -> {
                financeAccountMapper.updateAvailableMoneyByIncome(incomeRecord.getUid(), incomeRecord.getBidMoney(), incomeRecord.getIncomeMoney());
                // 更新收益记录状态
                incomeRecord.setIncomeStatus(YLBConstant.INCOME_STATUS_BACK);
                incomeRecordMapper.updateByPrimaryKeySelective(incomeRecord);
            });
        }

    }
}
