package com.salk.codelib.event.spring;

import org.springframework.context.ApplicationEvent;

/**
 * Created by salk on 2017/7/30.
 */
public class ContentEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public ContentEvent(Object source) {
        super(source);
    }
}
