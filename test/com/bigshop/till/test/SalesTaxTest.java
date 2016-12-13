package com.bigshop.till.test;

import com.bigshop.till.SalesTax;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class SalesTaxTest {

    //Only basic functionality is tested here
    //for Exercise specific tests please see ExerciseTest

    private static SalesTax salesTax;

    @BeforeClass
    public static void configureTest(){
        salesTax = new SalesTax("X", 12.34);
    }

    @Test
    public void getTaxCategory() throws Exception {
        assertEquals("X", salesTax.getTaxCategory());
    }

    @Test
    public void getRate() throws Exception {
        assertEquals(12.34, salesTax.getRate(), 0.01);
    }
}