package com.xwh.front.service;

import com.xwh.api.vo.RealNameVo;

/**
 * @author 陈方银
 * @date 2023/7/6
 * @since 1.0
 */
public interface AliyunRealNameService {

    // 实名认证
    boolean realName(RealNameVo realNameVo);
}
