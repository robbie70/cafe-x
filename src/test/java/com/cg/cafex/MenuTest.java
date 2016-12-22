package com.cg.cafex;

import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MenuTest {

    Menu menu = null;
    private boolean firstTime = Boolean.TRUE;

    @Before
    public void setup(){
        if (firstTime){
            List<Item> menuItems = new ArrayList<>();
            menuItems.add(new Item("Cola", Boolean.FALSE, new BigDecimal(0.50)));
            menuItems.add(new Item("Coffee", Boolean.TRUE, new BigDecimal(1.00)));
            menuItems.add(new Item("Cheese Sandwich", Boolean.FALSE, new BigDecimal(2.00)));
            menuItems.add(new Item("Steak Sandwich", Boolean.TRUE, new BigDecimal(4.50)));
            this.menu = new Menu(menuItems);
            firstTime = Boolean.FALSE;
        }
    }

    @Test
    public void given_an_itemname_return_item_object_Test(){
        String [] itemName = new String [] {"Cola"};
        Optional<Item> item = menu.lookupMenuItem(itemName[0]);

        assertEquals("Item{name='Cola', hot=false, price=0.5}", item.get().toString());
    }

    @Test
    public void given_a_nonexistant_itemname_return_an_empty_item_Test(){
        String [] itemName = new String [] {"DietCola"};
        Optional<Item> item = menu.lookupMenuItem(itemName[0]);

        assertTrue(!item.isPresent());
    }

    @Test
    public void given_a_menu_check_the_menu_items_Test(){
        List<Item> menuItems = new ArrayList<>();
        menuItems.add(new Item("Cola", Boolean.FALSE, new BigDecimal(0.50)));
        Menu menu = new Menu(menuItems);

        assertEquals("Menu{menuItems=[Item{name='Cola', hot=false, price=0.5}]}", menu.toString());
    }

}
