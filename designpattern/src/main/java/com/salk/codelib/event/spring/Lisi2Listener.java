package com.salk.codelib.event.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by salk on 2017/7/31.
 */
@Component
public class Lisi2Listener implements ApplicationListener<ApplicationEvent> {
    @Async
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(event instanceof ContentEvent) {
            System.out.println("李四2收到了新的内容：" + event.getSource());
        }
    }
}
