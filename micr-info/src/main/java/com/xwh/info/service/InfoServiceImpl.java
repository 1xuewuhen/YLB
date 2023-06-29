package com.xwh.info.service;

import com.xwh.api.service.info.InfoService;
import com.xwh.info.vo.QQInfoTemplate;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Date;

/**
 * @author 陈方银
 * @date 2023/6/29
 * @since 1.0
 */

@DubboService(interfaceClass = InfoService.class, version = "1.0")
public class InfoServiceImpl implements InfoService {

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sendMailer;

    @Resource
    private QQInfoTemplate qqInfoTemplate;

    @Override
    public void sendEmail(String toEmail) {
        if (StringUtils.hasText(toEmail)) {
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
                context = context.replaceAll("\\{code}", "222222");
                mimeMessageHelper.setText(context);
//                mimeMessageHelper.addAttachment();
                //邮件发送时间
                mimeMessageHelper.setSentDate(new Date());

                //发送邮件
                javaMailSender.send(mimeMessageHelper.getMimeMessage());

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
