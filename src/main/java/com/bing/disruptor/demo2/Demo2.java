package com.bing.disruptor.demo2;

import com.bing.disruptor.demo1.TradeTransaction;
import com.bing.disruptor.demo1.TradeTransactionInDBHandler;
import com.lmax.disruptor.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * demo2仍然没有使用disruptor封装，使用workerPool替代了BatchEventProcesser
 * <p>
 * 常用的方法是：disruptor.handleEventsWith(EventHandler ... handlers)，将多个EventHandler的实现类传入方法，封装成一个EventHandlerGroup，实现多消费者消费。
 * <p>
 * disruptor的另一个方法是：disruptor.handleEventsWithWorkerPool(WorkHandler ... handlers)，将多个WorkHandler的实现类传入方法，封装成一个EventHandlerGroup实现多消费者消费。
 * <p>
 * 两者共同点都是，将多个消费者封装到一起，供框架消费消息。
 * <p>
 * 不同点在于，
 * <p>
 * 1. 对于某一条消息m，handleEventsWith方法返回的EventHandlerGroup，Group中的每个消费者都会对m进行消费，各个消费者之间不存在竞争。handleEventsWithWorkerPool方法返回的EventHandlerGroup，Group的消费者对于同一条消息m不重复消费；也就是，如果c0消费了消息m，则c1不再消费消息m。
 * <p>
 * 2. 传入的形参不同。对于独立消费的消费者，应当实现EventHandler接口。对于不重复消费的消费者，应当实现WorkHandler接口。
 * <p>
 * 因此，根据消费者集合是否独立消费消息，可以对不同的接口进行实现。也可以对两种接口同时实现，具体消费流程由disruptor的方法调用决定。
 * https://www.cnblogs.com/pku-liuqiang/p/8544700.html
 * <p>
 * disruptor.handleEventsWith()和disruptor.handEventsWithWorkerPool组合，可以实现 多消费者全部消费，多消费者不重复消费的不同组合
 */
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        int BUFFER_SIZE = 1024;
        int THREAD_NUMBERS = 4;
        EventFactory<TradeTransaction> eventFactory = new EventFactory<TradeTransaction>() {
            public TradeTransaction newInstance() {
                return new TradeTransaction();
            }
        };
        RingBuffer<TradeTransaction> ringBuffer = RingBuffer.createSingleProducer(eventFactory, BUFFER_SIZE);

        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUMBERS);

        WorkHandler<TradeTransaction> workHandlers = new TradeTransactionInDBHandler();
        /*
         * 这个类代码很简单的，亲自己看哈！~
         */
        WorkerPool<TradeTransaction> workerPool = new WorkerPool<TradeTransaction>(ringBuffer, sequenceBarrier, new IgnoreExceptionHandler(), workHandlers);

        workerPool.start(executor);

        //下面这个生产8个数据，图简单就写到主线程算了
        for (int i = 0; i < 8; i++) {
            long seq = ringBuffer.next();
            ringBuffer.get(seq).setPrice(Math.random() * 9999);
            ringBuffer.publish(seq);
        }

        Thread.sleep(1000);
        workerPool.halt();
        executor.shutdown();
    }
}