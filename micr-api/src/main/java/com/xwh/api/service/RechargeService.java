package com.xwh.api.service;

import com.xwh.api.model.RechargeRecord;

import java.util.List;
import java.util.Map;

/**
 * @author 陈方银
 * @date 2023/7/13
 * @since 1.0
 */
public interface RechargeService {
    // 充值记录
    Map<String,List<?>> queryByUid(Integer uid, Integer pageNo, Integer pageSize);
}
