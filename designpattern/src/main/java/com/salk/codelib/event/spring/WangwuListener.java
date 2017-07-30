package com.salk.codelib.event.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by salk on 2017/7/30.
 */
//@Component
public class WangwuListener implements SmartApplicationListener {
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == ContentEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return sourceType == String.class;
    }
    @Async
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("王五在孙六之前收到新的内容：" + event.getSource());

    }

    @Override
    public int getOrder() {
        return 1;
    }
}
