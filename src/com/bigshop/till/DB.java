package com.bigshop.till;

import com.bigshop.till.exception.InvalidItem;
import com.bigshop.till.exception.InvalidItemType;
import com.bigshop.till.exception.InvalidSalesTax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DB {

    //This DB contains dummy data.

    private final HashMap<String, Item> itemMap = new HashMap<>();
    private final HashMap<String, ItemType> itemTypeMap = new HashMap<>();
    private final HashMap<String, SalesTax> salesTaxMap = new HashMap<>();
    private final List<MultiBuyOffer> multiBuyOffersList = new ArrayList<>();
    private final List<DiscountOffer> discountOffersList = new ArrayList<>();


    public DB(){

        //Populate Tax Map
        this.salesTaxMap.put("A", new SalesTax("A", 12.00));
        this.salesTaxMap.put("B", new SalesTax("B", 0.00));

        //Populate ItemType Map
        try {
            this.itemTypeMap.put("SIM", new ItemType("SIM", this.getSalesTax("A"), 10));
            this.itemTypeMap.put("Insurance", new ItemType("Insurance", this.getSalesTax("B")));
            this.itemTypeMap.put("Earphones", new ItemType("Earphones", this.getSalesTax("A")));
            this.itemTypeMap.put("Phone Cases", new ItemType("Phone Cases", this.getSalesTax("A")));
        }catch (InvalidSalesTax e){
            System.out.println("There has been an error while initialising the itemTypeMap");
            System.out.println(e);
        }

        //Populate Data for ItemMap
        try{
            this.itemMap.put("SIM card", new Item("SIM card", this.getItemType("SIM"), 20.00, "CHF"));
            this.itemMap.put("phone case", new Item("Phone Case", this.getItemType("Phone Cases"), 10.00, "CHF"));
            this.itemMap.put("phone insurance", new Item("Phone Insurance (2 years)", this.getItemType("Insurance"), 120.00, "CHF"));
            this.itemMap.put("wired earphones", new Item("Wired Earphones",  this.getItemType("Earphones"), 30.00, "CHF"));
            this.itemMap.put("wireless earphones", new Item("Wireless Earphones",  this.getItemType("Earphones"), 50.00, "CHF"));
        }catch (InvalidItemType e){
            System.out.println("There has been an error while initialising the itemMap");
            System.out.println(e);
        }

        //Initialise MultiBuyOffers List
        try {
            this.multiBuyOffersList.add(new MultiBuyOffer("BOGOF SIM cards", this.getItemType("SIM"), 2, 1, this.getItem("SIM card")));
            this.multiBuyOffersList.add(new MultiBuyOffer("4 for 3 phone cases", this.getItemType("Phone Cases"), 4, 1, this.getItem("phone case")));
        }catch (InvalidItemType | InvalidItem e){
                System.out.println("There has been an error while initialising the multiBuyOfferList");
                System.out.println(e);
        }

        //Initialise DiscountOffers List
        try {
            this.discountOffersList.add(new DiscountOffer("20% off Insurance",this.getItemType("Insurance"), 20.00, this.getItemType("Earphones")));
        }catch (InvalidItemType e){
            System.out.println("There has been an error while initialising the discountOfferList");
            System.out.println(e);
        }
    }

    public Item getItem(String name) throws InvalidItem{

        if(this.itemMap.containsKey(name)){
            return this.itemMap.get(name);
        }else {
            throw new InvalidItem("Item " + name + " not found");
        }
    }

    public Receipt getReceiptTemplate(){
        String logoString =
                        "        **********************************        \n" +
                        "               ***   BIG SHOP   ***               \n" +
                        "        **********************************        \n";
        String contactDetails = "TEL. 000 000 00 00";

        String separator = "----------------------------------------------";

        Receipt receipt = new Receipt(logoString, contactDetails, separator, 50, 2, "CHF");
        return receipt;
    }

    public ItemType getItemType(String name) throws InvalidItemType{
        if(this.itemTypeMap.containsKey(name)){
            return this.itemTypeMap.get(name);
        }else {
            throw new InvalidItemType("ItemType " + name + " not found");
        }
    }

    public SalesTax getSalesTax(String taxCategory) throws InvalidSalesTax{
        if(this.salesTaxMap.containsKey(taxCategory)){
            return this.salesTaxMap.get(taxCategory);
        }else {
            throw new InvalidSalesTax("SalesTax category " + taxCategory + " not found");
        }
    }

    public List<MultiBuyOffer> getMultiBuyOffers(){
        return this.multiBuyOffersList;
    }

    public List<DiscountOffer> getDiscountOfferList(){
        return this.discountOffersList;
    }
}
