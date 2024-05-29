package com.code.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TimeTask {

    @Scheduled(cron = "*/2 * * * * ?")
    public void refresh(){
        log.info(" 定时刷新");
        System.out.println("这是一个定时任务的测试");
        //业务代码
    }

}