package com.olddog.monkey.hackerrank;/**
 * Created by hanyong on 2017/8/3.
 */

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author hanyong
 * @Date 2017/8/3
 */
public class MainTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);



        int i = scanner.nextInt();
        double d = scanner.nextDouble();
        String s = scanner.nextLine();


        System.out.println(i + 4);


        System.out.println((new DecimalFormat("#.0").format(d +1.1)));

        System.out.println("hello " + s);

    }
}
