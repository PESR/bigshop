package com.bigshop.till;

import com.bigshop.till.exception.ExceededLegalLimit;
import com.bigshop.till.exception.InvalidItem;

import java.util.Scanner;

public class Main {

    //This program listens for items entered in the console as a list.
    //An example string containing all scenarios is:
    //"SIM card","S","OtherItem","SIM card","phone insurance", "phone insurance", "wireless earphones", "wireless earphones", "wireless earphones","SIM card","SIM card","SIM card","SIM card","SIM card","SIM card","SIM card","SIM card","SIM card","SIM card","phone case","phone case","phone case","phone case"

    public static void main(String[] args) {

        String usage = "Usage: \n" +
                "  Enter list of items in the following format: \"SIM card\",\"phone insurance\" etc \n" +
                "Valid items: \n" +
                "  SIM card\n" +
                "  phone case\n" +
                "  phone insurance\n" +
                "  wired earphones\n" +
                "  wireless earphones\n"+
                "To exit: \n" +
                "  write terminate and hit return\n";

        String[] input = {};
        String prompt = "Please enter the items from the basket in the following format: \"item\",\"item\"";

        Scanner scanner = new Scanner(System.in);

        //Inform the user what to do
        System.out.println(prompt);

        //Listen for input
        while (scanner.hasNextLine()){
            String inputLine = scanner.nextLine();
            //Prompt help if no data is supplied
            if(inputLine.isEmpty()) {
                System.out.println("Invalid usage!");
                System.out.println("--------------");
                System.out.print(usage);
                continue;
            }else if(inputLine.equals("terminate")){
                System.exit(0);
            }else {
                //Not ideal as this could cause issues if an item name contains a ,
                //remove any scenario where there is a space before the next item in the list
                String sanitisedInputLine = inputLine.replace(" \"","");
                sanitisedInputLine = sanitisedInputLine.replace("\"","");
                input = sanitisedInputLine.toString().split(",");
            }

            DB db = new DB();
            Basket basket = new Basket();

            for (String potentialItem: input) {
                //Process list and find Item
                Item item;
                try {
                    item = db.getItem(potentialItem);
                } catch (InvalidItem e) {
                    System.out.println(e);
                    continue;
                }
                //Add item to basket
                try{
                    basket.addItem(item);
                }catch (ExceededLegalLimit e){
                    System.out.println(e);
                }

            }

            //check if there are items in the basket
            if(basket.getItemList().isEmpty()){
                System.out.println("There are no items to print a receipt for.");
                continue;
            }

            //Set & Apply Offers
            basket.setMultiBuyOffersList(db.getMultiBuyOffers());
            basket.setDiscountOfferList(db.getDiscountOfferList());
            basket.applyOffers();

            //Apply Tax
            basket.applySalesTax();

            //Generate Receipt
            Receipt receipt = db.getReceiptTemplate();
            receipt.generateFromBasket(basket);

            //Print Receipt
            receipt.print();

            //Ask for more input
            System.out.println(prompt);
        }
    }
}
