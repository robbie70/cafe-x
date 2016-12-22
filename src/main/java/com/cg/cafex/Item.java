package com.cg.cafex;

import java.math.BigDecimal;

public class Item {

    private String name;
    private boolean hot;
    private BigDecimal price;

    public Item(String name, boolean hot, BigDecimal price) {
        this.name = name;
        this.hot = hot;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public boolean isHot() {
        return hot;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", hot=" + hot +
                ", price=" + price.toString() +
                '}';
    }
}
