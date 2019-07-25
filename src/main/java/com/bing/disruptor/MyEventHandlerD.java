package com.bing.disruptor;

import com.lmax.disruptor.EventHandler;

public class MyEventHandlerD implements EventHandler<MyEvent> {
    public void onEvent(MyEvent myEvent, long l, boolean b) throws Exception {
        System.out.println("Comsume Event D : " + myEvent.getValue());
    }
}
