package com;

public class Rose extends Flower implements Horns {
    private int nmPetals;
    Seasons seasons;

    public Rose(String type, String color, double price, Seasons seasons, int nmPetals) throws FlowerShopException {
        super(type, color, price);
        if (nmPetals < 4 || nmPetals > 5) {
            throw new FlowerShopException("The number of petals should be either 4 or 5 !");
        } else {
            this.nmPetals = nmPetals;
        }
        if (!seasons.equals(Seasons.SUMMER)) {
            throw new FlowerShopException("Rose is a summer flower");
        } else {
            this.seasons = seasons;
        }
    }

    public int getNmPetals() {
        return nmPetals;
    }

    public void setNmPetals(int nmPetals) {
        this.nmPetals = nmPetals;
    }

    @Override
    public boolean hasHorns() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s, Rose[has horns=%b, number of petals=%d, season=%s]",
                super.toString(), this.hasHorns(), this.nmPetals, this.seasons);
    }
}
