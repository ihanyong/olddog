package com.monkey.disruptor.example.quickstart;


import com.lmax.disruptor.EventHandler;

import java.text.MessageFormat;

/**
 * LongEventHandler
 *
 * @author yong.han
 * 2018/12/4
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(MessageFormat.format("event: {0}, sequence:{1}, endOfBatch:{2}", event, sequence, endOfBatch));

    }
}
