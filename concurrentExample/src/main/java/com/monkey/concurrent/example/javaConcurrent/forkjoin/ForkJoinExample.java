package com.monkey.concurrent.example.javaConcurrent.forkjoin;

import java.util.concurrent.*;

/**
 * ForkJoinExample
 * 通过forkjoin实现 1+2+3+…+n 求和， 并和最基本单线程方式做对比
 * @author yong.han
 * 2019/3/6
 */
public class ForkJoinExample {

    public static void testForkJoin(long begin, long end, long threshold) {

        ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool();

        ForkJoinTask<Long> task = new CountTask(begin, end, threshold);

        long start = System.currentTimeMillis();
        // 提交任务
        forkJoinPool.submit(task);
        // 获取结果
        long result = task.join();

        long stop = System.currentTimeMillis();
        System.out.println("forkjoin ("+threshold+ ") cost time is " + (stop - start) + "ms");
        System.out.println(result);

    }

    // 继承 RecursiveTask 来返回一个结果
    public static class CountTask extends RecursiveTask<Long> {
        private final long begin;
        private final long end;
        private final long threshold;
        public CountTask(long begin, long end, long threshold) {
            this.begin = begin;
            this.end = end;
            this.threshold = threshold;
        }
        @Override
        protected Long compute() {
            if (end - begin <= threshold) {
                // 任务量小于指定值时直接求结果
                long r = 0;
                for (long i = begin; i < end; i++) {
                    r += i;
                    delay();
                }
                return r;
            } else {
                // 任务量大于指定值时(递归地)分解任务
                long mid = (begin + end) / 2;
                ForkJoinTask<Long> left = new CountTask(begin, mid, this.threshold);
                ForkJoinTask<Long> right = new CountTask(mid, end, this.threshold);
                // fork 子任务
                left.fork();
                right.fork();

                // 等待子任务结果并合并结果
                long lr = left.join();
                long rr = right.join();
                return lr + rr;
            }
        }
    }


    /**
     * 单线程版本
     * @param begin
     * @param end
     * @return
     */
    public static long basicImpl(long begin, long end) {
        long start = System.currentTimeMillis();

        long r = 0;
        for (long i = begin; i < end; i++) {
            r += i;
            delay();
        }
        long stop = System.currentTimeMillis();
        System.out.println("basic cost time is " + (stop - start) + "ms.");
        System.out.println(r);
        return r;
    }


    /* 通过Thread.sleep 模拟耗时操作， 为0不sleep   */
    public static long delay = 0;

    /**
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        long count = 1_000_000;

        //
        for (int i = 0; i < 10; i++) {
            System.out.println("========================================");
            testForkJoin(1, count+1, 10);
            testForkJoin(1, count+1, 50);
            testForkJoin(1, count+1, 1_000);
            testForkJoin(1, count+1, 10_000);
            basicImpl(1, count+1);
        }
    }

    public static void delay() {
        if (delay>0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




}


