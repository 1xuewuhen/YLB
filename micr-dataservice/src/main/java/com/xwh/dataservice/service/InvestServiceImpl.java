package com.xwh.dataservice.service;

import com.xwh.api.model.BidInfo;
import com.xwh.api.model.FinanceAccount;
import com.xwh.api.model.ProductInfo;
import com.xwh.api.pojo.BidInfoProduct;
import com.xwh.api.service.InvestService;
import com.xwh.common.constants.YLBConstant;
import com.xwh.common.enums.ERRORCode;
import com.xwh.common.exception.MoneyException;
import com.xwh.common.exception.ProductException;
import com.xwh.common.exception.UserException;
import com.xwh.common.util.CommonUtil;
import com.xwh.dataservice.mapper.BidInfoMapper;
import com.xwh.dataservice.mapper.FinanceAccountMapper;
import com.xwh.dataservice.mapper.ProductInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 作者:陈方银
 * 时间:2023/6/27
 */

@DubboService(interfaceClass = InvestService.class, version = "1.0")
public class InvestServiceImpl implements InvestService {

    @Resource
    private BidInfoMapper bidInfoMapper;
    @Resource
    private FinanceAccountMapper financeAccountMapper;
    @Resource
    private ProductInfoMapper productInfoMapper;

    // 查询某个产品的投注记录
    @Override
    public List<BidInfoProduct> queryBidListByProductId(Integer productId, Integer pageNo, Integer pageSize) {
        List<BidInfoProduct> bidList = new ArrayList<>();
        if (Objects.nonNull(productId) && productId > 0) {
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);
            int offset = (pageNo - 1) * pageSize;
            bidList = bidInfoMapper.selectByProductId(productId, offset, pageSize);
        }
        return bidList;
    }

    // 投资理财产品
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void investProduct(Integer uid, Integer productId, BigDecimal money) {
        if (Objects.nonNull(uid) && Objects.nonNull(productId) &&
                (Objects.nonNull(money) &&
                        (CommonUtil.geBigDecimal(money.remainder(new BigDecimal("100")), BigDecimal.ZERO)) && CommonUtil.geBigDecimal(money, new BigDecimal("100")))) {
            // 查询账户金额
            FinanceAccount financeAccount = financeAccountMapper.selectByUidUpdate(uid);
            if (Objects.nonNull(financeAccount)) {
                // 是否可以购买
                if (CommonUtil.geBigDecimal(financeAccount.getAvailableMoney(), money)) {
                    // 检查产品是否可以购买
                    ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(productId);
                    if (Objects.nonNull(productInfo) && Objects.equals(productInfo.getProductStatus(), YLBConstant.PRODUCT_STATUS_SELLING)) {
                        if (CommonUtil.geBigDecimal(productInfo.getLeftProductMoney(), money)
                                && CommonUtil.geBigDecimal(money, productInfo.getBidMinLimit())
                                && CommonUtil.geBigDecimal(productInfo.getBidMaxLimit(), money)) {
                            // 购买产品
                            // 扣除账户资金
                            financeAccountMapper.updateAvailableMoneyByInvest(uid, money);
                            productInfoMapper.updateLeftProductMoney(productId, money);
                            // 创建投资记录
                            BidInfo bidInfo = BidInfo.builder()
                                    .bidMoney(money)
                                    .bidStatus(YLBConstant.INVEST_STATUS_SUCCESS)
                                    .bidTime(new Date())
                                    .prodId(productId)
                                    .uid(uid)
                                    .build();
                            bidInfoMapper.insertSelective(bidInfo);
                            // 判断产品是否买完，更新产品是满标状态
                            ProductInfo deProductInfo = productInfoMapper.selectByPrimaryKey(productId);
                            if (deProductInfo.getLeftProductMoney().compareTo(BigDecimal.ZERO) == 0) {
                                productInfoMapper.updateProductStatus(productId,YLBConstant.PRODUCT_STATUS_SELLED);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    throw new ProductException(CommonUtil.generateJSON(ERRORCode.PRODUCT_NOT_PRESENT_ERROR.getCode(), ERRORCode.PRODUCT_NOT_PRESENT_ERROR.getMessage()));
                }
                throw new MoneyException(CommonUtil.generateJSON(ERRORCode.MONEY_INSUFFICIENT_ERROR.getCode(), ERRORCode.EMAIL_CODE_ERROR.getMessage()));
            }
            throw new UserException(CommonUtil.generateJSON(ERRORCode.MONEY_ERROR.getCode(), ERRORCode.MONEY_ERROR.getMessage()));
        }
    }
}
