package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Inventory {

    List <Purchasable> stockingInventory = new ArrayList<>();  //Empty array list to be filled by stockingInventory().


    /*
     * Reads the vending machine file and separates the information in each line of file to make a new item and
     * adds that item to the stockingInventory array.
     */

    public List<Purchasable> stockingInventory () {

        File inputFile = new File("vendingmachine.csv");

        try (
                Scanner input = new Scanner(inputFile)) {

            while (input.hasNextLine()) {

                String lineOfInput = input.nextLine();
                String[] itemInformation = lineOfInput.split("\\|");

                if (itemInformation[3].equals("Chip")) {
                    Chip x = new Chip(itemInformation[0], itemInformation[1], Double.parseDouble(itemInformation[2]));
                    stockingInventory.add(x);
                } else if (itemInformation[3].equals("Candy")) {
                    stockingInventory.add(new Candy(itemInformation[0], itemInformation[1],
                            Double.parseDouble(itemInformation[2])));
                } else if (itemInformation[3].equals("Drink")) {
                    stockingInventory.add(new Drink(itemInformation[0], itemInformation[1],
                            Double.parseDouble(itemInformation[2])));
                } else if (itemInformation[3].equals("Gum")) {
                    stockingInventory.add(new Gum(itemInformation[0], itemInformation[1],
                            Double.parseDouble(itemInformation[2])));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return stockingInventory;
    }


}
