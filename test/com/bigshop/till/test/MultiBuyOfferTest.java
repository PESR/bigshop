package com.bigshop.till.test;

import com.bigshop.till.Item;
import com.bigshop.till.ItemType;
import com.bigshop.till.MultiBuyOffer;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class MultiBuyOfferTest {

    //Only basic functionality is tested here
    //for Exercise specific tests please see ExerciseTest

    private static Item item;
    private static ItemType itemType;
    private static MultiBuyOffer multiBuyOffer;

    @BeforeClass
    public static void configureTest() {
        item = new Item("Item Name", null, 12.34, "CHF");
        itemType = new ItemType(null, null);
        multiBuyOffer = new MultiBuyOffer("MultiBuyOffer Name",itemType, 3, 1, item);
    }

    @Test
    public void getName() throws Exception {
        assertEquals("MultiBuyOffer Name", multiBuyOffer.getName());
    }

    @Test
    public void getType() throws Exception {
        assertEquals(itemType, multiBuyOffer.getType());
    }

    @Test
    public void getMultiplesOf() throws Exception {
        assertEquals(3,multiBuyOffer.getMultiplesOf());
    }

    @Test
    public void getReducedBy() throws Exception {
        assertEquals(1, multiBuyOffer.getReducedBy());
    }

    @Test
    public void getItemToCredit() throws Exception {
        assertEquals(item, multiBuyOffer.getItemToCredit());
    }
}