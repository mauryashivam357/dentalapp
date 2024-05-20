package com.example.navigation_drawer;

// Item.java -------------------------------------
public class Item {
    public static final int TYPE_A = 0;
    public static final int TYPE_B = 1;
    public static final int TYPE_C = 2;

    private String data;
    private int type;

    public Item(String data, int type) {
        this.data = data;
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public int getType() {
        return type;
    }
}
