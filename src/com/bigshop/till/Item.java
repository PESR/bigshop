package com.bigshop.till;

public class Item {
    private String name;
    private ItemType type;
    private double price;
    private String currency;

    public Item(String name, ItemType type, double price, String currency){
        this.name = name;
        this.type = type;
        this.price = price;
        this.currency = currency;
    }

    public String getName() {
        return this.name;
    }

    public ItemType getType() {
        return this.type;
    }

    public double getPrice() {
        return this.price;
    }

    public String getCurrency() {
        return this.currency;
    }
}
