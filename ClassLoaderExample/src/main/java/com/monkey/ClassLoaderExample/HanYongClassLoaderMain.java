package com.monkey.ClassLoaderExample;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * HanYongClassLoaderMain
 *
 * @author yong.han
 * 2018/12/28
 */
public class HanYongClassLoaderMain {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException {

        // LoadTestImplementInClassPath
//        LoadTestInterface service = new LoadTestImplementInClassPath();
//        service.test("hahah");


//        ClassLoader classLoader = new MyClassLoader(Thread.currentThread().getContextClassLoader());
        ClassLoader classLoader = new MyClassLoader(null);
        try {
            Class<?> cls = classLoader.loadClass("com.monkey.ClassLoaderExample.LoadTestImplement");

            Object service = cls.newInstance();

//            service.test("我哩个叉， 这可以呀 ");
            invokeTest(service, "我哩个叉， 这可以呀");


            ClassLoader parentCl = HanYongClassLoaderMain.class.getClassLoader();
            Class<?> cls2 = parentCl.loadClass("com.monkey.ClassLoaderExample.LoadTestImplement");

            LoadTestInterface service2 = (LoadTestInterface) cls2.newInstance();
            invokeTest(service2, "never");


        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public static void invokeTest(Object target, String arg) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = target.getClass().getMethod("test", String.class);
        method.invoke(target, arg);
    }
}
