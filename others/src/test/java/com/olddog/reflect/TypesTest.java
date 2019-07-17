package com.olddog.reflect;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

/**
 * TypesTest
 *
 * @author yong.han
 * 2019/7/17
 */
public class TypesTest {


    public static class Entity {
        private Map<String, Integer> map = new HashMap<>();

    }


    @Test
    public void testParameterizedType() throws NoSuchFieldException {

        ParameterizedType type = (ParameterizedType) Entity.class.getDeclaredField("map").getGenericType();

        Type[] actualTypeArguments = type.getActualTypeArguments();
        Type rawType = type.getRawType();
        Type OwnerType = type.getOwnerType();

        System.out.println(type);
        System.out.println(actualTypeArguments);
        System.out.println(rawType);
        System.out.println(OwnerType);

        Assert.assertTrue(type instanceof ParameterizedType);

    }

    @Test
    public void testTypeVariable() {
        TypeVariable<Class<Map>>[] typeVariables = Map.class.getTypeParameters();


        for (TypeVariable<Class<Map>> typeVariable : typeVariables) {
            System.out.println(typeVariable);

        }




    }

    @Test
    public void test() {
        int[] intArray = new int[10];

//        GenericArrayType type = intArray.getClass().;

    }

}
