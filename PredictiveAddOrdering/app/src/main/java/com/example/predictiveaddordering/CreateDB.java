package com.example.predictiveaddordering;

import android.content.*;
import android.database.sqlite.*;

public class CreateDB extends SQLiteOpenHelper {
    public static final String DBNAME = "predOrder.db";
    public static final int DBVERSION = 1;

    public CreateDB(Context ct) {
        super(ct, DBNAME, null, DBVERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Users (" +
                "  uid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  firstname TEXT," +
                "  lastname TEXT," +
                "  username TEXT," +
                "  password TEXT," +
                "  addressFirst TEXT," +
                "  addressSecond TEXT," +
                "  city TEXT, " +
                "  state TEXT," +
                "  zip TEXT)");

        db.execSQL("CREATE TABLE Product (" +
                "  iid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  cid INTEGER," +
                "  name TEXT," +
                "  description TEXT," +
                "  stock NUMBER," +
                "  price NUMBER)");

        db.execSQL("CREATE TABLE Service (" +
                "  iid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  cid INTEGER," +
                "  name TEXT," +
                "  description TEXT," +
                "  startTime TEXT," +
                "  endTime TEXT," +
                "  price NUMBER)");

        db.execSQL("CREATE TABLE Company (" +
                "  cid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  name TEXT)");

        db.execSQL("CREATE TABLE ProductRating (" +
                "  iid INTEGER," +
                "  uid INTEGER," +
                "  rating NUMBER)");

        db.execSQL("CREATE TABLE Promotion (" +
                "  pid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  iid INTEGER," +
                "  cid INTEGER," +
                "  name TEXT," +
                "  description TEXT," +
                "  amount NUMBER," +
                "  isSubtracted BOOLEAN)");

        db.execSQL("CREATE TABLE UserPromotions (" +
                "  uid INTEGER," +
                "  pid INTEGER," +
                "  isUsed BOOLEAN," +
                "  PRIMARY KEY(uid, pid))");

        db.execSQL("CREATE TABLE UserOrder (" +
                "  oid INTEGER," +
                "  iid INTEGER," +
                "  purchaseDate DATE," +
                "  details TEXT, " +
                "  PRIMARY KEY(oid, iid, purchaseDate))");

        db.beginTransaction();
        db.insert("Users", null, getInsertValuesUsers("Jane", "Doe", "jdoe", "jdoe", "123 Fake Street", "", "Los Angeles", "CA", "90009"));
        db.insert("Users", null, getInsertValuesUsers("John", "Smith", "jsmith", "js123", "44 Star Road", "P.O. Box 1010", "New York City", "NY", "10022"));
        db.insert("Users", null, getInsertValuesUsers("Homer", "Simpson", "hsimpson", "hs1111", "742 Evergreen Terrace", "", "Springfield", "IL", "62629"));
        db.insert( "Product", null, getInsertValuesProduct("Apples", 1,"Red Delicious Apples Ripe For Sale", 100, 2.99));
        db.insert( "Product", null, getInsertValuesProduct("Oranges", 1,"Bright Orange Oranges Ripe For Sale", 150, 2.49));
        db.insert( "Product", null, getInsertValuesProduct("Grapes", 1,"Bright Red Globe Grapes Ripe For Sale", 120, 1.99));
        db.insert( "Product", null, getInsertValuesProduct("Watermelon", 1,"Bright Red Watermelons Ripe For Sale", 50, 4.99));
        db.insert( "Product", null, getInsertValuesProduct("Lemons", 1,"Bright Yellow Lemons Ripe For Sale", 200, 1.19));
        db.insert( "Product", null, getInsertValuesProduct("Carrots", 2,"Bright Orange Carrots Ripe For Sale", 180, 1.49));
        db.insert( "Product", null, getInsertValuesProduct("Celery", 2,"Bright Green-White Celery Ripe For Sale", 190, 1.29));
        db.insert( "Product", null, getInsertValuesProduct("Cucumber", 2,"Bright Green Cucumbers Ripe For Sale", 210, 1.39));
        db.insert( "Product", null, getInsertValuesProduct("Nuts", 3,"Giant Mixed Nuts For Sale", 140, 3.29));
        db.insert( "Product", null, getInsertValuesProduct("Vanilla Ice Cream", 4,"French Vanilla Ice Cream For Sale", 30, 3.99));
        db.insert( "Product", null, getInsertValuesProduct("Chocolate Ice Cream", 4,"Dark Chocolate Ice Cream For Sale", 50, 4.29));
        db.insert( "Product", null, getInsertValuesProduct("Mint Chocolate Ice Cream", 4,"Minty Chocolate Chip Ice Cream For Sale", 60, 3.99));
        db.insert( "Service", null, getInsertValuesServiceStart("Dog Walking", 5,"Walk Your Dog For 1 Hour Per Day", "10-MAY-2020 12:00:00", "31-MAY-2020 23:59:59", 100.00));
        db.insert( "Service", null, getInsertValuesService("Dog Grooming", 5,"Groom Your Dog In A 1/2 Hour Per Week", "08-MAY-2020 12:00:00", "31-MAY-2020 23:59:59", 15.00));
        db.insert( "Service", null, getInsertValuesService("Dog Training", 5,"Train Your Dog For 3 Hours Per Week", "12-MAY-2020 12:00:00", "31-MAY-2020 23:59:59", 199.00));
        db.insert( "Service", null, getInsertValuesService("Cat Grooming", 6,"Groom Your Cat For 1/2 Hour Per Week", "10-MAY-2020 12:00:00", "31-MAY-2020 23:59:59", 12.50));
        db.insert( "Service", null, getInsertValuesService("Cat Training", 6,"Train Your Cat For 2 Hours Per Week", "12-MAY-2020 12:00:00", "31-MAY-2020 23:59:59", 149.00));
        db.insert( "Company", null, getInsertValuesCompany("Fruitastic Garden"));
        db.insert( "Company", null, getInsertValuesCompany("Mixed Vegetable Farm"));
        db.insert( "Company", null, getInsertValuesCompany("Nutty Bar Co"));
        db.insert( "Company", null, getInsertValuesCompany("Haagen Dazs"));
        db.insert( "Company", null, getInsertValuesCompany("Happy Dog Company"));
        db.insert( "Company", null, getInsertValuesCompany("Pretty Kitty Co"));
        db.insert( "ProductRating", null, getInsertValuesRating(1, 1, 5));
        db.insert( "Promotion", null, getInsertValuesPromotion(1, 1, "Save On Our Fruits", "Save $5 On 5 Lbs Order Of Apples", 5.0, true));
        db.insert( "Promotion", null, getInsertValuesPromotion(6, 2, "Save On Our Vegetables", "Save 20% On Any Order Of Carrots", 0.2, false));
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public ContentValues getInsertValuesUsers(String first, String last, String user, String pass, String addrF, String addrS, String ct, String st, String zp)
    {
        ContentValues cv = new ContentValues();
        cv.put("firstname", first);
        cv.put("lastname", last);
        cv.put("username", user);
        cv.put("password", pass);
        cv.put("addressFirst", addrF);
        cv.put("addressSecond", addrS);
        cv.put("city", ct);
        cv.put("state", st);
        cv.put("zip", zp);
        return cv;
    }

    public ContentValues getInsertValuesProduct(String name, Integer cid, String desc, Integer stk, Double pr)
    {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("cid", cid);
        cv.put("description", desc);
        cv.put("stock", stk);
        cv.put("price", pr);
        return cv;
    }

    public ContentValues getInsertValuesService(String name, Integer cid, String desc, String start, String end, Double pr)
    {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("description", desc);
        cv.put("startTime", start);
        cv.put("endTime", end);
        cv.put("price", pr);
        return cv;
    }

    public ContentValues getInsertValuesServiceStart(String name, Integer cid, String desc, String start, String end, Double pr)
    {
        ContentValues cv = new ContentValues();
        cv.put("iid", 1000);
        cv.put("name", name);
        cv.put("description", desc);
        cv.put("startTime", start);
        cv.put("endTime", end);
        cv.put("price", pr);
        return cv;
    }

    public ContentValues getInsertValuesCompany(String name)
    {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        return cv;
    }

    public ContentValues getInsertValuesRating(Integer iid, Integer uid, Integer rate)
    {
        ContentValues cv = new ContentValues();
        cv.put("iid", iid);
        cv.put("uid", uid);
        cv.put("rating", rate);
        return cv;
    }

    public ContentValues getInsertValuesOrder(Integer oid, Integer iid, String date, String det)
    {
        ContentValues cv = new ContentValues();
        cv.put("oid", oid);
        cv.put("iid", iid);
        cv.put("purchaseDate", date);
        cv.put("details", det);
        return cv;
    }

    public ContentValues getInsertValuesPromotion(Integer iid, Integer cid, String name, String desc, Double amt, Boolean isSub)
    {
        ContentValues cv = new ContentValues();
        cv.put("iid", iid);
        cv.put("cid", cid);
        cv.put("name", name);
        cv.put("description", desc);
        cv.put("amount", amt);
        cv.put("isSubtracted", isSub);
        return cv;
    }

    public ContentValues getInsertValuesUserPromotion(Integer uid, Integer pid, Boolean isUsed)
    {
        ContentValues cv = new ContentValues();
        cv.put("uid", uid);
        cv.put("pid", pid);
        cv.put("isUsed", isUsed);
        return cv;
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
