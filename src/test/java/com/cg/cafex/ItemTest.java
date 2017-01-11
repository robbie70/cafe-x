package com.cg.cafex;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ItemTest {

    @Test
    public void given_item_attributes_create_a_new_item(){
        Item item = new Item("Cola", Item.Temperature.COLD, Item.Type.DRINK, new BigDecimal(0.50));
        assertEquals("Cola", item.getName());
        assertEquals(Item.Temperature.COLD, item.getTemperature());
        assertEquals(new BigDecimal(0.50), item.getPrice());
    }

    @Test
    public void given_item_attributes_check_toString(){
        Item item = new Item("Cola", Item.Temperature.COLD, Item.Type.DRINK, new BigDecimal("0.50"));
        assertEquals("Item{name='Cola', Temperature=COLD, Type=DRINK, price=0.50}", item.toString());
    }


}
