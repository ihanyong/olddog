package com.monkey.disruptor.example.quickstart;

import com.lmax.disruptor.EventFactory;

/**
 * LongEventFactory
 *
 * @author yong.han
 * 2018/12/4
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
