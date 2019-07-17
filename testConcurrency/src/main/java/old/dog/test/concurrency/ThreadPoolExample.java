package old.dog.test.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadPoolExample
 *
 * @author yong.han
 * 2019/6/3
 */
public class ThreadPoolExample {


    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(()->System.out.println("task 1"));
        executor.submit(()->System.out.println("task 2"));
        executor.submit(()->System.out.println("task 3"));
        executor.submit(()->System.out.println("task 4"));
        executor.submit(()->System.out.println("task 5"));
        executor.submit(()->System.out.println("task 6"));

        executor.shutdown();
    }
}
