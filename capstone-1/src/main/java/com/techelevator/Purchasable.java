package com.techelevator;

public abstract class Purchasable {

    private String slot;
    private String name;
    private double price;
    private String type;
    private int stock;

    public Purchasable (String slot, String name, double price){
        this.slot = slot;
        this.name = name;
        this.price = price;
        stock = 5;
    }

    // getters

    public String getSlot() {
        return slot;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getType() {
        return type;
    }
    public int getStock() {
        return stock;
    }

    // setters

    public void setSlot(String slot) {
        this.slot = slot;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void setType(String type) {
        this.type = type;
    }

    // methods

    public void takeStock(){
        setStock(getStock()-1);
    }

    public abstract void printMessage();
}
