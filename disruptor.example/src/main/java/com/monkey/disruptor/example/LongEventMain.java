package com.monkey.disruptor.example;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;
import java.text.MessageFormat;

/**
 * @author hanyong
 * @Date 2018/12/4
 */
public class LongEventMain {
    public static void handleEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println(MessageFormat.format("event:{0}, sequence:{1}, endOfBatch:{2}", event, sequence, endOfBatch));

    }

    public static void translate(LongEvent event, long sequence, ByteBuffer bb) {
        event.set(bb.getLong(0));
    }


    public static void main(String[] args) throws InterruptedException {
        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor =
                new Disruptor<LongEvent>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE, ProducerType.SINGLE, new BlockingWaitStrategy());

        disruptor.handleEventsWith(LongEventMain::handleEvent);

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ByteBuffer bb = ByteBuffer.allocate(8);

        int count = 0;
        while (count < 30) {
            bb.putLong(0, count++);
            ringBuffer.publishEvent(LongEventMain::translate, bb);
            Thread.sleep(1000);

        }


    }
}
