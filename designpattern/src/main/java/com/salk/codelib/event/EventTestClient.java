package com.salk.codelib.event;

/**
 * Created by salk on 2017/7/30.
 * 可以使用队列做异步操作，或者使用spring中的事件驱动
 */
public class EventTestClient {
    private static Button button;
    public static void main(String[] args) {
        EventTestClient eventTestClient=new EventTestClient();
        Event currentEvent = new ClickEvent();
        button.notifyListeners(currentEvent);
    }
    EventTestClient(){
        button=new Button();
        button.addEventListener(new EventListener<ClickEvent>() {
            @Override
            public void handlerEvent(ClickEvent event) {
                event.doEvent();
            }

            @Override
            public boolean supportsEventType(Event eventType) {
                return eventType instanceof ClickEvent;
            }
        });
        button.addEventListener(new EventListener<DbClickEvent>() {
            @Override
            public void handlerEvent(DbClickEvent event) {
                event.doEvent();
            }

            @Override
            public boolean supportsEventType(Event eventType) {
                return eventType instanceof DbClickEvent;
            }

        });

    }
}
