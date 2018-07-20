package old.dog.test.concurrency.volatilet;

import old.dog.test.concurrency.ThreadUtil;

/**
 * Created by HanYong on 2018/7/20.
 */
public class TestVolatileAndSynchronized {

    public static void main(String[] args) {
        int runTimes = 50000;
        run(runTimes, new EntityVolatile());
        run(runTimes, new EntityNoVolatile());
        run(runTimes, new EntitySyn());

    }

    public static void run(int times, EntityI e) {
        Runnable runnable = () -> {
            for (int i = 0; i < times; i++) {
                int r = e.increase();
//                System.out.println(String.format("%s::% 4d::% 4d", Thread.currentThread().getName(), i, r));
            }
        };


        Thread t1 = ThreadUtil.newDaemonThread("t1", runnable);
        Thread t2 = ThreadUtil.newDaemonThread("t2", runnable);

        long costTime = System.currentTimeMillis();

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();

            System.out.println(String.format("cost % 5d milliseconds, and the final result is % 7d : %s",System.currentTimeMillis()-costTime, e.getValue(),  e.getClass().getSimpleName()));
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

    }



    public interface EntityI{
        int increase();

        int getValue();
    }

    public static class EntityNoVolatile implements EntityI{
        private int i;
        public int increase() {
            i = i + 1;
            return i;
        }
        public int getValue() {
            return i;
        }
    }
    public static class EntityVolatile implements EntityI{
        private volatile int i;
        public int increase() {
            i = i + 1;
            return i;
        }
        public int getValue() {
            return i;
        }
    }
    public static class EntitySyn implements EntityI {
        private int i;

        public synchronized int increase() {
            i = i + 1;
            return i;
        }

        public synchronized int getValue() {
            return i;
        }
    }
}