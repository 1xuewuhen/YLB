package com.xwh.dataservice.service;

import com.xwh.api.model.BidInfo;
import com.xwh.api.model.IncomeRecord;
import com.xwh.api.model.RechargeRecord;
import com.xwh.api.service.RechargeService;
import com.xwh.common.util.CommonUtil;
import com.xwh.dataservice.mapper.BidInfoMapper;
import com.xwh.dataservice.mapper.IncomeRecordMapper;
import com.xwh.dataservice.mapper.RechargeRecordMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author 血无痕
 * @date 2023/7/13
 * @since 1.0
 */

@DubboService(interfaceClass = RechargeService.class, version = "1.0")
public class RechargeServiceImpl implements RechargeService {

    @Resource
    private RechargeRecordMapper rechargeRecordMapper;
    @Resource
    private BidInfoMapper bidInfoMapper;
    @Resource
    private IncomeRecordMapper incomeRecordMapper;
    // 充值记录
    @Override
    public Map<String, List<?>> queryByUid(Integer uid, Integer pageNo, Integer pageSize) {
        Map<String,List<?>> map = new HashMap<>();
        if (Objects.nonNull(uid)) {
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);
            int offset = (pageNo - 1) * pageSize;
            List<RechargeRecord> records = rechargeRecordMapper.selectByUid(uid, offset, pageSize);
            List<BidInfo> bidInfos = bidInfoMapper.selectByUid(uid, offset, pageSize);
            List<IncomeRecord> incomeRecords = incomeRecordMapper.selectByUid(uid, offset, pageSize);
            map.put("records",records);
            map.put("bidInfos",bidInfos);
            map.put("incomeRecords",incomeRecords);
        }
//        return records;
        return map;
    }
}
