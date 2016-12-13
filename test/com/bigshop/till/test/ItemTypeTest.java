package com.bigshop.till.test;

import com.bigshop.till.ItemType;
import com.bigshop.till.SalesTax;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTypeTest {

    //Only basic functionality is tested here
    //for Exercise specific tests please see ExerciseTest

    private static ItemType itemType;
    private static ItemType itemType2;
    private static SalesTax salesTax;

    @BeforeClass
    public static void configureTest(){
        salesTax = new SalesTax(null, 0);
        //Testing both constructors
        itemType = new ItemType("ItemType Name", salesTax);
        itemType2 = new ItemType("ItemType Name", salesTax, 123);
    }

    @Test
    public void getName() throws Exception {
        assertEquals("ItemType Name", itemType.getName());
        assertEquals("ItemType Name", itemType2.getName());
    }

    @Test
    public void getSalesTax() throws Exception {
        assertEquals(salesTax, itemType.getSalesTax());
        assertEquals(salesTax, itemType2.getSalesTax());
    }

    @Test
    public void getMaxAllowed() throws Exception {
        assertEquals(0, itemType.getMaxAllowed());
        assertEquals(123, itemType2.getMaxAllowed());
    }

    @Test
    public void getMaxEnforced() throws Exception {
        assertEquals(false, itemType.getMaxEnforced());
        assertEquals(true, itemType2.getMaxEnforced());
    }

}