package com.techelevator;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DrinkTest{
    @Test
    public void printsMessage(){
        Drink drink = new Drink("C1", "Cola", 5);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        // Print some output: goes to your special stream
        drink.printMessage();
        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened
//        System.out.println("Here: " + baos.toString());

        Assert.assertEquals("Glug Glug, Yum!", baos.toString().trim());

    }
}