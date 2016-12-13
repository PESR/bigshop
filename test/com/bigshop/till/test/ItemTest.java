package com.bigshop.till.test;

import com.bigshop.till.Item;
import com.bigshop.till.ItemType;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ItemTest {

    //Only basic functionality is tested here
    //for Exercise specific tests please see ExerciseTest

    private static Item item;
    private static ItemType itemType;

    @BeforeClass
    public static void configureTest(){
        itemType = new ItemType(null, null);
        item = new Item("Item Name", itemType, 12.34, "CHF");
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Item Name", item.getName());
    }

    @Test
    public void getType() throws Exception {
        assertEquals(itemType, item.getType());
    }

    @Test
    public void getPrice() throws Exception {
        assertEquals(12.34, item.getPrice(), 0.01);
    }

    @Test
    public void getCurrency() throws Exception {
        assertEquals("CHF", item.getCurrency());
    }

}