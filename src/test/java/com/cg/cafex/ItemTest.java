package com.cg.cafex;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ItemTest {

    @Test
    public void given_item_attributes_create_a_new_item_Test(){
        Item item = new Item("Cola", Boolean.FALSE, new BigDecimal(50));
        assertEquals("Cola", item.getName());
        assertEquals(Boolean.FALSE, item.isHot());
        assertEquals(new BigDecimal(50), item.getPrice());
    }

    @Test
    public void given_item_attributes_check_toString_Test(){
        Item item = new Item("Cola", Boolean.FALSE, new BigDecimal(50));
        assertEquals("Item{name='Cola', hot=false, price=50}", item.toString());
    }


}
