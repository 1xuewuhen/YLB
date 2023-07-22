package com.xwh.front.controller;

import com.xwh.api.model.BidInfo;
import com.xwh.api.model.IncomeRecord;
import com.xwh.api.model.RechargeRecord;
import com.xwh.common.util.CommonUtil;
import com.xwh.front.view.R;
import com.xwh.front.view.recharge.Record;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 血无痕
 * @date 2023/7/13
 * @since 1.0
 */

@Api(tags = "交易服务")
@RestController
@RequestMapping("/v1")
public class RechargeController extends BaseController {

    /**
     * @description: 充值记录
     * @author 血无痕
     * @date 2023/7/13 21:58
     * @version 1.0
     */
    @GetMapping("/recharge/records")
    @ApiOperation(value = "查询用户的充值记录", notes = "查询用户的充值记录")
    public R queryRechargePage(
            @RequestParam("uid") Integer uid,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "6") Integer pageSize

    ) {
        Map<String,List<?>> map = new HashMap<>();
        if (Objects.nonNull(uid)) {
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);
            Map<String, List<?>> listMap = rechargeService.queryByUid(uid, pageNo, pageSize);
            List<Record> records = toView(listMap.get("records"));
            List<Record> bidInfosRecords = toView(listMap.get("bidInfos"));
            List<Record> incomeRecords = toView(listMap.get("incomeRecords"));
            map.put("records",records);
            map.put("bidInfosRecords",bidInfosRecords);
            map.put("incomeRecords",incomeRecords);
        }
        return R.ok().setData(map);
    }

    private List<Record> toView(List<?> lists) {

        return lists.stream().map(item -> {
            Record record = new Record();
            if (item instanceof RechargeRecord) {
                RechargeRecord rechargeRecord = (RechargeRecord) item;
                record.setId(rechargeRecord.getId());
                record.setRechargeMoney(rechargeRecord.getRechargeMoney());
                if (Objects.nonNull(rechargeRecord.getRechargeTime())) {
                    record.setRechargeDate(DateFormatUtils.format(rechargeRecord.getRechargeTime(), "yyyy-MM-dd"));
                } else {
                    record.setRechargeDate("----");
                }
                switch (rechargeRecord.getRechargeStatus()) {
                    case 0:
                        record.setResult("充值中");
                        break;
                    case 1:
                        record.setResult("成功");
                        break;
                    case 2:
                        record.setResult("失败");
                        break;
                }
            } else if (item instanceof BidInfo) {
                BidInfo bidInfo = (BidInfo) item;
                record.setId(bidInfo.getId());
                record.setRechargeMoney(bidInfo.getBidMoney());
                if (Objects.nonNull(bidInfo.getBidTime())) {
                    record.setRechargeDate(DateFormatUtils.format(bidInfo.getBidTime(), "yyyy-MM-dd"));
                } else {
                    record.setRechargeDate("----");
                }
                switch (bidInfo.getBidStatus()) {
                    case 0:
                        record.setResult("充值中");
                        break;
                    case 1:
                        record.setResult("成功");
                        break;
                    case 2:
                        record.setResult("失败");
                        break;
                }
            } else if (item instanceof IncomeRecord) {
                IncomeRecord incomeRecord = (IncomeRecord) item;
                record.setId(incomeRecord.getId());
                record.setRechargeMoney(incomeRecord.getBidMoney());
                if (Objects.nonNull(incomeRecord.getIncomeDate())) {
                    record.setRechargeDate(DateFormatUtils.format(incomeRecord.getIncomeDate(), "yyyy-MM-dd"));
                } else {
                    record.setRechargeDate("----");
                }
                switch (incomeRecord.getIncomeStatus()) {
                    case 0:
                        record.setResult("充值中");
                        break;
                    case 1:
                        record.setResult("成功");
                        break;
                    case 2:
                        record.setResult("失败");
                        break;
                }
            }
            return record;
        }).collect(Collectors.toList());
    }
}
