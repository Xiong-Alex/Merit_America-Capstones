package com.techelevator;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ChipTest{
    @Test
    public void printsMessage(){
        Chip chip = new Chip("A1", "Potato Crisps", 3.10);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        // Print some output: goes to your special stream
        chip.printMessage();
        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened
//        System.out.println("Here: " + baos.toString());

        Assert.assertEquals("Crunch Crunch, Yum!", baos.toString().trim());

    }
}