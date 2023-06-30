package com.xwh.info.init;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈方银
 * @date 2023/6/30
 * @since 1.0
 */

@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "custom.thread")
@Component
public class Initial {

    private Integer corePoolSize;
    private Integer maximumPoolSize;
    private Integer keepAliveTime;
    private Integer capacity;


    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        // 在这里已经拿到数据库中的值了
        return new ThreadPoolExecutor(
                corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MINUTES, new LinkedBlockingDeque<>(capacity),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy()
        );
    }


}
