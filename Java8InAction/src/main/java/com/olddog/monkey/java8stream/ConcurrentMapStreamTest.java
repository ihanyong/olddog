package com.olddog.monkey.java8stream;

import com.google.common.collect.MapMaker;
import sun.rmi.runtime.Log;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * ConcurrentMapStreamTest
 *
 * @author yong.han
 * 2019/1/10
 */
public class ConcurrentMapStreamTest {

    static ConcurrentMap<String, ConcurrentMap<String, Person>>  cache = null;

    public static void main(String[] args) {

        Map<String, Person> personMap = new HashMap<>();

        personMap.put("name-1", new Person("name-1", 1, "label-a"));
        personMap.put("name-2", new Person("name-2", 2, "label-b"));
        personMap.put("name-3", new Person("name-3", 3, "label-c"));
        personMap.put("name-4", new Person("name-4", 4, "label-b"));
        personMap.put("name-5", new Person("name-5", 5, "label-a"));
        personMap.put("name-6", new Person("name-6", 6, "label-a"));


        cache = personMap.entrySet().stream()
                .collect(Collectors.groupingByConcurrent(entry -> entry.getValue().getLabel(),
                        Collectors.toConcurrentMap(Map.Entry::getKey, Map.Entry::getValue)));


        logMap("init", cache);


        cache.get("label-a").remove("name-6");
        cache.get("label-a").put("name-7", new Person("name-7", 6, "label-a"));

        logMap("remove and put label-a's map", cache);


        cache.remove("label-c");
        cache.put("new-key", new ConcurrentHashMap<>());

        logMap("remove label-c and put new-key", cache);
    }

    public static void logMap(String msg, Map<String, ?> map) {
        System.out.println("======= "+ msg + "    ========" );
        if (null == map) {
            System.out.println("null");
        } else {
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                System.out.println(MessageFormat.format("{0}->{1}", entry.getKey(), entry.getValue()));
            }
        }
        System.out.println("=======   end     ========" );

    }


    public static class Person {

        public Person(String name, int age, String label) {
            this.name = name;
            this.age = age;
            this.label = label;

        }

        private String name;
        private String label;
        private int age;


        public String getName() {
            return name;
        }

        public String getLabel() {
            return label;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", label='" + label + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
