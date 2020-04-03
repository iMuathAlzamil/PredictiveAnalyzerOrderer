package com.example.predictiveorderer;

import android.util.Pair;

import java.util.*;

public class Product
{
    private int productID;
    private String name;
    private double cost;
    private String description;
    private ArrayList<Pair<Integer, Integer>> ratings;
    private ArrayList<Pair<Integer, String>> reviews;

    public Product()
    {
        productID = 0;
        name = "";
        cost = 0.0;
        description = "";
        ratings = new ArrayList<Pair<Integer, Integer>>();
        reviews = new ArrayList<Pair<Integer, String>>();
    }

    public Product(int id, String nm, double ct, String desc)
    {
        productID = id;
        name = nm;
        cost = ct;
        description = desc;
        ratings = new ArrayList<Pair<Integer, Integer>>();
        reviews = new ArrayList<Pair<Integer, String>>();
    }

    public void addRating(int id, int rating)
    {
        Pair<Integer, Integer> newPair = new Pair<Integer, Integer>(id, rating);
        ratings.add(newPair);
    }

    public void addReview(int id, String rev)
    {
        Pair<Integer, String> newPair = new Pair<Integer, String>(id, rev);
        reviews.add(newPair);
    }

    public void setProductID(int id)
    {
        productID = id;
    }

    public void setName(String nm)
    {
        name = nm;
    }

    public void setDescription(String desc)
    {
        description = desc;
    }

    public void setCost(double ct)
    {
        cost = ct;
    }

    public int getProductID()
    {
        return productID;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public double getCost()
    {
        return cost;
    }
}
