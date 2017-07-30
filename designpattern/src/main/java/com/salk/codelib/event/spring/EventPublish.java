package com.salk.codelib.event.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by salk on 2017/7/30.
 */
    @Component
    public class EventPublish {
        @Autowired
        ApplicationContext context;
        public void publish(String message) {
            context.publishEvent(new ContentEvent(message));

        }
    }
