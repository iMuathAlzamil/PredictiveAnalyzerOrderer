package com.example.predictiveaddordering;

import java.util.ArrayList;

public abstract class Item
{
    protected int iid, cid;
    protected String name, description;
    protected double price;
    protected ArrayList<Integer> ratings = new ArrayList<Integer>();

    public Item() {
        setIID(0);
        setCID(0);
        setName("");
        setDescription("");
        setPrice(0.0);
    }

    public Item(int u_id, int c_id, String nm, String desc, double pr) {
        setIID(u_id);
        setCID(c_id);
        setName(nm);
        setDescription(desc);
        setPrice(pr);
    }

    public void setIID(int id) {
        iid = id;
    }

    public void setCID(int id) {
        cid = id;
    }

    public void setName(String nm) {
        name = nm;
    }

    public void setDescription(String desc) {
        description = desc;
    }

    public void setPrice(double pr) {
        price = pr;
    }

    public int getIID() {
        return iid;
    }

    public int getCID() {
        return cid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return name;
    }

    public void addRating(int rating) { ratings.add(rating); }

    public int numRatings() { return ratings.size(); }

    public double getAverageRating() {
        double total = 0.0;
        for(int i = 0; i < ratings.size(); i++) {
            total += ratings.get(i);
        }
        if(ratings.size() != 0) {
            return total / ratings.size();
        }
        else {
            return 0;
        }
    }
}
