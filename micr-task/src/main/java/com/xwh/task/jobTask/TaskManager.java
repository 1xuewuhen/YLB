package com.xwh.task.jobTask;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 血无痕
 * @date 2023/7/22
 * @since 1.0
 */

@Component
@EnableScheduling
public class TaskManager {
    /**
     * @description:
     *  定义方法，表示执行的定时任务功能
     *  方法定义的要求
     *  1、public 公共方法
     *  2、方法没有参数
     *  3、方法没有返回值
     *  4、方法的上面加入注解，设置corn属性，指定时间
     * @author 血无痕
     * @date 2023/7/22 23:06
     * @version 1.0
     */
    @Scheduled(cron = "* * * * * ?")
    public void testCorn(){
        System.out.println(new Date());
    }
}
