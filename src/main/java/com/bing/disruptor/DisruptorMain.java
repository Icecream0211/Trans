package com.bing.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DisruptorMain {
    public static void main(String[] args) {

        EventFactory<MyEvent> myEventEventFactory = new MyEventFactory();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        int ringBufferSize = 32;

        Disruptor<MyEvent> disruptor = new Disruptor<MyEvent>(myEventEventFactory,ringBufferSize,threadFactory);


         EventHandler<MyEvent> b = new MyEventHandlerB();
         EventHandler<MyEvent> c = new MyEventHandlerC();
         EventHandler<MyEvent> d = new MyEventHandlerD();

        SequenceBarrier sequenceBarrier = disruptor.handleEventsWith(b,c).asSequenceBarrier();

        //BatchEventProcessor processor = new BatchEventProcessor(disruptor.getRingBuffer(),sequenceBarrier,d);
        //disruptor.handleEventsWith(processor);
        disruptor.after(b,c).handleEventsWith(d);// 此行能代替上两行的程序逻辑


        RingBuffer<MyEvent> ringBuffer = disruptor.start();

        for (int i=0;i< 10;i++){
            long sequence = ringBuffer.next();
            try {
                MyEvent myEvent = ringBuffer.get(sequence);
                myEvent.setValue(i);
            }finally {
                ringBuffer.publish(sequence);
            }
            try {
                Thread.sleep(100);
            }catch (Exception e){

            }

        }
        disruptor.shutdown();


//        EventFactory<MyEvent> myEventFactory = new MyEventFactory();
//        Executor executor = Executors.newCachedThreadPool();
//        int ringBufferSize = 32;
//
//        Disruptor<MyEvent> disruptor = new Disruptor<MyEvent>(myEventFactory,ringBufferSize,executor, ProducerType.SINGLE,new BlockingWaitStrategy());
//        EventHandler<MyEvent> b = new MyEventHandlerB();
//        EventHandler<MyEvent> c = new MyEventHandlerC();
//        EventHandler<MyEvent> d = new MyEventHandlerD();
//
//        SequenceBarrier sequenceBarrier2 = disruptor.handleEventsWith(b,c).asSequenceBarrier();
//        BatchEventProcessor processord = new BatchEventProcessor(disruptor.getRingBuffer(),sequenceBarrier2,d);
//        disruptor.handleEventsWith(processord);
////  disruptor.after(b,c).handleEventsWith(d);              // 此行能代替上两行的程序逻辑
//        RingBuffer<MyEvent> ringBuffer = disruptor.start();    // 启动Disruptor
//        for(int i=0; i<10; i++) {
//            long sequence = ringBuffer.next();                 // 申请位置
//            try {
//                MyEvent myEvent = ringBuffer.get(sequence);
//                myEvent.setValue(i);                           // 放置数据
//            } finally {
//                ringBuffer.publish(sequence);                  // 提交，如果不提交完成事件会一直阻塞
//            }
//            try{
//                Thread.sleep(100);
//            }catch (Exception e){
//            }
//        }
//        disruptor.shutdown();
    }
}
