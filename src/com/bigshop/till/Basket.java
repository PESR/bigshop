package com.bigshop.till;

import com.bigshop.till.exception.ExceededLegalLimit;
import java.util.*;

public class Basket {

    //Using lists to preserve order
    private List<Item> itemList;
    private List<Item> offerList;
    private List<TaxEntry> taxEntryList;

    //For offers & legal checks
    private HashMap<Item, Integer> itemMap;
    private HashMap<ItemType, Integer> itemTypeMap;

    //Offer Lists
    private List<MultiBuyOffer> multiBuyOffersList;
    private List<DiscountOffer> discountOfferList;

    public Basket() {
        this.itemList = new ArrayList<>();
        this.offerList = new ArrayList<>();
        this.taxEntryList = new ArrayList<>();
        this.itemMap = new HashMap<>();
        this.itemTypeMap = new HashMap<>();
    }

    public void addItem(Item item) throws ExceededLegalLimit {
        //check if permissible
        if (this.itemMap.containsKey(item) && item.getType().getMaxEnforced() && this.itemMap.get(item) > item.getType().getMaxAllowed() - 1) {
            throw new ExceededLegalLimit("You can't purchase more than 10 SIM cards");
        } else {
            //Add the item to the itemMap
            if (this.itemMap.containsKey(item)) {
                this.itemMap.put(item, this.itemMap.get(item) + 1);
            } else {
                this.itemMap.put(item, 1);
            }
            //Add the item to the itemTypeMap
            if (this.itemTypeMap.containsKey(item.getType())) {
                this.itemTypeMap.put(item.getType(), this.itemTypeMap.get(item.getType()) + 1);
            } else {
                this.itemTypeMap.put(item.getType(), 1);
            }
            this.itemList.add(item);
        }
    }

    public List<Item> getItemList() {
        return this.itemList;
    }

    public void setMultiBuyOffersList(List<MultiBuyOffer> multiBuyOffersList){
        this.multiBuyOffersList = multiBuyOffersList;
    }

    public void setDiscountOfferList(List<DiscountOffer> discountOfferList){
        this.discountOfferList = discountOfferList;
    }

    public List<Item> getOfferList() {
        return this.offerList;
    }

    public List<TaxEntry> getTaxEntryList() {
        return this.taxEntryList;
    }

    public void applyOffers() {

        //Apply MultiBuy Offers
        for (MultiBuyOffer mbo : multiBuyOffersList){
            if(itemTypeMap.containsKey(mbo.getType()) && itemTypeMap.get(mbo.getType())>=mbo.getMultiplesOf()){
                int count = itemTypeMap.get(mbo.getType());
                while (count >= mbo.getMultiplesOf()){
                    OfferEntry offerEntry = new OfferEntry(mbo.getName(),mbo.getType(), -mbo.getItemToCredit().getPrice(), mbo.getItemToCredit().getCurrency());
                    offerList.add(offerEntry);
                    //reduce the count and try again
                    count -= mbo.getMultiplesOf();
                }
            }
        }

        //Apply Discount Offers
        for (DiscountOffer discountOffer : discountOfferList){
            if(itemTypeMap.containsKey(discountOffer.getItemTypeToDiscount()) && itemTypeMap.containsKey(discountOffer.getWhenBoughtItemType())){
                for (Item item : itemList) {
                    //check the type and if the type is a match create a discount entry
                    if(item.getType() == discountOffer.getItemTypeToDiscount()){
                        double discount = -item.getPrice()*discountOffer.getDiscountRate()/100;
                        OfferEntry offerEntry = new OfferEntry(discountOffer.getName(),item.getType(),discount, item.getCurrency());
                        offerList.add(offerEntry);
                    }
                }
            }
        }
    }

    public void applySalesTax() {

        HashMap<SalesTax, Double> salesTaxMap = new HashMap<>();
        //loop through itemList and add the sale price against the tax category
        for(Item item : this.itemList){
            double amount;
            if(salesTaxMap.containsKey(item.getType().getSalesTax())){
                amount = salesTaxMap.get(item.getType().getSalesTax()) + item.getPrice();
            }else{
                amount = item.getPrice();
            }
            salesTaxMap.put(item.getType().getSalesTax(), amount);
        }

        //loop through offerList and add the sale price against the tax category effectively lowering the amount
        for(Item item : this.offerList){
            double amount;
            if(salesTaxMap.containsKey(item.getType().getSalesTax())){
                amount = salesTaxMap.get(item.getType().getSalesTax()) + item.getPrice();
            }else{
                amount = item.getPrice();
            }
            salesTaxMap.put(item.getType().getSalesTax(), amount);
        }

        //create the taxEntryList
        for (Map.Entry<SalesTax, Double> item : salesTaxMap.entrySet()) {
            TaxEntry taxEntry = new TaxEntry(item.getKey().getTaxCategory(), item.getKey().getRate(), item.getValue());
            taxEntryList.add(taxEntry);
        }

        //sort the list so that it will be A B rather than B A
        Collections.sort(taxEntryList);
    }
}
