package com.monkey.olddog.cglib;/**
 * Created by hanyong on 2017/5/23.
 */

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @author hanyong
 * @Date 2017/5/23
 */
public class CglibTest {

    public static void main(String[] args) {
        MethodInterceptor MethodInterceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    methodProxy.invokeSuper(o, objects);
                    return "hello world! method interceptor.";
                } else {
                    return methodProxy.invokeSuper(o, objects);
                }
            }
        };


        InvocationHandler invocationHandler  = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
//                    method.invoke(proxy, args);
                    return "hello world! invocation handler.";

                } else {
//                    return method.invoke(proxy, args);
                    throw new RuntimeException("do not know what to do.");
                }

            }
        };

        testCallback(MethodInterceptor);
        testCallback(invocationHandler);

    }


    public static void testCallback(Callback callback) {

        System.out.println("===========test + " + callback.getClass());

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(CglibTestI.class);

        enhancer.setCallback(callback);

        CglibTestI client = (CglibTestI) enhancer.create();
        System.out.println(client.get());
        System.out.println(client.toString());
        System.out.println(client.hashCode());

    }


    public static class CglibTestI {
        String get() {
            System.out.println("invoking the origin method.");
            return "the origin rtn";
        }
    }


}
