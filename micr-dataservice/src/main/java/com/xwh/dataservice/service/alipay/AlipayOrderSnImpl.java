package com.xwh.dataservice.service.alipay;

import com.xwh.api.service.alipay.AlipayOrderSn;
import com.xwh.dataservice.mapper.FinanceAccountMapper;
import com.xwh.dataservice.mapper.RechargeRecordMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

/**
 * @author 血无痕
 * @date 2023/7/26
 * @since 1.0
 */

@DubboService(interfaceClass = AlipayOrderSn.class, version = "1.0")
public class AlipayOrderSnImpl implements AlipayOrderSn {

    @Resource
    private RechargeRecordMapper rechargeRecordMapper;
    @Resource
    private FinanceAccountMapper financeAccountMapper;

    @Transactional
    @Override
    public void addRecharge(Map<String, String> map) {
        if (Objects.nonNull(map)) {
            rechargeRecordMapper.addRecharge(map);
            financeAccountMapper.addMoneyByUid(map.get("uid"), new BigDecimal(map.get("recharge_money")));
            return;
        }
        throw new RuntimeException("记录添加失败");
    }
}
