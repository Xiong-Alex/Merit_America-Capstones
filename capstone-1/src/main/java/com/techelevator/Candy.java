package com.techelevator;

public class Candy extends Purchasable{
    public Candy(String slot, String name, double price) {
        super(slot, name, price);
        setType("Candy");
    }

    @Override
    public void printMessage(){
        System.out.println("Munch Munch, Yum!");
    }
}
