package com.xwh.info.service;

import com.xwh.api.service.info.InfoService;
import com.xwh.common.constants.RedisKey;
import com.xwh.common.enums.ERRORCode;
import com.xwh.common.exception.InfoException;
import com.xwh.common.util.CommonUtil;
import com.xwh.info.vo.QQInfoTemplate;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈方银
 * @date 2023/6/29
 * @since 1.0
 */

@DubboService(interfaceClass = InfoService.class, version = "1.0")
public class InfoServiceImpl implements InfoService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sendMailer;

    @Resource
    private QQInfoTemplate qqInfoTemplate;

    @Resource
    private ThreadPoolExecutor executor;

    // 发送邮件
    @Override
    public void sendEmail(String toEmail) {
        if (!CommonUtil.checkEmail(toEmail)) {
            throw new InfoException(CommonUtil.generateJSON(ERRORCode.EMAIL_NULL_ERROR.getCode(), ERRORCode.EMAIL_NULL_ERROR.getMessage()));
        }
        if (Objects.nonNull(stringRedisTemplate.boundValueOps(RedisKey.KEY_EMAIL_CODE_REG + toEmail).get())) {
            throw new InfoException(CommonUtil.generateJSON(ERRORCode.EMAIL_CODE_CAN_USE.getCode(), ERRORCode.EMAIL_CODE_CAN_USE.getMessage()));
        }
        CompletableFuture.runAsync(() -> {
            try {
                //true 代表支持复杂的类型
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
                //邮件发信人
                mimeMessageHelper.setFrom(sendMailer);
                //邮件收信人  1或多个
                mimeMessageHelper.setTo(toEmail.split(","));
                //邮件主题
                mimeMessageHelper.setSubject(qqInfoTemplate.getSubject());
                //邮件内容
                String context = qqInfoTemplate.getContext();
                String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(4);
                context = context.replaceAll("\\{code}", randomAlphanumeric);
                mimeMessageHelper.setText(context);
//                mimeMessageHelper.addAttachment();
                //邮件发送时间
                mimeMessageHelper.setSentDate(new Date());
                //发送邮件
                javaMailSender.send(mimeMessageHelper.getMimeMessage());
                stringRedisTemplate.boundValueOps(RedisKey.KEY_EMAIL_CODE_REG + toEmail).set(randomAlphanumeric, 1, TimeUnit.MINUTES);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }
}
