package com.xwh.task;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 血无痕
 * @date 2023/7/22
 * @since 1.0
 */
@SpringBootApplication
@EnableDubbo
public class MicrTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicrTaskApplication.class, args);
    }
}
