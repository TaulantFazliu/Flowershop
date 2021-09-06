package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bouquete extends Product {
    private Size size;
    private boolean hasDecoration;
    private List<Flower> flowers;

    public Bouquete(Size size, boolean hasDecoration) throws FlowerShopException {
        this.size = size;
        this.hasDecoration = hasDecoration;
        flowers = new ArrayList<>();
    }

    @Override
    public double getPrice() {
        double price = 0;
        for (Flower flower : flowers) {
            price += flower.getPrice();
        }
        if (completed()) {
            price = price - price * 0.125;
        }
        if (hasDecoration) {
            price = price + price * 0.025;
        }
        return price;
    }

    public boolean completed() {
        return flowers.size() == size.getNumberOfFlowers();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Bouquete bouquete = (Bouquete) o;
        return hasDecoration == bouquete.hasDecoration && size == bouquete.size;
    }

    @Override
    public String toString() {
        return String.format("Bouquete[size=%s, with %s flowers, has decoration=%b",
                this.size,this.flowers.size(),this.hasDecoration);
    }
}
