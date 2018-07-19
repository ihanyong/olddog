package old.dog.test.concurrency;

/**
 * Created by HanYong on 2018/7/19.
 */
public class TestMain {

    public static void main(String[] args) {
        Counter2 counter = new Counter2();

        Runnable runnable = () -> {
            for (int i = 0; i < 2000; i++) {
                System.out.println(Thread.currentThread().getName() + " get counter: " + counter.getCounter());
            }
        };
        newThread("thread0",runnable).start();
        newThread("thread1",runnable).start();
        newThread("thread2",runnable).start();
    }


    public static void test1(String[] args) {
        Person person = new Person();

        Thread thread0 = newThread("thread0", ()->{
            System.out.println( Thread.currentThread().getName() + " invoke the person.readCount(): " +person.readCount());
        });


        Thread thread1 = newThread("thread1", ()->{
            System.out.println( Thread.currentThread().getName() + " invoke the person.testSyn():" + person.testSyn());
        });

        Thread thread2 = newThread("thread2", () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println( Thread.currentThread().getName() + " invoke the person.increaseCount():" + person.increaseCount());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread0.start();
        thread1.start();
        thread2.start();

    }


    private static Thread newThread(String threadName, Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setDaemon(false);
        thread.setName(threadName);
//        thread.start();
        return thread;
    }

}
