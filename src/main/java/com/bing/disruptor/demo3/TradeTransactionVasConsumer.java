package com.bing.disruptor.demo3;

import com.bing.disruptor.demo1.TradeTransaction;
import com.lmax.disruptor.EventHandler;

public class TradeTransactionVasConsumer implements EventHandler<TradeTransaction> {

    @Override
    public void onEvent(TradeTransaction event, long sequence,
                        boolean endOfBatch) throws Exception {
        //do something....
    }

}
