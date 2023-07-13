package com.xwh.dataservice.service;

import com.xwh.api.model.RechargeRecord;
import com.xwh.api.service.RechargeService;
import com.xwh.common.util.CommonUtil;
import com.xwh.dataservice.mapper.RechargeRecordMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 血无痕
 * @date 2023/7/13
 * @since 1.0
 */

@DubboService(interfaceClass = RechargeService.class, version = "1.0")
public class RechargeServiceImpl implements RechargeService {

    @Resource
    private RechargeRecordMapper rechargeRecordMapper;

    // 充值记录
    @Override
    public List<RechargeRecord> queryByUid(Integer uid, Integer pageNo, Integer pageSize) {
        List<RechargeRecord> records = new ArrayList<>();
        if (Objects.nonNull(uid)) {
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);
            int offset = (pageNo - 1) * pageSize;
            records = rechargeRecordMapper.selectByUid(uid, offset, pageSize);
        }
        return records;
    }
}
