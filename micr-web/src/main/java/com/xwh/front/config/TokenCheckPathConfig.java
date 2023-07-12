package com.xwh.front.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 作者:陈方银
 * 时间:2023/7/12
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ConfigurationProperties(prefix = "token.check")
@PropertySource(value = "classpath:tokenPath.properties", encoding = "utf-8")
public class TokenCheckPathConfig {

    private List<String> path;
}
