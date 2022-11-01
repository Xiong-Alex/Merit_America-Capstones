package com.techelevator;

public class Drink extends Purchasable{
    public Drink(String slot, String name, double price) {
        super(slot, name, price);
        setType("Drink");
    }

    @Override
    public void printMessage(){
        System.out.println("Glug Glug, Yum!");
    }
}
