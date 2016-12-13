package com.bigshop.till.test;

import com.bigshop.till.DiscountOffer;
import com.bigshop.till.ItemType;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiscountOfferTest {

    //Only basic functionality is tested here
    //for Exercise specific tests please see ExerciseTest

    private static DiscountOffer discountOffer;
    private static ItemType itemType;
    private static ItemType itemType2;

    @BeforeClass
    public static void configureTest(){
        itemType = new ItemType(null, null,0);
        itemType2 = new ItemType(null, null,0);
        discountOffer = new DiscountOffer("Discount Offer Name",itemType, 20.00, itemType2);
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Discount Offer Name", discountOffer.getName());
    }

    @Test
    public void getItemTypeToDiscount() throws Exception {
        assertEquals(itemType, discountOffer.getItemTypeToDiscount());
    }

    @Test
    public void getDiscountRate() throws Exception {
        assertEquals(20.00, discountOffer.getDiscountRate(), 0.01);
    }

    @Test
    public void getWhenBoughtItemType() throws Exception {
        assertEquals(itemType2, discountOffer.getWhenBoughtItemType());
    }

}