package com.monkey.reactivex1;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/7/5.
 */
public class FirstTest {
    public static void main(String[] args) {
        testCreate(5);

    }


    static Subscriber subscriber1 = new Subscriber<Integer>() {
        @Override
        public void onNext(Integer item) {
            if (item > 5) {
                throw new RuntimeException("test exception");
            }

            System.out.println("Next: " + item);
        }

        @Override
        public void onError(Throwable error) {
            System.err.println("Error: " + error.getMessage());
        }

        @Override
        public void onCompleted() {
            System.out.println("Sequence complete.");
        }
    };
    static Subscriber subscriber2 = new Subscriber<Integer>() {
        @Override
        public void onNext(Integer item) {
            if (item > 5) {
                throw new RuntimeException("2: test exception");
            }

            System.out.println("2: Next: " + item);
        }

        @Override
        public void onError(Throwable error) {
            System.err.println("2: Error: " + error.getMessage());
        }

        @Override
        public void onCompleted() {
            System.out.println("2: Sequence complete.");
        }
    };

    public static void testCreate(int count) {
        Observable observable = Observable.create((Subscriber<? super Integer> observer) ->{
                try {
                    if (!observer.isUnsubscribed()) {
                        for (int i = 1; i <= count; i++) {
                            observer.onNext(i);
                        }
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
         );



        observable.subscribe(subscriber1);
        observable.subscribe(subscriber2);
    }

    static void testDefer(int count) {
        Observable<Integer> observable = Observable.defer(()->Observable.create(
                subscriber -> {
                    System.out.println("Start" );
                    for (int i = 0; i < count; i++) {
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                }));

        observable.subscribe(subscriber1);
        observable.subscribe(subscriber2);



    }

}
