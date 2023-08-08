package com.techelevator.service;

import org.springframework.data.relational.core.sql.In;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Base62 {
    private static final int BASE = 62;
    private static final String CIPHER = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String encode(String input) {
        // Convert input string into a hex value
        List<String> hexList = input.chars().mapToObj(Integer::toHexString).collect(Collectors.toList());
        String hex = hexList.stream().reduce("", String::concat);

        // Convert hex into a decimal value, we use BigInteger because hex value can exceed Long.MAX_VALUE
        BigInteger decimal = new BigInteger(hex, 16);

        // Encode decimal into the base62 cipher
        StringBuilder output = new StringBuilder();
        BigInteger zero = new BigInteger("0");
        do {
            BigInteger[] results = decimal.divideAndRemainder(new BigInteger(String.valueOf(BASE)));
            decimal = results[0];
            int remainder = results[1].intValue();
            output.insert(0, CIPHER.charAt(remainder));
        } while ( decimal.compareTo(zero) > 0);

        return output.toString();
    }


    public static String decode(String input) {
        int length = input.length();
        // Decode input string to decimal value
        BigInteger base = new BigInteger(String.valueOf(BASE));
        BigInteger decimal = new BigInteger("0");

        for (int i = 0; i < length; i++) {
            int index = CIPHER.indexOf(input.charAt(length - i  - 1));
            decimal = decimal.add( base.pow(i).multiply(new BigInteger(String.valueOf(index))) );
        }

        // Convert decimal to hex value list
        String hex = decimal.toString(16);
        List<String> hexList = new ArrayList<>();
        for (int i = 0; i < hex.length(); i += 2) {
            hexList.add(hex.substring(i, i + 2));
        }

        // Convert each hex value in hex list to char and concatenate to a string
        String output = hexList.stream().reduce("", (result, s) -> {
            char c = (char) Byte.parseByte(s, 16);
            return result.concat(Character.toString(c));
        });

        return output;
    }

    public static void main(String[] args) {
        String text = "1,2";
        System.out.println("text: " + text);
        String encodedText = encode(text);
        System.out.println("encoded: " + encodedText);
        String decodedText = decode(encodedText);
        System.out.printf("decoded: " + decodedText);
    }
}
