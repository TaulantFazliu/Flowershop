package com;

public enum Size {
    M(7), L(11);

    int numberOfFlowers;

    Size(int numberOfFlowers){
        this.numberOfFlowers=numberOfFlowers;
    }

    public int getNumberOfFlowers() {
        return numberOfFlowers;
    }
}
