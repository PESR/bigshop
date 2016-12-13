package com.bigshop.till;


public class DiscountOffer {

    //This class is used for the creation of Discount offers such as buy 20% off insurance if you buy another product etc.
    //How to use:
    //name: Can be anything - will appear on the statement to identify the offer
    //itemTypeToDiscount: This is the itemType that items which are to be discounted are.
    //discountRate: This is the discount percentage which is to be applied
    //whenBoughtItemType: This is the itemType which triggers the discount. If an item with this type is in the basket. Items with the itemTypeToDiscount will be discounted.

    private final String name;
    private final ItemType itemTypeToDiscount;
    private final double discountRate;
    private final ItemType whenBoughtItemType;

    public DiscountOffer(String name, ItemType itemTypeToDiscount, double discountRate, ItemType whenBoughtItemType){
        this.name = name;
        this.itemTypeToDiscount = itemTypeToDiscount;
        this.discountRate = discountRate;
        this.whenBoughtItemType = whenBoughtItemType;
    }

    public String getName(){
        return this.name;
    }

    public ItemType getItemTypeToDiscount(){
        return this.itemTypeToDiscount;
    }

    public double getDiscountRate(){
        return this.discountRate;
    }

    public ItemType getWhenBoughtItemType(){
        return this.whenBoughtItemType;
    }
}
