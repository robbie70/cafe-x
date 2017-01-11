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

    private Menu menu = null;
    private boolean firstTime = Boolean.TRUE;

    @Before
    public void setup(){
        if (firstTime){
            List<Item> menuItems = new ArrayList<>();
            menuItems.add(new Item("Cola", Item.Temperature.COLD, Item.Type.DRINK, new BigDecimal(0.50)));
            menuItems.add(new Item("Coffee", Item.Temperature.HOT, Item.Type.DRINK, new BigDecimal(1.00)));
            menuItems.add(new Item("Cheese Sandwich", Item.Temperature.COLD, Item.Type.FOOD, new BigDecimal(2.00)));
            menuItems.add(new Item("Steak Sandwich", Item.Temperature.HOT, Item.Type.FOOD, new BigDecimal(4.50)));
            this.menu = new Menu(menuItems);
            firstTime = Boolean.FALSE;
        }
    }

    @Test
    public void given_an_itemname_return_item_object(){
        String [] itemName = new String [] {"Cola"};
        Optional<Item> item = menu.lookupMenuItem(itemName[0]);

        assertEquals("Item{name='Cola', Temperature=COLD, Type=DRINK, price=0.5}", item.orElse(null).toString());
    }

    @Test
    public void given_a_nonexistant_itemname_return_an_empty_item(){
        String [] itemName = new String [] {"DietCola"};
        Optional<Item> item = menu.lookupMenuItem(itemName[0]);

        assertTrue(!item.isPresent());
    }

    @Test
    public void given_a_menu_check_the_menu_items(){
        List<Item> menuItems = new ArrayList<>();
        menuItems.add(new Item("Cola", Item.Temperature.HOT, Item.Type.DRINK, new BigDecimal(0.50)));
        Menu menu = new Menu(menuItems);

        assertEquals("Menu{menuItems=[Item{name='Cola', Temperature=HOT, Type=DRINK, price=0.5}]}", menu.toString());
    }

}
