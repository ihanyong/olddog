package old.dog.test.concurrency;

/**
 * Created by HanYong on 2018/7/19.
 */
public class Counter2 {
    private int counter;

    public int getCounter() {
        return counter++;
    }

}
