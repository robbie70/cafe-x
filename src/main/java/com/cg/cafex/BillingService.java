package com.cg.cafex;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BillingService {

    private static BillingService instance;
    private static Menu menu = null;
    private BillingService(){}

    public static synchronized BillingService getInstance(Menu menu){
        if(instance == null){
            instance = new BillingService();
            BillingService.menu = menu;
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
        ServiceChargeCalculator serviceChargeCalculator = new ServiceChargeCalculator();
        BigDecimal total = new BigDecimal(0);
        for (Item items : listOfItems){
            total = total.add(items.getPrice());
        }
        BigDecimal totalIncService = total.add(serviceChargeCalculator.calculate(total, listOfItems.toArray(new Item[listOfItems.size()])));

        return totalIncService.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
