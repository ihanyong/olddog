package com.monkey.concurrent.example.javaConcurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrierTest
 *
 * @author yong.han
 * 2018/12/14
 */
public class CyclicBarrierTest {


    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(()->{
            System.out.println();

        });
        executor.submit(()->{});


        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }

//    public static class exampleRun
}
