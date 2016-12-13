package com.bigshop.till;

public class OfferEntry extends Item {

    //This class is used to enable the tracking of redemed offers

    public OfferEntry(String name, ItemType type, double price, String currency){
            super(name,type,price,currency);
    }
}
