package com.monkey.disruptor.example.quickstart;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * LongEventProducer
 *
 * @author yong.han
 * 2018/12/4
 */
public class LongEventProducer {


    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer) {
        long sequence = ringBuffer.next();

        try {
            LongEvent event = ringBuffer.get(sequence);
            event.set(byteBuffer.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
