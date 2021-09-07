package com;

public class Flower extends Product {
    private String type;
    private String color;
    private double price;
    private Seasons seasons;

    public Flower(String type, String color, double price) throws FlowerShopException {
        if (type == null || type.isEmpty()) {
            throw new FlowerShopException("Type must not be empty!");
        } else if (color == null || color.isEmpty()) {
            throw new FlowerShopException("Color munst not be empty!");

        } else if (price == 0 || price < 0) {
            throw new FlowerShopException("Price must be a positive value!");
        } else {
            this.type = type;
            this.color = color;
            this.price = price;
        }
    }

    public Seasons getSeasons() {
        return seasons;
    }

    public void setSeasons(Seasons seasons) {
        this.seasons = seasons;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) throws FlowerShopException {
        if (price == 0 || price < 0) {
            throw new FlowerShopException("Price must be a positive value!");
        } else {
            this.price = price;
        }
    }

    @Override
    public String toString() {
        return String.format("%s %nFlower: Type:%s, color:%s, price:%.1f"
                , super.toString(), this.type, this.color, this.price);
    }
}
