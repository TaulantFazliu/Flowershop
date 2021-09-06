package com;

public class Violet extends Flower implements Horns {
    private boolean flourishesWeekly;
    private Seasons seasons;

    public Violet(String type, String color, double price, Seasons seasons, boolean flourishesWeekly) throws FlowerShopException {
        super(type, color, price);
        if (!seasons.equals(Seasons.SPRING) && !seasons.equals(Seasons.SUMMER)) {
            throw new FlowerShopException("Violet must be a summer or spring flower");
        } else {
            this.seasons = seasons;
        }
        this.flourishesWeekly = flourishesWeekly;
    }

    @Override
    public boolean hasHorns() {
        return false;
    }

    public boolean isFlourishesWeekly() {
        return flourishesWeekly;
    }

    public void setFlourishesWeekly(boolean flourishesWeekly) {
        this.flourishesWeekly = flourishesWeekly;
    }

    @Override
    public String toString() {
        return String.format("%s, Violet[ has horns=%b, season=%s, flourishes weekly=%b]",
                super.toString(), this.hasHorns(), this.seasons, this.flourishesWeekly);
    }
}
