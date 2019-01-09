package com.monkey.ronmano.reflections;

import com.google.common.base.Joiner;
import org.reflections.Reflections;

import java.util.Set;

/**
 * reflections_main_1
 *
 * @author yong.han
 * 2018/12/27
 */
public class reflections_main_1 {
    public static void main(String[] args) {
        Reflections reflections = new Reflections("com.monkey.ronmano.reflections");
        Set<Class<? extends ParentClass>> clsSet = reflections.getSubTypesOf(ParentClass.class);

        System.out.println(Joiner.on(",").join(clsSet));

    }
}
