package old.dog.test.concurrency;

/**
 * Created by HanYong on 2018/7/20.
 */
public class ThreadUtil {

    public static Thread newDaemonThread(String name, Runnable runnable) {
        return newThread(name, false, runnable);
    }
    public static Thread newThread(String name,boolean daemon, Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setDaemon(false);
        thread.setName(name);
        return thread;
    }
}
