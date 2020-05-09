package com.example.predictiveaddordering;

public class Product extends Item
{
    private int stock;

    public Product() {
        super();
        setStock(0);
    }

    public Product(int u_id, int c_id, String nm, String desc, int stk, double pr) {
        super(u_id, c_id, nm, desc, pr);
        setStock(stk);
    }

    public void setStock(int stk) {
        stock = stk;
    }

    public int getStock() {
        return stock;
    }
}
