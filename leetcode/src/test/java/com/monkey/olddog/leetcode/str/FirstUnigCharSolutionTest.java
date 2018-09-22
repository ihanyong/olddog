package com.monkey.olddog.leetcode.str;

import com.monkey.olddog.leetcode.str.firstUniqChar.Solution;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author hanyong
 * @Date 2018/9/17
 */
public class FirstUnigCharSolutionTest {


    @Test
    public void test_1() {
        Solution solution = new Solution();

        assertEquals(0, solution.firstUniqChar("asdfgh"));
        assertEquals(2, solution.firstUniqChar("aaf"));
        assertEquals(3, solution.firstUniqChar("aaaf"));
        assertEquals(2, solution.firstUniqChar("loveleetcode"));
        assertEquals(0, solution.firstUniqChar("leetcode"));



    }

}
