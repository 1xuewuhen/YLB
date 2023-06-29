package com.xwh.info.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 陈方银
 * @date 2023/6/29
 * @since 1.0
 */

@Data
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix = "info.email")
public class QQInfoTemplate {
    private String subject;
    private String context;
}
