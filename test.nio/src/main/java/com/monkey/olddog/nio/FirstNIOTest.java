package com.monkey.olddog.nio;/**
 * Created by hanyong on 2018/7/16.
 */

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author hanyong
 * @Date 2018/7/16
 */
public class FirstNIOTest {

    public static void main(String[] args) {
        Path currentDir = Paths.get("");
        Path abs = currentDir.toAbsolutePath();

        System.out.println(abs.toString());
        System.out.println(abs.getFileName());
        System.out.println(abs.getRoot().toString());
        System.out.println(abs.getParent().toString());

    }

}
