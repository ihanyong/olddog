package com.monkey.disruptor.example.quickstart;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * LongEventProducerWithTranslator
 *
 * @author yong.han
 * 2018/12/4
 */
public class LongEventProducerWithTranslator {
    private RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR = (event, sequence, bb) -> event.set(bb.getLong(0));

    public void onData(ByteBuffer byteBuffer) {
        ringBuffer.publishEvent(TRANSLATOR, byteBuffer);
    }
}
