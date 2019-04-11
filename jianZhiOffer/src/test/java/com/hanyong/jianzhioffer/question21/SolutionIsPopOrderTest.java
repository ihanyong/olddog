package com.hanyong.jianzhioffer.question21;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * SolutionIsPopOrderTest
 *
 * @author yong.han
 * 2019/4/11
 */
public class SolutionIsPopOrderTest {
    @Test
    public void test() {
        SolutionIsPopOrder s = new SolutionIsPopOrder();


        assertTrue(s.IsPopOrder(new int[]{}, new int[]{}));
        assertTrue(s.IsPopOrder(new int[]{1}, new int[]{1}));
        assertTrue(s.IsPopOrder(new int[]{1,2}, new int[]{2,1}));
        assertTrue(s.IsPopOrder(new int[]{1,2}, new int[]{1,2}));
        assertTrue(s.IsPopOrder(new int[]{1,2,3}, new int[]{3,2,1}));
        assertTrue(s.IsPopOrder(new int[]{1,2,3}, new int[]{2,3,1}));
        assertTrue(s.IsPopOrder(new int[]{1, 2, 3}, new int[]{2, 1, 3}));


        assertFalse(s.IsPopOrder(new int[]{1, 2, 3}, new int[]{3, 1, 2}));

        assertTrue(s.IsPopOrder(new int[]{1, 2, 3, 4}, new int[]{2, 1, 3, 4}));

    }
}
