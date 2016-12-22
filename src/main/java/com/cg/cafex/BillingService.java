package com.cg.cafex;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BillingService {

    private static BillingService instance;
    private static Menu menu = null;

    private BillingService(Menu menu){
        BillingService.menu = menu;
    }

    public static synchronized BillingService getInstance(Menu menu){
        if(instance == null){
            instance = new BillingService(menu);
        }
        return instance;
    }

    public BigDecimal calcTotal(String [] ofItems){
        final List<Item> itemsToSum = buildItemsList(Arrays.asList(ofItems));
        return sumItems(itemsToSum);
    }

    private List<Item> buildItemsList(List<String> listOfItemStrings) {
        List<Item> listOfItems = new ArrayList<>();
        for(String itemString : listOfItemStrings){
            Optional<Item> item = menu.lookupMenuItem(itemString);
            if(item.isPresent()){
                listOfItems.add(item.get());
            }else{
                throw new IllegalArgumentException("Invalid menu item found <" + itemString + ">");
            }
        }
        return listOfItems;
    }

    private BigDecimal sumItems(List<Item> listOfItems){
        BigDecimal total = new BigDecimal(0);
        for (Item items : listOfItems){
            total = total.add(items.getPrice());
        }
        return total;
    }

}
