package com.hanyong.jianzhioffer.question12;

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
        Solution solution = new Solution();


        Assert.assertEquals(Math.pow(0, 0), solution.Power(0, 0), 10);
        Assert.assertEquals(Math.pow(0, 1), solution.Power(0, 1),10);
        Assert.assertEquals(Math.pow(1, 0), solution.Power(1, 0),10);
        Assert.assertEquals(Math.pow(12.33, 20), solution.Power(12.33, 20),10);
        Assert.assertEquals(Math.pow(12.33, -20), solution.Power(12.33, -20),10);

    }

//    @Test
//    public void test1() {
//        // pass
//        Assert.assertEquals(6.1, 6.0, 0.1);
//        // error
//        Assert.assertEquals(6.01, 6.0, 0.01);
//    }
}
