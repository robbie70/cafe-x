package com.cg.cafex;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BillingServiceTest {

    private Menu menu = null;
    private boolean firstTime = Boolean.TRUE;
    private BillingService billingService;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setup(){
        if (firstTime){
            List<Item> menuItems = new ArrayList<>();
            menuItems.add(new Item("Cola", Item.Temperature.COLD, Item.Type.DRINK, new BigDecimal(0.50)));
            menuItems.add(new Item("Coffee", Item.Temperature.HOT, Item.Type.DRINK, new BigDecimal(1.00)));
            menuItems.add(new Item("Cheese Sandwich", Item.Temperature.COLD, Item.Type.FOOD, new BigDecimal(2.00)));
            menuItems.add(new Item("Steak Sandwich", Item.Temperature.HOT, Item.Type.FOOD, new BigDecimal(4.50)));
            this.menu = new Menu(menuItems);
            billingService = BillingService.getInstance(menu);
            firstTime = Boolean.FALSE;
        }
    }

    @Test
    public void given_a_list_of_items_containing_drinks_only_no_service_charge_should_be_applied(){
        billingService = BillingService.getInstance(menu);
        String [] stringOfItems = new String [] {"Cola","Coffee"};
        assertEquals(new BigDecimal("1.50"), billingService.calcTotal(stringOfItems));
    }

    @Test
    public void given_a_list_of_items_service_can_be_run_twice(){
        billingService = BillingService.getInstance(menu);
        String [] stringOfItems = new String [] {"Cola","Coffee","Cheese Sandwich"};

        assertEquals(new BigDecimal("3.85"), billingService.calcTotal(stringOfItems));

        String [] stringOfItems2 = new String [] {"Cola","Coffee", "Steak Sandwich"};
        assertEquals(new BigDecimal("7.20"), billingService.calcTotal(stringOfItems2));

    }

    @Test
    public void given_a_list_of_items_with_total_greater_than_hundred_pounds_service_is_no_more_than_twenty(){
        billingService = BillingService.getInstance(menu);

        String [] stringOfItems = getSteakSandwiches(23);
        assertEquals(new BigDecimal("123.50"), billingService.calcTotal(stringOfItems));
    }


    @Test
    public void given_an_invalid_item_throw_exception() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Invalid menu item found <Cheese and Onion Toasted Sandwich>");

        BillingService bill = BillingService.getInstance(this.menu);
        String [] stringOfItems = new String [] {"Cola","Coffee","Cheese and Onion Toasted Sandwich"};
        bill.calcTotal(stringOfItems);

        fail();
    }

    private String [] getSteakSandwiches (int noOfSteakSandwiches){
        List<String> stringOfItems = new ArrayList<>();
        for (int i = 0; i < noOfSteakSandwiches; i++){
            stringOfItems.add("Steak Sandwich");
        }
        return stringOfItems.toArray(new String[stringOfItems.size()]);
    }
}
