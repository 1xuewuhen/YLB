package com.xwh.api.service.info;

/**
 * @author 陈方银
 * @date 2023/6/29
 * @since 1.0
 */
public interface InfoService {

    // 发送邮件
    void sendEmail(String toEmail) throws Exception;
}
