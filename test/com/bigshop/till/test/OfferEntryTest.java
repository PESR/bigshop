package com.bigshop.till.test;

import com.bigshop.till.ItemType;
import com.bigshop.till.OfferEntry;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class OfferEntryTest {

    //Only basic functionality is tested here
    //for Exercise specific tests please see ExerciseTest

    private static OfferEntry offerEntry;
    private static ItemType itemType;

    @BeforeClass
    public static void configureTest(){
        itemType = new ItemType(null, null);
        offerEntry = new OfferEntry("Item Name", itemType, 12.34, "CHF");
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Item Name", offerEntry.getName());
    }

    @Test
    public void getType() throws Exception {
        assertEquals(itemType, offerEntry.getType());
    }

    @Test
    public void getPrice() throws Exception {
        assertEquals(12.34, offerEntry.getPrice(), 0.01);
    }

    @Test
    public void getCurrency() throws Exception {
        assertEquals("CHF", offerEntry.getCurrency());
    }
}