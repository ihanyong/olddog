package com.olddog;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * TestBigDecimal
 *
 * @author yong.han
 * 2018/9/20
 */
public class TestBigDecimal {

    @Test
    public void test() {
        BigDecimal integer1 = BigDecimal.valueOf(1);
        BigDecimal integer2 = BigDecimal.valueOf(2);
        BigDecimal integer3 = integer1.add(integer2);

        assertEquals(BigDecimal.valueOf(1), integer1);
        assertEquals(BigDecimal.valueOf(2), integer2);
        assertEquals(BigDecimal.valueOf(3), integer3);
    }

    @Test
    public void testScale() {
        BigDecimal n1 = BigDecimal.valueOf(1);
        BigDecimal n3 = BigDecimal.valueOf(3);

        BigDecimal n = n1.divide(n3,4, BigDecimal.ROUND_HALF_UP);

//        n1.scale()
        assertEquals(0, n1.scale());
        assertEquals(0, n3.scale());
        assertEquals(4, n.scale());

//        assertEquals(BigDecimal.ZERO, n);
        assertEquals(BigDecimal.valueOf(0.3333), n);

    }
    @Test
    public void testMax() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);

    }
}
