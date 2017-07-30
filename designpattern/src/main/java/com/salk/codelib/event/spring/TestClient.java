package com.salk.codelib.event.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by salk on 2017/7/30.
 */
public class TestClient {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigTest.class);
        EventPublish eventPublish=context.getBean(EventPublish.class);
        eventPublish.publish("ZHANGSAN");
        System.out.println("out");
    }

}
