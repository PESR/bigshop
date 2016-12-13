package com.bigshop.till.test;

import com.bigshop.till.*;
import com.bigshop.till.exception.ExceededLegalLimit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasketTest {

    //Only basic functionality is tested here
    //for Exercise specific tests please see ExerciseTest

    private static Basket basket;
    private static Item item;


    @Before
    public void configureTest(){
        ItemType itemType;
        SalesTax salesTax;
        DB db;

        basket = new Basket();
        salesTax = new SalesTax("A", 10.00);
        itemType = new ItemType("SIM", salesTax, 10);
        item = new Item("SIM card", itemType, 12.00, "CHF");
        db = new DB();
        basket.setMultiBuyOffersList(db.getMultiBuyOffers());
    }

    @Test
    public void addItem() throws Exception {
        //Testing that no exception is hit
        basket.addItem(item);
    }

    @Test(expected = ExceededLegalLimit.class)
    public void addItemException() throws Exception {
        basket.addItem(item);
        basket.addItem(item);
        basket.addItem(item);
        basket.addItem(item);
        basket.addItem(item);
        basket.addItem(item);
        basket.addItem(item);
        basket.addItem(item);
        basket.addItem(item);
        basket.addItem(item);
        basket.addItem(item);
    }
}