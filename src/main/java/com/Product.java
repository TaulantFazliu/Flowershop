package com;

public abstract class Product {
    public abstract double getPrice();

    @Override
    public String toString() {
        return "Product: ";
    }

}
