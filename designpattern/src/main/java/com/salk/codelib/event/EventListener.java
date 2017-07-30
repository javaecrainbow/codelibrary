package com.salk.codelib.event;

/**
 * Created by salk on 2017/7/30.
 *
 * 事件监听器通用接口：
 */
public interface EventListener<T extends Event> {
    void handlerEvent(T event);
    boolean  supportsEventType(final Event eventType);
}
