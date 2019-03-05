package com.olddog.monkey.newfuture;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * CompletableFutureTest
 *
 * @author yong.han
 * 2019/3/5
 */
public class CompletableFutureTest {

    public static Shop shop = new Shop(100);

    private static String[] products = new String[]{
            "a"                                                                                  // 1
            ,"b","c","d"                                                                         // 4
            ,"e"                                                                                 // 5
            ,"f","e","f"                                                                         // 8
            ,"g"                                                                                 // 9
            ,"e","w","4","y","t","o","w","n","h","u","e","w","4","y","t","o","w","n","h","u"     // 29
            ,"y"                                                                                 // 30
            ,"e","w","4","y","t","o","w","n","h","u","e","w","4","y","t","o","w","n","h","u"     // 50
            ,"e","w","4","y","t","o","w","n","h","u","e","w","4","y","t","o","w","n","h","u"     // 70
            ,"e","w","4","y","t","o","w","n","h","u","e","w","4","y","t","o","w","n","h","u"     // 90
            ,"e","w","4","y","t","o","w","n","h","u","e","w","4","y","t","o","w","n","h","u"     // 110
    };

    public static void main(String[] args) {
        Map<String, Runnable> toRunMap = new LinkedHashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        toRunMap.put("DefaultPriceFetcher", () -> new DefaultPriceFetcher().fetchPrice(products));
        toRunMap.put("StreamPriceFetcher(no parallel)", () -> new StreamPriceFetcher(false).fetchPrice(products));
        toRunMap.put("StreamPriceFetcher(with parallel)", () -> new StreamPriceFetcher(true).fetchPrice(products));
        toRunMap.put("CompletableFuturePriceFetcher(default executor)", () -> new CompletableFuturePriceFetcher(null).fetchPrice(products));
        toRunMap.put("CompletableFuturePriceFetcher(executorPool of 100)", () -> new CompletableFuturePriceFetcher(executorService).fetchPrice(products));

        PrefermanceTester tester = new PrefermanceTester();
        Map<String, List<Long>> result = tester.invoke(toRunMap, 5, 10);


        executorService.shutdown();
        System.out.println("result for [" + String.join(",", products) + "]");

        result.entrySet().stream()
                .map(entry -> {
                        String key = entry.getKey();
                        Double average = entry.getValue().stream().collect(Collectors.averagingDouble(d -> d));
                        return Tuple2.of(key, average);
                })
                .forEach(System.out::println);
    }

    public static class Tuple2<F, S> {
        final F first;
        final S second;

        public Tuple2(F first, S second) {
            this.first = first;
            this.second = second;
        }

        public static <K, V> Tuple2<K, V> of(K first, V second) {
            return new Tuple2<>(first, second);
        }

        @Override
        public String toString() {
            return "{" +  first + " -> " + second + '}';
        }
    }

    public  static class  PrefermanceTester<T> {
        public Map<String, List<Long>> invoke(Map<String, Runnable> toRun, int preRunTimes, int testRunTime) {
            System.out.println("Begin to prerun for " + preRunTimes + " rounds.");
            for (int i = 0; i < preRunTimes; i++) {
                toRun.values().forEach(Runnable::run);
            }

            System.out.println("Begin to run for " + testRunTime + " rounds.");
            Map<String, List<Long>> result = new HashMap<>(toRun.size());
            for (int i = 0; i < testRunTime; i++) {
                System.out.println("running the " + (i+1) + " rounds...");

                toRun.forEach((name, runnable) -> {
                    result.compute(name, (key,list) -> {
                        if (list == null) {
                            list = Lists.newArrayList(innerRun(runnable));
                        } else {
                            list.add(innerRun(runnable));
                        }
                        return list;
                    });
                });
            }
            return result;
        }

        private long innerRun(Runnable runnable) {
            long start = System.currentTimeMillis();
            runnable.run();
            return System.currentTimeMillis() - start;
        }

    }

    public interface PriceFetcher {
        double[] fetchPrice(String[] productList);
    }
    public static class DefaultPriceFetcher implements PriceFetcher {
        @Override
        public double[] fetchPrice(String[] productList) {
            double[] result = new double[productList.length];

            for (int i = 0; i < productList.length; i++) {
                result[i] = shop.getPrice(productList[i]);
            }
            return new double[0];
        }
    }

    public static class StreamPriceFetcher implements PriceFetcher {
        private boolean parallel;

        public StreamPriceFetcher(boolean parallel) {
            this.parallel = parallel;
        }

        @Override
        public double[] fetchPrice(String[] productList) {
            if (parallel) {
                return Arrays.stream(productList).parallel().mapToDouble(shop::getPrice).toArray();
            } else {
                return Arrays.stream(productList).mapToDouble(shop::getPrice).toArray();
            }
        }
    }
    public static class CompletableFuturePriceFetcher implements PriceFetcher {
        private ExecutorService executorService;

        public CompletableFuturePriceFetcher(ExecutorService executorService) {
            this.executorService = executorService;
        }

        @Override
        public double[] fetchPrice(String[] productList) {
            CompletableFuture<Double>[] futures = new CompletableFuture[productList.length];
            for (int i = 0; i < productList.length; i++) {
                int finalI = i;
                if (null != executorService) {
                    futures[i] = CompletableFuture.supplyAsync(() -> shop.getPrice(productList[finalI]), executorService);
                } else {
                    futures[i] = CompletableFuture.supplyAsync(() -> shop.getPrice(productList[finalI]));
                }
            }
            CompletableFuture.allOf(futures).join();
            return Arrays.stream(futures).mapToDouble(CompletableFuture::join).toArray();
        }

    }


    public static class Shop {
        private long delay;

        public Shop(long delay) {
            this.delay = delay;
        }

        // 模拟了一个耗时一s 的操作
        public double getPrice(String product) {
            delay();
            return calculatePrice(product);
        }

        public double calculatePrice(String product) {
            if (null == product || product.length() == 0) {
                return 0;
            }
            return ThreadLocalRandom.current().nextDouble(10) * product.charAt(0);
        }

        private void delay() {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
