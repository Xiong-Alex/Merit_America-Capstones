package com.techelevator;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GumTest{
    @Test
    public void printsMessage(){
        Gum gum = new Gum("D2", "Little League Chew", .95);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        // Print some output: goes to your special stream
        gum.printMessage();
        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened
//        System.out.println("Here: " + baos.toString());

        Assert.assertEquals("Chew Chew, Yum!", baos.toString().trim());

    }
}