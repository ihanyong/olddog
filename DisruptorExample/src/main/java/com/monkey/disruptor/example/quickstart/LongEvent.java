package com.monkey.disruptor.example.quickstart;

/**
 * LongEvent
 *
 * @author yong.han
 * 2018/12/4
 */
public class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}
