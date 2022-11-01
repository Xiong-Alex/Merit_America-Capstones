package com.techelevator.util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class PurchaseLog {
    /*
     * Makes a log file in target folder that can be used to log different transactions that the vending machine
     * uses.
     */
    public static void log(String message){
        File dataFile = new File("target/Log.txt");
        try{
            PrintWriter logFile = new PrintWriter(new FileWriter(dataFile, true));
            logFile.write(message);
            logFile.println();
            logFile.close();
        }
        catch(Exception e){
            System.err.println("File not found");
        }
    }
}
