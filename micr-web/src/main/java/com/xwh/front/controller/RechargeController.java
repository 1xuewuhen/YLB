package com.xwh.front.controller;

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

import java.util.List;
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

        List<Record> recordList = null;
        if (Objects.nonNull(uid)) {
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);
            List<RechargeRecord> records = rechargeService.queryByUid(uid, pageNo, pageSize);
            recordList = toView(records);
        }
        return R.ok().setList(recordList);
    }

    private List<Record> toView(List<RechargeRecord> records) {

        return records.stream().map(item -> {
            Record record = new Record();
            record.setId(item.getId());
            record.setRechargeMoney(item.getRechargeMoney());
            if (Objects.nonNull(item.getRechargeTime())) {
                record.setRechargeDate(DateFormatUtils.format(item.getRechargeTime(), "yyyy-MM-dd"));
            } else {
                record.setRechargeDate("----");
            }
            switch (item.getRechargeStatus()) {
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
            return record;
        }).collect(Collectors.toList());
    }


}
