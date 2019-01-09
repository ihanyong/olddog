package com.monkey.concurrent.example.javaConcurrent.utilstest.semaphore;

import java.util.concurrent.Semaphore;

import static com.monkey.concurrent.example.javaConcurrent.Utils.log;

/**
 * SemaphoreTest
 *
 * @author yong.han
 * 2019/1/9
 */
public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(5);
        log("init permits: {0}", semaphore.availablePermits());

        semaphore.release();
        log("permits after release one: {0}", semaphore.availablePermits());
        semaphore.acquire();
        log("permits after acquire one: {0}", semaphore.availablePermits());
        semaphore.acquire(5);
        log("permits after acquire 5: {0}", semaphore.availablePermits());

        semaphore.release(6);
        log("permits after release 6: {0}", semaphore.availablePermits());

        log("drain permits: {0}", semaphore.drainPermits());
        log("permits after drain: {0}", semaphore.availablePermits());
        log("drain permits: {0}", semaphore.drainPermits());


    }

}
