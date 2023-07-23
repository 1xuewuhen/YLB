package com.xwh.task.jobTask;

import com.xwh.api.service.income.IncomeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 血无痕
 * @date 2023/7/22
 * @since 1.0
 */

@Component(value = "taskManager")
@EnableScheduling
public class TaskManager {
    /**
     * @description: 定义方法，表示执行的定时任务功能
     * 方法定义的要求
     * 1、public 公共方法
     * 2、方法没有参数
     * 3、方法没有返回值
     * 4、方法的上面加入注解，设置corn属性，指定时间
     * @author 血无痕
     * @date 2023/7/22 23:06
     * @version 1.0
     */

    @DubboReference(interfaceClass = IncomeService.class, version = "1.0")
    private IncomeService incomeService;

    // 生成收益计划
    @Scheduled(cron = "0 0 1 * * ?")
    public void invokeGenerateIncomePlan() {
        incomeService.generateIncomePlan();
    }
    // 收益的返还
    @Scheduled(cron = "0 0 2 * * ?")
    public void invokeGenerateIncomeBack(){
        incomeService.generateIncomeBack();
    }
}
