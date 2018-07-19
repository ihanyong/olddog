package old.dog.test.concurrency;

/**
 * Created by HanYong on 2018/7/19.
 */
public class TestMain {
    public static void main(String[] args) {
        Person person = new Person();

        Thread thread0 = new Thread(()->{
            System.out.println("the first thread invoke the person.readCount(): " +person.readCount());
        });


        Thread thread1 = new Thread(()->{
            System.out.println("the second thread invoke the person.testSyn():" + person.testSyn());
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("the third thread invoke the person.increaseCount():" + person.increaseCount());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread0.setDaemon(false);
        thread1.setDaemon(false);
        thread2.setDaemon(false);

        thread0.setName("thread0");
        thread1.setName("thread1");
        thread2.setName("thread2");

        thread0.start();
        thread1.start();
        thread2.start();

    }
}
