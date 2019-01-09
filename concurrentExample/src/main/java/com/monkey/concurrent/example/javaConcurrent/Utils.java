package com.monkey.concurrent.example.javaConcurrent;

import java.text.MessageFormat;

/**
 * Utils
 *
 * @author yong.han
 * 2019/1/9
 */
public class Utils {

    public static void log(String pattern, Object ... arguments) {
        System.out.println(MessageFormat.format(pattern, arguments));
    }
}
