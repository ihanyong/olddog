package com.monkey.concurrent.example.javaConcurrent.utilstest.lock;

import com.monkey.concurrent.example.javaConcurrent.Utils;

import java.util.concurrent.locks.ReentrantLock;

import static com.monkey.concurrent.example.javaConcurrent.Utils.log;

/**
 * LockTest
 *
 * @author yong.han
 * 2019/1/9
 */
public class LockTest {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        log("main: init hold count : {0}", lock.getHoldCount());
        lock.lock();
        log("main: hold count after lock once : {0}", lock.getHoldCount());
        lock.lock();
        log("main: hold count after lock 2 times : {0}", lock.getHoldCount());


        lock.unlock();
        log("main: hold count after unlock 1 time : {0}", lock.getHoldCount());

        tryLockByOtherThread(1, lock);


//        Thread.sleep(2000);

        lock.unlock();
        log("main: hold count after unlock 2 time : {0}", lock.getHoldCount());

        tryLockByOtherThread(2, lock);

    }

    private static void tryLockByOtherThread(int i, ReentrantLock lock) {
        new Thread("sub-thread-" + i) {
            @Override
            public void run() {
                try{
                    lock.lock();
                    log("{0} got the lock , and hold count is :{1}", this.getName(), lock.getHoldCount() );
                } finally {
                    lock.unlock();
                }
            }
        }.start();
    }


}
