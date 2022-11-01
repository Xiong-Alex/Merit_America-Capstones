package com.techelevator;

public class Gum extends Purchasable{

    public Gum(String slot, String name, double price) {
        super(slot, name, price);
        setType("Gum");
    }

    @Override
    public void printMessage(){
        System.out.println("Chew Chew, Yum!");
    }
}
