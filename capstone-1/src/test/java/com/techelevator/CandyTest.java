package com.techelevator;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CandyTest {
    @Test
    public void printsMessage(){
        Candy candy = new Candy("B3", "Wonka Bar", 1.50);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        // Print some output: goes to your special stream
        candy.printMessage();
        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened
//        System.out.println("Here: " + baos.toString());

        Assert.assertEquals("Munch Munch, Yum!", baos.toString().trim());

    }

}