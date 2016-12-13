package com.bigshop.till;

public class TaxEntry implements Comparable<TaxEntry>{

    //This class exists to add tax information to the receipt

    private String taxCategory;
    private double rate;
    private double purchaseAmount;
    private double taxAmount;

    public TaxEntry(String taxCategory, double rate, double purchaseAmount){
        this.taxCategory = taxCategory;
        this.rate = rate;
        this.purchaseAmount = purchaseAmount;
        this.taxAmount = purchaseAmount * rate / 100;
    }

    public String getTaxCategory(){
        return this.taxCategory;
    }

    public double getRate(){
        return this.rate;
    }

    public double getPurchaseAmount(){
        return this.purchaseAmount;
    }

    public double getTaxAmount(){
        return this.taxAmount;
    }

    public int compareTo(TaxEntry item)
    {
        return this.getTaxCategory().compareTo(item.getTaxCategory());
    }

}
