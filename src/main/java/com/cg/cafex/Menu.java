package com.cg.cafex;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Menu {

    private List<Item> menuItems = new ArrayList<>();

    public Menu(List<Item> items){

        this.menuItems = items;
    }

    public Optional<Item> lookupMenuItem(String itemName) {
        Optional<Item> itemFound = Optional.empty();
        for(Item item : menuItems){
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemFound = Optional.of(item);
                break;
            }
        }
        return itemFound;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuItems=" + menuItems +
                '}';
    }
}
