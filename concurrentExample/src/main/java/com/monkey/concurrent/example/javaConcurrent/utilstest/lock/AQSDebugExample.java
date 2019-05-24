package com.monkey.concurrent.example.javaConcurrent.utilstest.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * AQSDebugExample
 *
 * @author yong.han
 * 2019/4/30
 */
public class AQSDebugExample {


    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);


        lock.lock();
        lock.lock();

        new Thread(lock::lock, "sub-thread-0").start();
        new Thread(lock::lock, "sub-thread-1").start();
        new Thread(lock::lock, "sub-thread-2").start();
        new Thread(lock::lock, "sub-thread-3").start();


        lock.unlock();
        lock.unlock();
        lock.unlock();

    }
}
