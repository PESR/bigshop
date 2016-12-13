package com.bigshop.till.test;

import com.bigshop.till.Basket;
import com.bigshop.till.DB;
import com.bigshop.till.exception.ExceededLegalLimit;
import com.bigshop.till.exception.InvalidItem;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExcerciseTest {

    private static DB db;
    private Basket basket;

    @Before
    public void configureTest(){
        db = new DB();
        basket = new Basket();
    }

    @Test
    public void taxTest1(){
        //12% Tax is added to a items which are not insurance

        try {
            basket.addItem(db.getItem("SIM card"));
        }catch (InvalidItem | ExceededLegalLimit e){
            fail();
        }
        basket.applySalesTax();
        assertEquals(2.40,basket.getTaxEntryList().get(0).getTaxAmount(), 0.01);
    }

    @Test
    public void taxTest2(){
        //0% Tax is added to Insurance Products

        try {
            basket.addItem(db.getItem("phone insurance"));
        }catch (InvalidItem | ExceededLegalLimit e){
            fail();
        }
        basket.applySalesTax();
        assertEquals(0.00,basket.getTaxEntryList().get(0).getTaxAmount(), 0.01);
    }

    @Test
    public void taxTest3(){
        // Mixed scenario - shopping items excl. Insurance Products are taxed at 12%

        try {
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("phone insurance"));
        }catch (InvalidItem | ExceededLegalLimit e){
            fail();
        }
        basket.applySalesTax();
        assertEquals(2.40,basket.getTaxEntryList().get(0).getTaxAmount(), 0.01);
    }

    @Test
    public void bogofOfferTest1(){
        // BOGOF offer is being applied only to multiples of 2

        try {
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
        }catch (InvalidItem | ExceededLegalLimit e){
            fail();
        }
        basket.setMultiBuyOffersList(db.getMultiBuyOffers());
        basket.setDiscountOfferList(db.getDiscountOfferList());
        basket.applyOffers();
        //check that the description is listed in the offer list
        assertEquals("BOGOF SIM cards",basket.getOfferList().get(0).getName());
        //check that the amount is -20
        assertEquals(-20.00,basket.getOfferList().get(0).getPrice(), 0.01);
        //check that the offer was only applied once
        assertEquals(1,basket.getOfferList().size());
    }

    @Test
    public void bogofOfferTest2(){
        // BOGOF offer is being applied multiple times if more than one set of two SIM cards are found

        try {
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
        }catch (InvalidItem | ExceededLegalLimit e){
            fail();
        }
        basket.setMultiBuyOffersList(db.getMultiBuyOffers());
        basket.setDiscountOfferList(db.getDiscountOfferList());
        basket.applyOffers();
        //check that the description is listed in the offer list
        assertEquals("BOGOF SIM cards",basket.getOfferList().get(0).getName());
        assertEquals("BOGOF SIM cards",basket.getOfferList().get(1).getName());
        //check that the amount is -20
        assertEquals(-20.00,basket.getOfferList().get(0).getPrice(), 0.01);
        assertEquals(-20.00,basket.getOfferList().get(1).getPrice(), 0.01);
        //check that the offer was applied twice
        assertEquals(2,basket.getOfferList().size());
    }

    @Test
    public void discountOfferTest1(){
        // Insurance is discounted by 20% when earphones are purchased  - test with wireless earphones

        try {
            basket.addItem(db.getItem("phone insurance"));
            basket.addItem(db.getItem("wireless earphones"));
        }catch (InvalidItem | ExceededLegalLimit e){
            fail();
        }
        basket.setMultiBuyOffersList(db.getMultiBuyOffers());
        basket.setDiscountOfferList(db.getDiscountOfferList());
        basket.applyOffers();

        //check that the correct name is applied
        assertEquals("20% off Insurance",basket.getOfferList().get(0).getName());
        //check that the amount is -20
        assertEquals(-24.00,basket.getOfferList().get(0).getPrice(), 0.01);
        //check that the offer was only applied once
        assertEquals(1,basket.getOfferList().size());
    }

    @Test
    public void discountOfferTest2(){
        //Insurance is discounted by 20% when earphones are purchased - test with wired earphones

        try {
            basket.addItem(db.getItem("phone insurance"));
            basket.addItem(db.getItem("wired earphones"));
        }catch (InvalidItem | ExceededLegalLimit e){
            fail();
        }
        basket.setMultiBuyOffersList(db.getMultiBuyOffers());
        basket.setDiscountOfferList(db.getDiscountOfferList());
        basket.applyOffers();

        //check that the correct name is applied
        assertEquals("20% off Insurance",basket.getOfferList().get(0).getName());
        //check that the amount is -20
        assertEquals(-24.00,basket.getOfferList().get(0).getPrice(), 0.01);
        //check that the offer was only applied once
        assertEquals(1,basket.getOfferList().size());
    }

    @Test
    public void discountOfferTest3(){
        // Insurance is not discounted when no earphones are purchased

        try {
            basket.addItem(db.getItem("phone insurance"));
        }catch (InvalidItem | ExceededLegalLimit e){
            fail();
        }
        basket.setMultiBuyOffersList(db.getMultiBuyOffers());
        basket.setDiscountOfferList(db.getDiscountOfferList());
        basket.applyOffers();

        assertEquals(0,basket.getOfferList().size());
    }

    @Test
    public void discountOfferTest4(){
        //Insurance is discounted by 20% when earphones are purchased - test with multiple insurance purchases
        //All insurance products are expected to be discounted. Even if only one set of earphones are purchased

        try {
            basket.addItem(db.getItem("phone insurance"));
            basket.addItem(db.getItem("phone insurance"));
            basket.addItem(db.getItem("wired earphones"));
        }catch (InvalidItem | ExceededLegalLimit e){
            fail();
        }
        basket.setMultiBuyOffersList(db.getMultiBuyOffers());
        basket.setDiscountOfferList(db.getDiscountOfferList());
        basket.applyOffers();

        //check that the correct name is applied
        assertEquals("20% off Insurance",basket.getOfferList().get(0).getName());
        assertEquals("20% off Insurance",basket.getOfferList().get(1).getName());
        //check that the amount is -20
        assertEquals(-24.00,basket.getOfferList().get(0).getPrice(), 0.01);
        assertEquals(-24.00,basket.getOfferList().get(1).getPrice(), 0.01);
        //check that the offer was applied twice
        assertEquals(2,basket.getOfferList().size());
    }

    @Test
    public void fourFor3OfferTest1(){
        // When 4 phone cases are purchased, 1 is free - offer is only applied to multiples of 4

        try {
            basket.addItem(db.getItem("phone case"));
            basket.addItem(db.getItem("phone case"));
            basket.addItem(db.getItem("phone case"));
            basket.addItem(db.getItem("phone case"));
            basket.addItem(db.getItem("phone case"));
        }catch (InvalidItem | ExceededLegalLimit e){
            fail();
        }
        basket.setMultiBuyOffersList(db.getMultiBuyOffers());
        basket.setDiscountOfferList(db.getDiscountOfferList());
        basket.applyOffers();
        //check that the description is listed in the offer list
        assertEquals("4 for 3 phone cases",basket.getOfferList().get(0).getName());
        //check that the amount is -10
        assertEquals(-10.00,basket.getOfferList().get(0).getPrice(), 0.01);
        //check that the offer was only applied once
        assertEquals(1,basket.getOfferList().size());
    }

    @Test
    public void fourFor3OfferTest2(){
        // When 4 phone cases are purchased, 1 is free

        try {
            basket.addItem(db.getItem("phone case"));
            basket.addItem(db.getItem("phone case"));
            basket.addItem(db.getItem("phone case"));
            basket.addItem(db.getItem("phone case"));
            basket.addItem(db.getItem("phone case"));
            basket.addItem(db.getItem("phone case"));
            basket.addItem(db.getItem("phone case"));
            basket.addItem(db.getItem("phone case"));
        }catch (InvalidItem | ExceededLegalLimit e){
            fail();
        }
        basket.setMultiBuyOffersList(db.getMultiBuyOffers());
        basket.setDiscountOfferList(db.getDiscountOfferList());
        basket.applyOffers();
        //check that the offer was applied twice
        assertEquals(2,basket.getOfferList().size());
    }

    @Test
    public void legalLimitTest1(){
        // Allow the purchase of 10 sim cards
        try {
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
        }catch (InvalidItem | ExceededLegalLimit e){
            fail();
        }
    }

    @Test
    public void legalLimitTest2(){
        // Throw an ExceededLegalLimit exception when more than 10 SIM cards are attempted to be added to the basket
        try {
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            basket.addItem(db.getItem("SIM card"));
            fail();
        }catch (InvalidItem e){
            fail();
        }catch (ExceededLegalLimit e){
            //expected behaviour
        }
    }
}
