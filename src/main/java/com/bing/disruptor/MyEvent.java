package com.bing.disruptor;

public class MyEvent {

    private long value;

    public MyEvent() {
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
