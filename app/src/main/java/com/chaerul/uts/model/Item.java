package com.chaerul.uts.model;

import java.io.Serializable;

public class Item implements Serializable {

    public String name;
    public int price;
    public int image;

    public Item(String name, int price, int image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Item() {

    }

}
