package com.techelevator;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class InventoryTest {
    @Test
    public void shouldEqualA1305(){
        Inventory inventory = new Inventory();
        List<Purchasable> list = inventory.stockingInventory();

        Assert.assertEquals("A1 3.05", list.get(0).getSlot() + " " + list.get(0).getPrice());
    }
}