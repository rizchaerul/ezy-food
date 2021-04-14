package com.chaerul.uts.model;

public class Order {

    public long id;
    public Item item;
    public int quantity;

    public Order(long id, Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.id = id;
    }

    public Order(Item item) {
        this.item = item;
    }

    public Order() {

    }

}
