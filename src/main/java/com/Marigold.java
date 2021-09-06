package com;

public class Marigold extends Flower implements Horns {
    private Groups group;
    private Seasons seasons;

    public Marigold(String type, String color, double price, Seasons seasons, Groups group) throws FlowerShopException {
        super(type, color, price);
        if (!group.equals(Groups.TAGETES) && !group.equals(Groups.CELANDULA)) {
            throw new FlowerShopException("Marigold must be of group Tageta or Celandula");
        } else {
            this.group = group;
        }
        if (!seasons.equals(Seasons.ALLSEASONAL)) {
            throw new FlowerShopException("Marigold is an All Seasonal Flower");
        } else {
            this.seasons = seasons;
        }
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    @Override
    public boolean hasHorns() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s, Marigold[ of group=%s, of season=%s]",
                super.toString(), this.group, this.seasons);
    }
}
