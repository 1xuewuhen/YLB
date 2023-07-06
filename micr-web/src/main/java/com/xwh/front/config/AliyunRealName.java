package com.xwh.front.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 陈方银
 * @date 2023/7/6
 * @since 1.0
 */

@Component
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "aliyun.real-name")
public class AliyunRealName {

    private String host;
    private String path;
    private String appcode;
}
