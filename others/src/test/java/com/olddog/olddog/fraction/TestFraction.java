package com.olddog.olddog.fraction;

import olddog.fraction.Fraction;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * TestFraction
 *
 * @author yong.han
 * 2018/10/11
 */
public class TestFraction {

    @Test
    public void testNew() {
        Fraction a = new Fraction(2, 6);
        Fraction b = new Fraction(1, 3);


        assertEquals(1, a.getNumerator());
        assertEquals(3, a.getDenominator());
        assertEquals(1, b.getNumerator());
        assertEquals(3, b.getDenominator());

        assertEquals("Fraction{1/3}", a.toString());
        assertEquals("Fraction{1/3}", b.toString());


    }


    @Test
    public void testAdd() {
        Fraction f_1_6 = new Fraction(1, 6);
        Fraction f_2_3 = new Fraction(2, 3);

        Fraction result = f_1_6.add(f_2_3);



        assertEquals(1, f_1_6.getNumerator());
        assertEquals(6, f_1_6.getDenominator());
        assertEquals("Fraction{1/6}", f_1_6.toString());

        assertEquals(2, f_2_3.getNumerator());
        assertEquals(3, f_2_3.getDenominator());
        assertEquals("Fraction{2/3}", f_2_3.toString());

        assertEquals(5, result.getNumerator());
        assertEquals(6, result.getDenominator());
        assertEquals("Fraction{5/6}", result.toString());

    }

    @Test
    public void testSub() {
        Fraction f_1_6 = new Fraction(1, 6);
        Fraction f_2_3 = new Fraction(2, 3);

        Fraction result = f_2_3.sub(f_1_6);



        assertEquals(1, f_1_6.getNumerator());
        assertEquals(6, f_1_6.getDenominator());
        assertEquals("Fraction{1/6}", f_1_6.toString());

        assertEquals(2, f_2_3.getNumerator());
        assertEquals(3, f_2_3.getDenominator());
        assertEquals("Fraction{2/3}", f_2_3.toString());

        assertEquals(1, result.getNumerator());
        assertEquals(2, result.getDenominator());
        assertEquals("Fraction{1/2}", result.toString());
    }

    @Test
    public void testSubToZero() {
        Fraction f_1_6 = new Fraction(1, 6);

        Fraction result = f_1_6.sub(f_1_6);



        assertEquals(1, f_1_6.getNumerator());
        assertEquals(6, f_1_6.getDenominator());
        assertEquals("Fraction{1/6}", f_1_6.toString());

        assertEquals(0, result.getNumerator());
        assertEquals(1, result.getDenominator());
        assertEquals("Fraction{0/1}", result.toString());
    }


}
