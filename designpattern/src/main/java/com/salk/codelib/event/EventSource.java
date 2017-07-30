package com.salk.codelib.event;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by salk on 2017/7/30.
 * 事件源通用接口：
 */
public interface EventSource {
    void addEventListener(EventListener<? extends Event> eventListener);
    void removeEventListener(EventListener<? extends Event> eventListener);
    void notifyListeners(Event event);
}

class Button implements EventSource{
    List<EventListener<? extends Event>> listenerList=new LinkedList<EventListener<? extends Event>>();
    @Override
    public void addEventListener(EventListener<? extends Event> eventListener) {
        listenerList.add(eventListener);
    }

    @Override
    public void removeEventListener(EventListener<? extends Event> eventListener) {
        listenerList.remove(eventListener);
    }

    @Override
    public void notifyListeners(Event event) {
    for(EventListener eventListener:listenerList){
        if(eventListener.supportsEventType(event)) {
            eventListener.handlerEvent(event);
        }
    }
    }
}
