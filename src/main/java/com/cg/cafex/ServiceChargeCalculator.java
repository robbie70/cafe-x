package com.cg.cafex;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Stream;

public class ServiceChargeCalculator {

    private static final int SERVICE_CHARGE_RATE_WHEN_BILL_INCLUDES_FOOD = 10;
    private static final int SERVICE_CHARGE_RATE_WHEN_BILL_INCLUDES_HOT_FOOD = 20;
    private static final String MAX_SERVICE_CHARGE_AMOUNT = "20.00";

    public BigDecimal calculate(final BigDecimal salePrice, final Item... items) {
        if (hasHotFood(items)) {
            return minimumOf(applyDiscount(salePrice, percent(SERVICE_CHARGE_RATE_WHEN_BILL_INCLUDES_HOT_FOOD)), new BigDecimal(MAX_SERVICE_CHARGE_AMOUNT));
        }
        if (hasFood(items)) {
            return applyDiscount(salePrice, percent(SERVICE_CHARGE_RATE_WHEN_BILL_INCLUDES_FOOD));
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal applyDiscount(final BigDecimal salePrice, final BigDecimal percent) {
        return salePrice.multiply(percent).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal percent(final int i) {
        return new BigDecimal(i).divide(new BigDecimal("100"));
    }

    private boolean hasHotFood(final Item... items) {
        return asStream(items)
                .filter(t -> t.getType() == Item.Type.FOOD)
                .filter(t -> t.getTemperature() == Item.Temperature.HOT)
                .count() > 0;
    }

    private boolean hasFood(final Item... items) {
        return asStream(items)
                .filter(t -> t.getType() == Item.Type.FOOD)
                .count() > 0;
    }

    private BigDecimal minimumOf(final BigDecimal... candidates) {
        return asStream(candidates)
                .min(BigDecimal::compareTo)
                .orElseThrow(IllegalArgumentException::new);
    }

    private <T> Stream<T> asStream(final T... objects) {
        return Arrays.asList(objects).stream();
    }

}