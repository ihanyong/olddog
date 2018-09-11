package com.olddog.monkey.java8inaction.chapter1;/**
 * Created by hanyong on 2018/3/6.
 */

import lombok.Data;

/**
 * @author hanyong
 * @Date 2018/3/6
 */
@Data(staticConstructor = "of")
public class Apple {
    private final String color;
    private final int weight;

    public boolean isAccepted() {
        return "red".equals(getColor()) && getWeight() > 200;
    }
}
