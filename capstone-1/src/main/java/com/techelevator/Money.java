package com.techelevator;

import java.text.NumberFormat;
import java.util.Locale;

public class Money {

    private final double ONE = 1.00;
    private final double FIVE = 5.00;
    private final double TEN = 10.00;
    private final double TWO = 2.00;


    private double money = 0; //The main variable Money class uses to keep track & change the money in Vending machine.

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getONE() {
        return ONE;
    }

    public double getFIVE() {
        return FIVE;
    }

    public double getTEN() {
        return TEN;
    }

    public double getTWO() {
        return TWO;
    }

    public void addTen(){
        this.money = getMoney() + TEN;
    }

    public void addFive(){
        this.money = getMoney() + FIVE;
    }

    public void addOne(){
        this.money = getMoney() + ONE;
    }

    public void addTwo(){
        this.money = getMoney() + TWO;
    }

    /*
     * Will return a string that converts any money amount to the amount of quarters, dimes, and nickels returned.
     */

    public String returnChange(double money){

        final int NICKEL = 5;
        final int DIME = 10;
        final int QUARTER = 25;

        int quarterCount = 0;
        int dimesCount = 0;
        int nickelsCount = 0;
        int moneyConverted = (int)(money * 100);

       while(moneyConverted >= QUARTER){
           quarterCount++;
           moneyConverted -= QUARTER;
       }
        while(moneyConverted >= DIME){
            dimesCount++;
            moneyConverted -= DIME;
        }
        while(moneyConverted >= NICKEL){
            nickelsCount++;
            moneyConverted -= NICKEL;
        }



        return "Quarters: " + quarterCount + " Dimes: " + dimesCount + " Nickels: " + nickelsCount;

    }

    public void displayBalance(){
        System.out.println("");
        System.out.println("Current Money Provided: " + formatMoney(getMoney()));
    }

    public String formatMoney(double money){
        return NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                .format(money);
    }

}
