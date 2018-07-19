package old.dog.test.concurrency;

/**
 * Created by HanYong on 2018/7/19.
 */
public class Person {

    private int count;



    public int increaseCount() {
        return count++;
    }

    public synchronized int readCount() {
        while (count < 5) {
            try {
                System.out.println(Thread.currentThread().getName() + " sleep for 1 second.");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " get the count " + count);
        return count;
    }

    public synchronized String testSyn() {
        System.out.println(Thread.currentThread().getName() + " get the test ");
        return "yes, you got the test.";
    }

}
