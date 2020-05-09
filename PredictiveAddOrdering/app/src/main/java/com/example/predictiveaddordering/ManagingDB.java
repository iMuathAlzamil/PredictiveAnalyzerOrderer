package com.example.predictiveaddordering;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;

public class ManagingDB
{
    public Context thisContext;
    private SQLiteDatabase db;
    private CreateDB helper;
    private static ManagingDB dbmInstance = null;

    private ManagingDB() {

    }

    public static ManagingDB getInstance() {
        if(dbmInstance == null) {
            dbmInstance = new ManagingDB();
        }
        return dbmInstance;
    }

    public ManagingDB open() throws SQLException {
        helper = new CreateDB(thisContext);
        db = helper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
        helper.close();
    }

    public void insertUserData(String first, String last, String user, String pass, String addrF, String addrS, String ct, String st, String zp) {
        ContentValues cv = helper.getInsertValuesUsers(first, last, user, pass, addrF, addrS, ct, st, zp);
        db.beginTransaction();
        db.insert("Users", null, cv);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void insertOrderData(Integer oid, Integer iid, String date, String det) {
        ContentValues cv = helper.getInsertValuesOrder(oid, iid, date, det);
        db.beginTransaction();
        db.insertOrThrow("UserOrder", null, cv);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void insertRatingData(Integer iid, Integer uid, Integer rate) {
        ContentValues cv = helper.getInsertValuesRating(iid, uid, rate);
        db.beginTransaction();
        db.insert("Rating", null, cv);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void insertUserPromotionData(Integer uid, Integer pid) {
        ContentValues cv = helper.getInsertValuesUserPromotion(uid, pid, true);
        db.beginTransaction();
        db.insert("UserPromotions", null, cv);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void modifyProductData(Integer iid, Integer stk) {
        ContentValues cv = new ContentValues();
        cv.put("stock", stk);
        db.update("Product", cv, "iid=\'" + iid + "\'", null);
        db.beginTransaction();
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public Cursor query(String table, String cols[], String selection, String args[]) {
        Cursor cursor = db.query(table, cols, selection, args, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor rawQuery(String queryStr)
    {
        Cursor cursor = db.rawQuery(queryStr, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
