package com.olddog.reflect;

import org.junit.Test;

import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

/**
 * ExecutableTest
 *
 * @author yong.han
 * 2019/7/17
 */
public class ExecutableTest {
    public static class Entity {
        public <T extends Long, V> void test1(T t, String name) {

            System.out.println("test1");
        }

    }

    @Test
    public void test() throws NoSuchMethodException {

        Method[] methods = Entity.class.getDeclaredMethods();
        for (Method mTest1 : methods) {

            Parameter[] parameters = mTest1.getParameters();

            System.out.println(Arrays.stream(parameters).map(Parameter::getName).collect(joining(",")));

            Type[] types = mTest1.getGenericParameterTypes();
            System.out.println(Arrays.stream(types).map(Type::getTypeName).collect(joining(",")));


            Class<?>[] parameterClasses = mTest1.getParameterTypes();
            System.out.println(Arrays.stream(parameterClasses).map(Class::getName).collect(joining(",")));



            System.out.println(mTest1.getDeclaringClass());
            System.out.println(((Method) mTest1).getReturnType());
        }



    }


}
