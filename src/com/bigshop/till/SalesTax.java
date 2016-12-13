package com.bigshop.till;

public class SalesTax {

    private String taxCategory;
    private double rate;

    public SalesTax (String taxCategory, double rate){
        this.taxCategory = taxCategory;
        this.rate = rate;
    }

    public String getTaxCategory(){
        return this.taxCategory;
    }

    public double getRate(){
        return this.rate;
    }
}
