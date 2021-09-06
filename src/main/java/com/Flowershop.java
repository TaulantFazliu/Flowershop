package com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Flowershop {
    private Map<Product, Integer> products;
    private String name;


    public Flowershop(String name) throws FlowerShopException {
        if (name == null || name.equals("")) {
            throw new FlowerShopException("The shop's name cannot be empty or null");
        }
        this.name = name;
        products = new HashMap<>();
        loadProducts();
    }

    public void loadProducts() {
        try {
            List<String> items = Files.readAllLines(Path.of("Flowers.txt"));
            for (String flower : items) {
                String[] fields = flower.split(",");
                String kind = fields[0];
                String type = fields[1];
                String color = fields[2];
                double price = Double.parseDouble(fields[3]);
                Seasons seasons = Seasons.valueOf(fields[4]);
                Flower flower1 = null;
                switch (kind) {
                    case "Violet":
                        boolean flourishesWeekly = Boolean.parseBoolean(fields[5]);
                        flower1 = new Violet(type, color, price, seasons, flourishesWeekly);
                        break;
                    case "Rose":
                        int nmPetals = Integer.parseInt(fields[5]);
                        flower1 = new Rose(type, color, price, seasons, nmPetals);
                        break;
                    case "Marigold":
                        Groups groups = Groups.valueOf(fields[5]);
                        flower1 = new Marigold(type, color, price, seasons, groups);
                        break;
                }
                if (flower1 != null) {
                    if (products.containsKey(flower1)) {
                        products.put(flower1, products.get(flower1) + 1);
                    } else {
                        products.put(flower1, 1);
                    }
                }
            }
        } catch (IOException | FlowerShopException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product, int qty) throws FlowerShopException {
        if (product == null) {
            throw new FlowerShopException("Product cannot be null!");
        }
        if (qty < 0) {
            throw new FlowerShopException("Qty should be positive!");
        }
        if (products.containsKey(product))
            products.put(product, products.get(product) + qty);
        else products.put(product, qty);
    }

    public void createBouquete(Size size, boolean hasDecoration) throws FlowerShopException {
        Bouquete bouquete = new Bouquete(size, hasDecoration);
        if (products.containsKey(bouquete))
            products.put(bouquete, products.get(bouquete) + 1);
        else
            products.put(bouquete, 1);
    }

    public boolean buyProduct(Product product, int qty) {
        if (products.containsKey(product) && products.get(product) >= qty) {
            products.put(product, products.get(product) - qty);
            return true;
        }
        return false;
    }





}
