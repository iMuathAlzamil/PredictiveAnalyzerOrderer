package com.example.predictiveaddordering;

import android.database.Cursor;

import java.util.ArrayList;

public class InventoryWarehouse
{
    public ArrayList<Item> products, services;
    private static InventoryWarehouse iw = null;
    private static ManagingDB db = ManagingDB.getInstance();

    private InventoryWarehouse()
    {
        products = new ArrayList<Item>();
        services = new ArrayList<Item>();
        db.open();
        queryDB();
    }

    public static InventoryWarehouse getInstance() {
        if(iw == null) {
            iw = new InventoryWarehouse();
        }
        return iw;
    }

    public ArrayList<Item> getProductsList() {
        return products;
    }

    public ArrayList<Item> getServicesList() {
        return services;
    }

    public void addItem(Item it, boolean isProduct) {
        if(isProduct)
            products.add(it);
        else
            services.add(it);
    }

    public void updatePriceOfItem(Item it, Integer pr, boolean isProduct) {
        if(isProduct) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getIID() == it.getIID()) {
                    products.get(i).setPrice(pr);
                }
            }
        }
        else {
            for (int i = 0; i < services.size(); i++) {
                if (services.get(i).getIID() == it.getIID()) {
                    services.get(i).setPrice(pr);
                }
            }
        }
    }

    public void deleteItem(Item it, boolean isProduct) {
        if(isProduct) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getIID() == it.getIID()) {
                    products.remove(i);
                }
            }
        }
        else {
            for (int i = 0; i < services.size(); i++) {
                if (services.get(i).getIID() == it.getIID()) {
                    services.remove(i);
                }
            }
        }
    }

    public Item getItem(String name) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(name)) {
                return products.get(i);
            }
        }
        for (int i = 0; i < services.size(); i++) {
            if (services.get(i).getName().equals(name)) {
                return services.get(i);
            }
        }
        return null;
    }

    public void queryDB()
    {
        products.clear();
        services.clear();

        String nameCol1[] = {"iid", "cid", "name", "description", "stock", "price"};
        Cursor csr = db.query("Product", nameCol1, null, null);
        if (csr.moveToFirst()) {
            while (!csr.isAfterLast()) {
                Product pd = new Product(
                        csr.getInt(csr.getColumnIndex("iid")),
                        csr.getInt(csr.getColumnIndex("cid")),
                        csr.getString(csr.getColumnIndex("name")),
                        csr.getString(csr.getColumnIndex("description")),
                        csr.getInt(csr.getColumnIndex("stock")),
                        csr.getDouble(csr.getColumnIndex("price")));
                products.add(pd);
                System.out.println("PRODUCT: " + pd);
                csr.moveToNext();
            }
        }

        String nameCol2[] = {"iid", "cid", "name", "description", "startTime", "endTime", "price"};
        csr = db.query("Service", nameCol2, null, null);
        if (csr.moveToFirst()) {
            while (!csr.isAfterLast()) {
                Service sv = new Service(
                        csr.getInt(csr.getColumnIndex("iid")),
                        csr.getInt(csr.getColumnIndex("cid")),
                        csr.getString(csr.getColumnIndex("name")),
                        csr.getString(csr.getColumnIndex("description")),
                        csr.getString(csr.getColumnIndex("startTime")),
                        csr.getString(csr.getColumnIndex("endTime")),
                        csr.getDouble(csr.getColumnIndex("price")));
                services.add(sv);
                csr.moveToNext();
            }
        }
    }
}
