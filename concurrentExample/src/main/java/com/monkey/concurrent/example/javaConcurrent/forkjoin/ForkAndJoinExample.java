package com.monkey.concurrent.example.javaConcurrent.forkjoin;

import java.util.concurrent.*;

/**
 * ForkAndJoinExample
 *
 * @author yong.han
 * 2019/3/6
 */
public class ForkAndJoinExample {


    public static long delay = 5;

    public static void main(String[] args) {
        long count = 1_000;

        for (int i = 0; i < 10; i++) {
            System.out.println("========================================");
            testForkJoin(1, count+1, 10);
            testForkJoin(1, count+1, 50);
//            testForkJoin(1, count+1, 1_000);
//            testForkJoin(1, count+1, 10_000);
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

    public static void testForkJoin(long begin, long end, long threshold) {
        ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool();


        ForkJoinTask<Long> task = new CountTask(begin, end, threshold); // 100000001

        long start = System.currentTimeMillis();
        forkJoinPool.submit(task);
        long result = 0;
        try {
            result = task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long stop = System.currentTimeMillis();
        System.out.println("forkjoin ("+threshold+ ") cost time is " + (stop - start) + "ms");
        System.out.println(result);

    }




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
                long r = 0;
                for (long i = begin; i < end; i++) {
                    r += i;
                    delay();
                }
                return r;
            } else {
                long mid = (begin + end) / 2;
                ForkJoinTask<Long> left = new CountTask(begin, mid, this.threshold);
                ForkJoinTask<Long> right = new CountTask(mid, end, this.threshold);
                left.fork();
                right.fork();

                long lr = left.join();
                long rr = right.join();
                return lr + rr;
            }
        }
    }

}


