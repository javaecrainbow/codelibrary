package com.salk.codelib.event;

/**
 * Created by salk on 2017/7/30.
 * 事件通用接口：
 */
public interface Event {
    void doEvent();
}

class DbClickEvent implements Event {

    @Override
    public void doEvent() {
        System.out.println("do dbclick event");
    }
}

class ClickEvent implements Event {

    @Override
    public void doEvent() {
        System.out.println("do click event");
    }
}
