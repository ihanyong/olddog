package old.dog.test.concurrency.volatilet;

import old.dog.test.concurrency.ThreadUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by HanYong on 2018/7/20.
 *
 * 这个 volatile 是搞什么鬼的呢, 这里的用法应该不对, 运行的结果是错误的.
 * 这里实现了四种 entityI, 一个什么都不用的, 一个用volatile修饰状态, 一个使用synchronized个修饰所有方法, 一个使用 atomicInteger
 * 现在看只有synchronized和atomicInteger是ok的. todo 这个volatile 是用法还要进一步深究.
 *
 */
public class TestVolatileAndSynchronized {

    public static void main(String[] args) {
        int runTimes = 50000;
        run(runTimes, new EntityImpl());
        run(runTimes, new EntityVolatile());
        run(runTimes, new EntitySyn());
        run(runTimes, new EntityAtomic());
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

    public static class EntityImpl implements EntityI{
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

    public static class EntityAtomic implements EntityI {
        private AtomicInteger i = new AtomicInteger();

        @Override
        public int increase() {
            return i.addAndGet(1);
        }

        @Override
        public int getValue() {
            return i.get();
        }
    }

}
