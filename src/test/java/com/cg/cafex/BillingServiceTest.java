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
            menuItems.add(new Item("Cola", Boolean.FALSE, new BigDecimal(0.50)));
            menuItems.add(new Item("Coffee", Boolean.TRUE, new BigDecimal(1.00)));
            menuItems.add(new Item("Cheese Sandwich", Boolean.FALSE, new BigDecimal(2.00)));
            menuItems.add(new Item("Steak Sandwich", Boolean.TRUE, new BigDecimal(4.50)));
            this.menu = new Menu(menuItems);
            billingService = BillingService.getInstance(menu);
            firstTime = Boolean.FALSE;
        }
    }

    @Test
    public void given_a_list_of_items_calculate_total_should_equals_Test(){
        billingService = BillingService.getInstance(menu);
        String [] listOfItems = new String [] {"Cola","Coffee","Cheese Sandwich"};

        assertEquals(new BigDecimal(3.5), billingService.calcTotal(listOfItems));
    }

    @Test
    public void given_an_invalid_item_throw_exception_Test() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Invalid menu item found <Cheese and Onion Toasted Sandwich>");

        BillingService bill = BillingService.getInstance(this.menu);
        String [] listOfItems = new String [] {"Cola","Coffee","Cheese and Onion Toasted Sandwich"};
        bill.calcTotal(listOfItems);

        fail();
    }
}
