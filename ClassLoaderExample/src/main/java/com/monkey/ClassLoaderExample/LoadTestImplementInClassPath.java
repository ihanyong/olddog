package com.monkey.ClassLoaderExample;

import com.monkey.ClassLoaderExample.LoadTestInterface;

import java.text.MessageFormat;

/**
 * com.monkey.ClassLoaderExample.LoadTestImplementInClassPath
 *
 * @author yong.han
 * 2018/12/28
 */
public class LoadTestImplementInClassPath implements LoadTestInterface {

    @Override
    public void test(String string) {
        System.out.println(MessageFormat.format("hello , you send me the message of [ {0} ]", string));
    }
}
