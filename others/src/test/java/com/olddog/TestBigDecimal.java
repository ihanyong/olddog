package com.olddog;

import org.junit.Assert;
import org.junit.Test;

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
        BigInteger integer1 = BigInteger.valueOf(1);
        BigInteger integer2 = BigInteger.valueOf(2);
        BigInteger integer3 = integer1.add(integer2);

        assertEquals(BigInteger.valueOf(1), integer1);
        assertEquals(BigInteger.valueOf(2), integer2);
        assertEquals(BigInteger.valueOf(3), integer3);


    }
}
