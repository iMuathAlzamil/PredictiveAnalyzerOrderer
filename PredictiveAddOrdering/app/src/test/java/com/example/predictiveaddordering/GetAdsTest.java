package com.example.predictiveaddordering;

import android.app.Fragment;
import android.app.Instrumentation;
import android.content.Context;
import android.database.Cursor;

import org.junit.Test;
import org.mockito.invocation.MockHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GetAdsTest {

    @Test
    public void testGetAdOfAPersonWithAds() {
        ManagingDB db = ManagingDB.getInstance();
        db.open();
        Cursor csr = db.rawQuery("SELECT p.pid, p.name, p.description, p.amount, p.isSubtracted FROM UserPromotions u, Promotion p WHERE p.pid = u.pid AND u.uid = 1");
        if (csr.moveToFirst()) {
            while (!csr.isAfterLast()) {
                // If they have bought 3 or more of the same item with an ad, label it as true.
                assertTrue(true);
            }
        }
        // This should not fail to find any person.
        fail();
    }

    @Test
    public void testGetAdOfAPersonWithNoAds() {
        ManagingDB db = ManagingDB.getInstance();
        db.open();
        Cursor csr = db.rawQuery("SELECT p.pid, p.name, p.description, p.amount, p.isSubtracted FROM UserPromotions u, Promotion p WHERE p.pid = u.pid AND u.uid = 2");
        if (csr.moveToFirst()) {
            while (!csr.isAfterLast()) {
                // This should not find any person.
                fail();
            }
        }
        // This person did not buy enough of the product.
        assertTrue(true);
    }
}