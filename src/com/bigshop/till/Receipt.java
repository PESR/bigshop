package com.bigshop.till;

import java.util.List;

public class Receipt {

    //This class is responsible for the creation of the receipt. Currently it logs the receipt to the console.
    //This can be changed to return something which can be printed.

    private String separator;
    private String contactDetails;
    private String logo;
    private int receiptWidth;
    private String currency;
    private int padding;

    private List<Item> itemList;
    private List<Item> offerList;
    private List<TaxEntry> taxEntryList;

    public Receipt (String logo, String contactDetails, String separator, int receiptWidth, int padding, String currency){
        this.logo = logo;
        this.separator = separator;
        this.contactDetails = contactDetails;
        this.receiptWidth = receiptWidth;
        this.currency = currency;
        this.padding = padding;
    }

    public void generateFromBasket(Basket basket){
        //This takes information from the basket to then enable formatting the receipt
        this.itemList = basket.getItemList();
        this.offerList = basket.getOfferList();
        this.taxEntryList = basket.getTaxEntryList();
    }
    
    public void print(){
        //Present the Data
        //In a future release there could be a format() method formats the receipt and returns something that's in a printable format.

        //Style for the output
        String formatItems = "  %1$-32s%2$10.2f   %3$-1s\n";
        String formatOffers = "  %1$-32s%2$10.2f   %3$-1s\n";
        String formatTotals = "  %1$-32s%2$10.2f\n";
        String formatTax = "  %1$-1s       %2$5.2f%%    %3$10.2f    %4$10.2f\n";

        double subTotal = 0.00;

        //******************
        //     HEADER
        //******************
        //Insert a blank line
        System.out.println();
        //Print LOGO
        System.out.println(this.logo);
        //Print Contact Details
        System.out.println(centerLine(this.contactDetails));
        //Separator
        System.out.println(centerLine(this.separator));

        //******************
        //     ITEMS
        //******************
        //Items
        System.out.println(centerLine("Items"));
        //Separator
        System.out.println(centerLine(this.separator));
        //Currency
        System.out.println(currencyAlign(this.currency));
        //PrintAll Items;
        for (Item item : itemList) {
            System.out.format(formatItems, item.getName(), item.getPrice(), item.getType().getSalesTax().getTaxCategory());
            subTotal += item.getPrice();
        }
        //Sub-Total line
        System.out.println("                                    ------------");
        //Sub-Total
        System.out.format(formatTotals,"SUB-TOTAL", subTotal);
        //Separator
        System.out.println(centerLine(this.separator));

        //******************
        //     OFFERS
        //******************
        if(offerList.size()>0) {
            //ONLY SHOW IF THERE ARE OFFERS!!!
            //Offers
            System.out.println(centerLine("Offers"));
            //Separator
            System.out.println(centerLine(this.separator));
            //Loop through all offers and print them
            for (Item item : offerList) {
                System.out.format(formatOffers, item.getName(), item.getPrice(), item.getType().getSalesTax().getTaxCategory());
                subTotal += item.getPrice();
            }
            //Sub-total line
            System.out.println("                                    ------------");
            //Sub-Total
            System.out.format(formatTotals, "SUB-TOTAL", subTotal);
            //Separator
            System.out.println(centerLine(this.separator));
        }

        //******************
        //     TAX
        //******************
        System.out.println(centerLine("Tax"));
        //Separator
        System.out.println(centerLine(this.separator));
        //Heading
        System.out.println("  CAT       RATE     excl. TAX           TAX");
        //Loop through all tax entries and print them
        for (TaxEntry entry : taxEntryList) {
            System.out.format(formatTax, entry.getTaxCategory(), entry.getRate(), entry.getPurchaseAmount(), entry.getTaxAmount());
            subTotal += entry.getTaxAmount();
        }
        //Sub-Total line
        System.out.println("                                    ------------");
        //Sub-Total
        System.out.format(formatTotals,"SUB-TOTAL", subTotal);
        //Separator
        System.out.println(centerLine(this.separator));

        //******************
        //     TOTAL
        //******************
        System.out.format(formatTotals,"TOTAL", subTotal);
        //Separator
        System.out.println(centerLine("=============================================="));
    }


    //The following two methods enable the styling of the receipt
    private String centerLine(String line){
        int space = (this.receiptWidth-line.length())/2;
        int spaceAndString = space + line.length();
        return String.format("%1$" + spaceAndString + "s", line);
    }

    private String currencyAlign(String line){
        int space = this.receiptWidth - line.length() - this.padding - 4;
        int spaceAndString = space + line.length();
        return String.format("%1$" + spaceAndString + "s", line);
    }

}
