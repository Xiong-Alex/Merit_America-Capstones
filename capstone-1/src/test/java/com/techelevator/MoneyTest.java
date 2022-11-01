package com.techelevator;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class MoneyTest{
    @Test
    public void shouldHave4Quarters2Dimes(){
        Money money = new Money();

        Assert.assertEquals("Quarters: 4 Dimes: 2 Nickels: 0", money.returnChange(1.20));
    }
}