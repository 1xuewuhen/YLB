package com.xwh.task;

import com.xwh.task.jobTask.TaskManager;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 血无痕
 * @date 2023/7/22
 * @since 1.0
 */
@SpringBootApplication
@EnableDubbo
public class MicrTaskApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MicrTaskApplication.class, args);
        TaskManager taskManager = (TaskManager) applicationContext.getBean("taskManager");
        taskManager.invokeGenerateIncomePlan();
    }
}
