package com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow;

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
            List<String> items = Files.readAllLines(Path.of("D:\\skyver\\git\\Flowershop\\src\\main\\java\\com\\Flower.txt"));
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


    public void buildBouquete(Size size, Flower product, int qty) throws FlowerShopException {
        if (qty > size.getNumberOfFlowers()) {
            throw new FlowerShopException("The required quantity is not correct");
        }
        int flowersLeft = products.get(product) == null ? 0 : products.get(product);
        if (flowersLeft < qty) {
            throw new FlowerShopException("There are not enough flowers to complete the bouquete!");
        }

        Bouquete bouquete = null;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey() instanceof Bouquete) {
                Bouquete b = (Bouquete) entry.getKey();
                if (b.getSize()==size&&!b.completed()) {
                    bouquete = b;
                    break;
                }
            }
        }

        if (bouquete == null) {
            throw new FlowerShopException("The bouquete is null! ");
        }

        int toBeCompleted = Math.min(flowersLeft, bouquete.flowersNeeded());
        for (Product p : products.keySet()) {
            if (p.equals(product) || toBeCompleted != 0) {
                Flower flower = (Flower) p;
                bouquete.addFlower(flower);
                products.put(p, products.get(p) - 1);
                toBeCompleted--;
            }
        }

    }

    public boolean buyProduct(Product product, int qty) {
        if (products.containsKey(product) && products.get(product) >= qty) {
            products.put(product, products.get(product) - qty);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Flowershop: %s, with products: %s",this.name,this.products);
    }

    public static void main(String[] args) {
        try {
            Flowershop flowershop = new Flowershop("Tali's shop");
//            flowershop.createBouquete(Size.M, false);
//            flowershop.createBouquete(Size.L,true);
//            Flower flower1=new Violet("Violet", "Violet",12.4,Seasons.SUMMER,true);
            Flower flower4=new Rose("Rose","red",4.3,Seasons.SUMMER,4);
//            Flower flower2=new Marigold("Marigold", "Marigold",10.4,Seasons.ALLSEASONAL,Groups.CELANDULA);
//            Flower flower3=new Violet("Violet", "Violet",12.4,Seasons.SUMMER,true);

//            flowershop.addProduct(flower1, 1);
            flowershop.addProduct(flower4, 10);
//            flowershop.addProduct(flower2,3);
            Bouquete bouquete=new Bouquete(Size.M,true);
//            bouquete.addFlower(flower4);
//            bouquete.addFlower(flower4);
//            bouquete.addFlower(flower2);
//            bouquete.addFlower(flower3);
//            bouquete.addFlower(flower3);
//            bouquete.addFlower(flower3);
//            bouquete.addFlower(flower3);
            flowershop.addProduct(bouquete, 1);
//            System.out.println(bouquete);
            System.out.println(flowershop);
            System.out.println(bouquete);
            flowershop.buildBouquete(Size.M, flower4,1);
//            flowershop.buyProduct(bouquete, 1);


//            flowershop.loadProducts();
//            flowershop.buildBouquete(Size.M,new Rose("Rose","red",12.4,Seasons.SUMMER,4),5);


            System.out.println(flowershop);
            System.out.println(bouquete);
        } catch (FlowerShopException f) {
            f.printStackTrace();
        }
    }


}
