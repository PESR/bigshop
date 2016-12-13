package com.bigshop.till;

public class MultiBuyOffer {

    //This class is used for the creation of MultiBuy offers such as buy 1 get 1 free etc.
    //How to use:
    //name: Can be anything - will appear on the statement to identify the offer
    //itemType: This is the itemType that the offer will apply to. Any Items with this itemType will be looked at.
    //multiplesOf: This is an int value which is enabling every nth item to be free. For BOGOF this will be set to 1, for buy 4 pay for 3 this is set to 4.
    //reducedBy: This is the number of items which will be credited per multipleOf. If this is set to 1. It means for every group of MultiplesOf, one will be credited.
    //itemToCredit: This is used to apply the credit. The price and tax information will be used for the credit

    private String name;
    private ItemType itemType;
    private int multiplesOf;
    private int reducedBy;
    private Item itemToCredit;

    public MultiBuyOffer(String name, ItemType itemType, int multiplesOf, int reducedBy, Item itemToCredit){
        this.name = name;
        this.itemType = itemType;
        this.multiplesOf = multiplesOf;
        this.reducedBy = reducedBy;
        this.itemToCredit = itemToCredit;
    }

    public String getName(){
        return this.name;
    }

    public ItemType getType(){
        return this.itemType;
    }

    public int getMultiplesOf(){
        return this.multiplesOf;
    }

    public int getReducedBy(){
        return this.reducedBy;
    }

    public Item getItemToCredit(){
        return this.itemToCredit;
    }
}
