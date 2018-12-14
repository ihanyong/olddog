package com.monkey.prometheus.example;


import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * MyClass
 *
 * @author yong.han
 * 2018/12/5
 */
public class MyClass {
    static final Counter counter = Counter.build().name("han_test_counter").help("test ccounter of han").labelNames("type").register();

    static final Histogram histogram_1 = Histogram.build().buckets(0.2,0.4,0.6,0.8,1.0).name("han_test_histogram_1").help("test histogram of han 1").labelNames("name").register();
    static final Histogram histogram_2 = Histogram.build().buckets(0.2,0.4,0.6,0.8,1.0).name("han_test_histogram_2").help("test histogram of han 2").labelNames("name").register();
    static final Histogram histogram_3 = Histogram.build().buckets(0.2,0.4,0.6,0.8,1.0).name("han_test_histogram_3").help("test histogram of han 3").labelNames("name").register();


    static final Gauge gauge = Gauge.build().name("han_test_gauge_1").help("help").register();

    HTTPServer  server;

    public void start() throws IOException {
        server = new HTTPServer(8089);

    }

    public void stop() {
        if (server != null) {
            server.stop();
        }
    }

    void process() {
//        counter.labels("han_g").inc();
//        histogram.labels("han_y").time(MyClass::process0);
//        histogram_1.labels("han_y 1").observe((long) (Math.random()));
//        histogram_2.labels("han_y 2").observe((long) (Math.random()));
//        histogram_3.labels("han_y 3").observe((long) (Math.random()));


        gauge.set(Math.random());

    }

//    static void process0() {
//        try {
//            long sleep = (long) (Math.random()*1000);
//            System.out.println(MessageFormat.format("{0} sleep for {1} ms.", Thread.currentThread().getName(), sleep));
//            Thread.sleep(sleep);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) throws IOException, InterruptedException {

        MyClass my = new MyClass();

        my.start();

        for (int i = 0; i < 100000; i++) {
            my.process();
            Thread.sleep((long) (Math.random()*1000));
        }

        Thread.sleep(50000000);
        Runtime.getRuntime().addShutdownHook(new Thread(my::stop));
    }

}
