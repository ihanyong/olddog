package com.hanyong.jianzhioffer.question20;

import org.junit.Assert;
import org.junit.Test;

/**
 * SolutionMiniOfStackTest
 *
 * @author yong.han
 * 2019/4/11
 */
public class SolutionMiniOfStackTest {
    @Test
    public void test1() {
        SolutionMiniOfStack s = new SolutionMiniOfStack();
        s.push(300);
        Assert.assertEquals(300, s.min());

        s.push(300);
        Assert.assertEquals(300, s.min());

        s.push(299);
        Assert.assertEquals(299, s.min());

        s.push(300);
        Assert.assertEquals(299, s.min());


        s.pop();
        Assert.assertEquals(299, s.min());


        s.push(1);
        s.push(3);
        s.push(50);
        Assert.assertEquals(1, s.min());

    }
}
