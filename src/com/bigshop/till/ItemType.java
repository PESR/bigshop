package com.bigshop.till;

public class ItemType {

    private String name;
    private SalesTax salesTax;
    private int maxAllowed;
    private boolean maxEnforced;

    public ItemType (String name, SalesTax salesTax, int maxAllowed){
        this.name = name;
        this.salesTax = salesTax;
        this.maxAllowed = maxAllowed;
        this.maxEnforced = true;
    }

    public ItemType (String name, SalesTax salesTax){
        this.name = name;
        this.salesTax = salesTax;
        this.maxEnforced = false;
    }

    public String getName(){
        return this.name;
    }

    public SalesTax getSalesTax(){
        return this.salesTax;
    }

    public int getMaxAllowed(){
        return this.maxAllowed;
    }

    public boolean getMaxEnforced(){
        return this.maxEnforced;
    }
}
