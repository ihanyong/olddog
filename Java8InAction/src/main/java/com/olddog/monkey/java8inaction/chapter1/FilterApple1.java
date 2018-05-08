package com.olddog.monkey.java8inaction.chapter1;/**
 * Created by hanyong on 2018/3/6.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author hanyong
 * @Date 2018/3/6
 */
public class FilterApple1 {


    public static void main(String[] args) {
        testLambda();

    }
    public static void testLambda() {
        List<Apple> inventory = new ArrayList<>(20);
        inventory.add(Apple.of("green", 150));
        inventory.add(Apple.of("red", 200));
        inventory.add(Apple.of("red", 201));
        inventory.add(Apple.of("yellow", 200));
        inventory.add(Apple.of("yellow", 400));
        inventory.add(Apple.of("green", 201));

        System.out.println(filter(inventory, a-> "green".equals(a.getColor())));
        System.out.println(filter(inventory, a -> a.getWeight() > 200));
        System.out.println(filter(inventory, Apple::isAccepted));
        System.out.println(inventory.stream().filter(a -> a.getWeight() > 200).collect(Collectors.toList()));
    }


    static  <T> List<T> filter(Collection<T> c, Predicate<T> p) {
        List<T> result = new ArrayList<T>(c.size());

        for (T t : c) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

}
