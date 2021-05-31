package com.nbnfsoft.admin.config;

import cn.hutool.cron.CronUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author:louyi
 * @Description：
 * @Date:Create in 8:55 2021-02-03
 */
@Component
public class ApplicationClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        if(CronUtil.getScheduler().isStarted()){
            CronUtil.stop();
        }
    }
}
