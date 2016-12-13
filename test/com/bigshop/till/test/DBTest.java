package com.bigshop.till.test;

import com.bigshop.till.DB;
import com.bigshop.till.exception.InvalidItem;
import com.bigshop.till.exception.InvalidItemType;
import com.bigshop.till.exception.InvalidSalesTax;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBTest {

    //Only basic functionality is tested here
    //for Exercise specific tests please see ExerciseTest

    private static DB db;

    @BeforeClass
    public static void configureTest(){
        db = new DB();
    }

    @Test
    public void getItem() throws Exception {
        //Testing to see that no exception is hit
        db.getItem("SIM card");
    }

    @Test
    public void getItemType() throws Exception {
        //Testing to see that no exception is hit
        db.getItemType("SIM");
    }

    @Test
    public void getSalesTax() throws Exception {
        //Testing to see that no exception is hit
        db.getSalesTax("A");
    }

    @Test(expected = InvalidItem.class)
    public void getItemException() throws Exception {
        //Testing to see that exception is hit when invalid item is requested
        db.getItem("|||");
    }

    @Test(expected = InvalidItemType.class)
    public void getItemTypeException() throws Exception {
        //Testing to see that exception is hit when invalid itemtype is requested
        db.getItemType("|||");
    }

    @Test(expected = InvalidSalesTax.class)
    public void getSalesTaxException() throws Exception {
        //Testing to see that exception is hit when invalid tax is requested
        db.getSalesTax("|||");
    }
}