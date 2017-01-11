package com.cg.cafex;

import java.math.BigDecimal;

public class Item {

    private String name;
    private Temperature temperature;
    private Type type;
    private BigDecimal price;

    public Item(String name, Temperature temperature, Type type, BigDecimal price) {
        this.name = name;
        this.temperature = temperature;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Type getType() { return type; }

    public enum Temperature {
        HOT,
        COLD
    }

    public enum Type {
        FOOD,
        DRINK
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", Temperature=" + temperature +
                ", Type=" + type +
                ", price=" + price.toString() +
                '}';
    }
}
