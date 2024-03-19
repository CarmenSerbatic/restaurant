package com.example.restaurant.presentation.dto;

public class Food {

    private String dish;
    private int amount;

    public Food() {
    }

    public Food(String dish, int amount) {
        this.dish = dish;
        this.amount = amount;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Food{" +
                "dish=" + dish +
                ", amount=" + amount +
                '}';
    }
}
