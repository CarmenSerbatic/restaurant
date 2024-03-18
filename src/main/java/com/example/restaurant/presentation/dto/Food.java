package com.example.restaurant.presentation.dto;

public class Food {

    private Dish dish;
    private int amount;

    public Food() {
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
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
