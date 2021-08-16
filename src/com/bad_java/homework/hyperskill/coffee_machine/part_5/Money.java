package com.bad_java.homework.hyperskill.coffee_machine.part_5;

public class Money implements Ingredient{

    private int amount;
    private final String unit = "$";

    Money(int amount) {
        this.amount = amount;
    }
    public int getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }
}
