package com.cg.cafex;

import org.junit.Test;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
public class ServiceChargeCalculatorTest {

    private ServiceChargeCalculator underTest = new ServiceChargeCalculator();

    @Test
    public void should_calculate_service_charge_of_zero_for_drink_only_items() {
        Item cola = new Item("Cola", Item.Temperature.COLD, Item.Type.DRINK, new BigDecimal(0.50));
        Item coffee = new Item("Coffee", Item.Temperature.HOT, Item.Type.DRINK, new BigDecimal(1.00));
        assertEquals(BigDecimal.ZERO, underTest.calculate(new BigDecimal("1.50"), cola, coffee));
    }

    @Test
    public void should_calculate_service_charge_of_ten_percent_of_bill_with_cold_food_items() {
        Item cola = new Item("Cola", Item.Temperature.COLD, Item.Type.DRINK, new BigDecimal(0.50));
        Item cheeseSandwich = new Item("Cheese Sandwich", Item.Temperature.COLD, Item.Type.FOOD, new BigDecimal(2.00));
        assertEquals(new BigDecimal("0.25"), underTest.calculate(new BigDecimal("2.50"), cola, cheeseSandwich));
    }

    @Test
    public void should_calculate_service_charge_of_twenty_percent_of_bill_with_hot_food_items() {
        Item cola = new Item("Cola", Item.Temperature.COLD, Item.Type.DRINK, new BigDecimal(0.50));
        Item steakSandwich = new Item("Steak Sandwich", Item.Temperature.HOT, Item.Type.FOOD, new BigDecimal(4.50));
        assertEquals(new BigDecimal("1.00"), underTest.calculate(new BigDecimal("5.00"), cola, steakSandwich));
    }

    @Test
    public void should_calculate_maximum_service_charge_of_twenty_pounds_for_bill_exceeding_one_hundred_pounds() {
        Item cola = new Item("Cola", Item.Temperature.COLD, Item.Type.DRINK, new BigDecimal(0.50));
        Item steakSandwich = new Item("Steak Sandwich", Item.Temperature.HOT, Item.Type.FOOD, new BigDecimal(4.50));
        assertEquals(new BigDecimal("20.00"), underTest.calculate(new BigDecimal("110.00"), cola, steakSandwich, steakSandwich));
    }

    @Test
    public void should_calculate_maximum_service_charge_of_twenty_pounds_for_bill_equal_to_one_hundred_pounds() {
        Item cola = new Item("Cola", Item.Temperature.COLD, Item.Type.DRINK, new BigDecimal(0.50));
        Item steakSandwich = new Item("Steak Sandwich", Item.Temperature.HOT, Item.Type.FOOD, new BigDecimal(4.50));
        assertEquals(new BigDecimal("20.00"), underTest.calculate(new BigDecimal("100.00"), cola, steakSandwich, steakSandwich));
    }

    @Test
    public void should_round_service_charge_up() {
        Item cola = new Item("Cola", Item.Temperature.COLD, Item.Type.DRINK, new BigDecimal(0.50));
        Item cheeseSandwich = new Item("Cheese Sandwich", Item.Temperature.COLD, Item.Type.FOOD, new BigDecimal(2.00));
        assertEquals(new BigDecimal("0.56"), underTest.calculate(new BigDecimal("5.56"), cola, cheeseSandwich));
    }

    @Test
    public void should_round_service_charge_half_up() {
        Item cola = new Item("Cola", Item.Temperature.COLD, Item.Type.DRINK, new BigDecimal(0.50));
        Item cheeseSandwich = new Item("Cheese Sandwich", Item.Temperature.COLD, Item.Type.FOOD, new BigDecimal(2.00));
        assertEquals(new BigDecimal("0.56"), underTest.calculate(new BigDecimal("5.55"), cola, cheeseSandwich));
    }

    @Test
    public void should_round_service_charge_down() {
        Item cola = new Item("Cola", Item.Temperature.COLD, Item.Type.DRINK, new BigDecimal(0.50));
        Item cheeseSandwich = new Item("Cheese Sandwich", Item.Temperature.COLD, Item.Type.FOOD, new BigDecimal(2.00));
        assertEquals(new BigDecimal("0.55"), underTest.calculate(new BigDecimal("5.54"), cola, cheeseSandwich));
    }

}
