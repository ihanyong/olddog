package com.monkey.disruptor.example;

/**
 * @author hanyong
 * @Date 2018/12/4
 */
public class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LongEvent{");
        sb.append("value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
