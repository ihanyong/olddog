package com.hanyong.jianzhioffer.question2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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


        assertEquals("", solution.replaceSpace(new StringBuffer("")));
        assertEquals("%20", solution.replaceSpace(new StringBuffer(" ")));
        assertEquals("%20%20", solution.replaceSpace(new StringBuffer("  ")));
        assertEquals("a%20b", solution.replaceSpace(new StringBuffer("a b")));
        assertEquals("ab%20", solution.replaceSpace(new StringBuffer("ab ")));
        assertEquals("%20ab", solution.replaceSpace(new StringBuffer(" ab")));
        assertEquals("%20ab%20", solution.replaceSpace(new StringBuffer(" ab ")));

    }
}
