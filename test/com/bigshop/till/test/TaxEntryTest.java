package com.bigshop.till.test;

import com.bigshop.till.SalesTax;
import com.bigshop.till.TaxEntry;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaxEntryTest {

    //Only basic functionality is tested here
    //for Exercise specific tests please see ExerciseTest

    private static TaxEntry taxEntry;
    private static TaxEntry taxEntry2;

    @BeforeClass
    public static void configureTest(){
        taxEntry = new TaxEntry("X",12.00,1234.00);
        taxEntry2 = new TaxEntry("Z",12.00,1234.00);
    }

    @Test
    public void getTaxCategory() throws Exception {
        assertEquals("X", taxEntry.getTaxCategory());
    }

    @Test
    public void getRate() throws Exception {
        assertEquals(12.00, taxEntry.getRate(), 0.01);
    }

    @Test
    public void getPurchaseAmount() throws Exception {
        assertEquals(1234.00, taxEntry.getPurchaseAmount(), 0.01);
    }

    @Test
    public void getTaxAmount() throws Exception {
        assertEquals(148.08, taxEntry.getTaxAmount(), 0.01);
    }

    @Test
    public void compareTo() throws Exception {
        assertEquals(2, taxEntry2.compareTo(taxEntry));

    }
}