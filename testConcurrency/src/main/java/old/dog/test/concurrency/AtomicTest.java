package old.dog.test.concurrency;

import old.dog.test.concurrency.ThreadUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by HanYong on 2018/7/20.
 * 测试原子性的问题
 *
 * 这个 volatile 是搞什么鬼的呢, 这里的用法应该不对, 运行的结果是错误的.
 * 这里实现了四种 entityI, 一个什么都不用的, 一个用volatile修饰状态, 一个使用synchronized个修饰所有方法, 一个使用 atomicInteger
 * 现在看只有synchronized和atomicInteger是ok的. todo 这个volatile 是用法还要进一步深究.
 *
 */
public class AtomicTest {

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

            System.out.println(String.format("cost % 5d milliseconds, and the final result is % 7d - % 7d : %s",System.currentTimeMillis()-costTime, e.getValue(), e.getAnotherValue(),  e.getClass().getSimpleName()));
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

    }

    public interface EntityI{
        int increase();

        int getValue();

        int getAnotherValue();
    }

    public static class EntityImpl implements EntityI{
        private int i;
        private int a;
        @Override
        public int increase() {
            i = i + 1;
            a = a + 1;
            return i;
        }

        @Override
        public int getValue() {
            return i;
        }

        @Override
        public int getAnotherValue() {
            return a;
        }
    }
    public static class EntityVolatile implements EntityI{
        private volatile int i;
        private volatile int a;
        @Override
        public int increase() {
            i = i + 1;
            a = a + 1;
            return i;
        }
        @Override
        public int getValue() {
            return i;
        }

        @Override
        public int getAnotherValue() {
            return a;
        }
    }
    public static class EntitySyn implements EntityI {
        private int i;
        private int a;

        @Override
        public synchronized int increase() {
            i = i + 1;
            a = a + 1;
            return i;
        }
        @Override
        public synchronized int getValue() {
            return i;
        }

        @Override
        public synchronized int getAnotherValue() {
            return a;
        }
    }

    public static class EntityAtomic implements EntityI {
        private AtomicInteger i = new AtomicInteger();
        private AtomicInteger a = new AtomicInteger();


        @Override
        public int increase() {
            a.addAndGet(1);
            return i.addAndGet(1);
        }

        @Override
        public int getValue() {
            return i.get();
        }

        @Override
        public int getAnotherValue() {
            return a.get();
        }
    }

}
