package com.monkey.disruptor.example.quickstart;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

/**
 * LongEventMain
 *
 * @author yong.han
 * 2018/12/4
 */
public class LongEventMain {
    public static void main(String[] args) throws InterruptedException {
        LongEventFactory factory = new LongEventFactory();

        int bufferSize = 8;

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, DaemonThreadFactory.INSTANCE);

        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        long count = 0;
        while (true) {
            bb.putLong(0, count++);
            producer.onData(bb);
            Thread.sleep(1000);
        }
    }
}
