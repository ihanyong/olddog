package com.monkey.ClassLoaderExample;

/**
 * LoadTestImplement
 *
 * @author yong.han
 * 2018/12/29
 */
public class LoadTestImplement implements LoadTestInterface {
    @Override
    public void test(String string) {
        System.out.println("---这是个什么东西： " + string);

    }
}
