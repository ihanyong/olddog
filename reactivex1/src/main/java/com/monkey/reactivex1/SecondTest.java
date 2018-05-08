package com.monkey.reactivex1;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/7/10.
 */
public class SecondTest {

    static void testInterval() throws InterruptedException {
        Observable<Long> observable = Observable.interval(0, 1, TimeUnit.SECONDS);

        Action1<Long> action1 = next -> {
            if (next < 5) {
                System.out.println("the action first: " + next);
            }
        };
        Action1<Long> action2 = next -> {
            System.out.println("the action second: " + next);
        };

        observable.subscribe(action1);
        Thread.sleep(6000);
        observable.subscribe(action2);

        Thread.sleep(60000);
    }


    static void testCreate() throws InterruptedException {
        Observable<Long> observable =  Observable.defer(()->Observable.create((Subscriber<? super Long> subscreber)  -> {
            for (Long i = 0L; i < 20; i++) {
                subscreber.onNext(i);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }));

        observable.subscribe(next -> System.out.println("first:" + next));

        Thread.sleep(500);

        observable.subscribe(next -> System.out.println("second:" + next));

        Thread.sleep(200000);



    }


    public static void main(String[] args) throws InterruptedException {
        testCreate();
    }

}
