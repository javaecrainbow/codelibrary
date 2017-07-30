package com.salk.codelib.event.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by salk on 2017/7/30.
 */
@Component
public class LisiListener implements ApplicationListener<ApplicationEvent> {
    @Async
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(event instanceof ContentEvent) {
            System.out.println("李四收到了新的内容：" + event.getSource());
        }
    }
}
