package com.techelevator;

public class Chip extends Purchasable{
    public Chip(String slot, String name, double price) {
        super(slot, name, price);
        setType("Chip");
    }

    @Override
    public void printMessage(){
        System.out.println("Crunch Crunch, Yum!");
    }
}
