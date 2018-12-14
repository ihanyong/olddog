package com.monkey.concurrent.example.javaConcurrent;


import java.util.concurrent.*;

/**
 * blockingQueueTest
 *
 * @author yong.han
 * 2018/12/14
 */
public class blockingQueueTest {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(2);


        BlockingQueue<String> queue = ofBlockingQueue("");


        CountDownLatch latch = new CountDownLatch(2);

        executor.submit(() -> {
            for (int i = 1; i <= 5000; i++) {
                String msg = "msg" + i;
                try {
                    queue.put(msg);
                    System.out.println("send msg: " +  msg + " and " + queue.size() + "msgs left");
                } catch (InterruptedException e) {
                    System.out.println("send msg " + msg + " fail, " + e.getMessage());
                }
            }
            latch.countDown();
        });

        executor.submit(() -> {
            int nullCount = 0;
            while (true) {
                String msg = queue.poll();
                if (null == msg) {
                    nullCount++;
                    System.out.println("empty queue for " + nullCount + " times.");
                    if (latch.getCount() < 2) {
                        break;
                    }
                } else {
                    System.out.println("receive msg: " + msg + " and " + queue.size() + "msgs left");
                }

            }
            latch.countDown();

        });

        latch.await();
        executor.shutdown();
    }


    public static <T> BlockingQueue<T> ofBlockingQueue(String type) {
        return new ArrayBlockingQueue<T>(500);
    }

}
