package com.monkey.reactivex2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2017/7/4.
 */
public class FirstTest {
    public static void main(String[] args) {

//        hello("hanyong", "test", "youyou");
//        hello2("hanyong", "test", "youyou");
//        hello3("hanyong", "test", "youyou", "");

        testCreate(10);

    }

    public static void testCreate(int count) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                try {
                    for (int i = 1; i <= count; i++) {
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }).subscribe(
                next -> {if(next>5) throw new RuntimeException("test on error"); System.out.println("on next: " + next);},
                throwable -> System.out.println("on error: " + throwable.getMessage()),
                () -> System.out.println("the Observable is Complete."));
    }


    public static void hello(String... names) {

        Observable.fromArray(names).subscribe(s -> System.out.println("hello " + s));

    }


    public static void hello2(String... names) {
        Observable.fromArray(names).skip(1).take(2).map(s -> "hello " + s + " --").subscribe(s -> System.out.println(s));
    }
    public static void hello3(String... names) {
        Observable.fromArray(names).map(s -> { if(s.equals("")) throw new Exception(); return "hello " + s + " ***";}).onErrorReturn(throwable -> throwable.getMessage()).subscribe(s -> System.out.println(s));
    }
}
