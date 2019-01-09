package com.monkey.concurrent.example.javaConcurrent.utilstest.cyclicBarrier;

import com.monkey.concurrent.example.javaConcurrent.Utils;

import java.util.concurrent.*;

/**
 * CyclicBarrierTest
 *
 * @author yong.han
 * 2019/1/9
 */
public class CyclicBarrierTest {

    public static void main(String[] args) throws InterruptedException {

        final CyclicBarrier cb = new CyclicBarrier(2);


        startThread("thread-1", ()->{
            String threadName = Thread.currentThread().getName();
            Utils.log("{0} await for cb", threadName);

            try {
                Thread.sleep(105);
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
                synchronized (cb) {
                    if (cb.isBroken()) {
                        Utils.log("{0} reset cb", threadName);
                        cb.reset();
                    }
                }
                try {
                    Utils.log("{0} await cb after broken", threadName);
                    cb.await();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } catch (BrokenBarrierException e1) {
                    e1.printStackTrace();
                }
            }
            Utils.log("{0} tripped cb", threadName);

        });

        System.out.println("for debug");

        startThread("thread-2", ()->{
            String threadName = Thread.currentThread().getName();
            Utils.log("{0} await for cb", threadName);

            try {
                cb.await(100, TimeUnit.MILLISECONDS);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
                synchronized (cb) {
                    if (cb.isBroken()) {
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        Utils.log("{0} reset cb", threadName);
                        cb.reset();
                    }
                }
                try {
                    Utils.log("{0} await cb after timeout", threadName);
                    cb.await();
                } catch (InterruptedException e_) {
                    e_.printStackTrace();
                } catch (BrokenBarrierException e_) {
                    e_.printStackTrace();
                }
            }
            Utils.log("{0} tripped cb", threadName);

        });

//        new CountDownLatch(1).await();
        System.out.println("end");
    }

    public static void startThread(String threadName, Runnable runnable) {
        new Thread(threadName){
            @Override
            public void run() {
                runnable.run();
            }
        }.start();
    }

}
