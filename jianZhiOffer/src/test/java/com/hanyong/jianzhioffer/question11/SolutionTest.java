package com.hanyong.jianzhioffer.question11;

import org.junit.Assert;
import org.junit.Test;

/**
 * SolutionTest
 *
 * @author yong.han
 * 2019/4/8
 */
public class SolutionTest {
    @Test
    public void test() {
        System.out.println(0xffff);

        Solution solution = new Solution();
        Assert.assertEquals(0, solution.NumberOf1(0x0));
        Assert.assertEquals(1, solution.NumberOf1(0x1));
        Assert.assertEquals(4, solution.NumberOf1(0xf));
        Assert.assertEquals(16, solution.NumberOf1(0xffff));
        Assert.assertEquals(16, solution.NumberOf1(214748367));
        Assert.assertEquals(32, solution.NumberOf1(-1));
        Assert.assertEquals(31, solution.NumberOf1(Integer.MAX_VALUE));
        Assert.assertEquals(31, solution.NumberOf1(-2));
    }
}
