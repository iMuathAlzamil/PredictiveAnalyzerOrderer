package com.example.predictiveaddordering;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class PromotionsActivity extends AppCompatActivity {
    private Integer uid;
    private TreeMap<Integer, Integer> counts;

    @Override
    protected void onCreate (Bundle sis){
        super.onCreate(sis);
        setContentView(R.layout.activity_promotion);
        TextView tv = findViewById(R.id.PromotionDetailsText);
        uid = getIntent().getIntExtra("uid", 0);

        counts = new TreeMap<Integer, Integer>();
        ManagingDB db = ManagingDB.getInstance();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        System.out.println(cal.getTime().toString());
        Cursor csr = db.rawQuery("SELECT * FROM UserOrder WHERE purchaseDate >= date('now','-1 month') AND iid = " + uid);
        tv.setText("");
        if (csr.moveToFirst()) {
            while (!csr.isAfterLast()) {
                Integer oid = csr.getInt(csr.getColumnIndex("oid"));
                if(counts.containsKey(oid)) {
                    counts.put(oid, counts.get(oid) + 1);
                }
                else {
                    counts.put(oid, 1);
                }
                csr.moveToNext();
            }
        }
        System.out.println(counts);
        Iterator<Integer> keys = counts.keySet().iterator();
        ArrayList<Integer> heightenedKeys = new ArrayList<Integer>();
        while(keys.hasNext()) {
            Integer key = keys.next();
            Integer val = counts.get(key);
            if(val >= 3) {
                heightenedKeys.add(key);
            }
        }
        System.out.println(heightenedKeys);

        Set<Integer> pids = new TreeSet<Integer>();
        csr = db.rawQuery("SELECT pid FROM UserPromotions WHERE uid = " + uid);
        if (csr.moveToFirst()) {
            while (!csr.isAfterLast()) {
                pids.add(csr.getInt(csr.getColumnIndex("pid")));
                csr.moveToNext();
            }
        }
        System.out.println(pids);

        for(int i = 0; i < heightenedKeys.size(); i++) {
            csr = db.rawQuery("SELECT * FROM Promotion WHERE iid = " + heightenedKeys.get(i));
            if (csr.moveToFirst()) {
                while (!csr.isAfterLast()) {
                    Integer promo = csr.getInt(csr.getColumnIndex("pid"));
                    if(!pids.contains(promo)) {
                        db.insertUserPromotionData(uid, promo);
                        pids.add(promo);
                    }
                    csr.moveToNext();
                }
            }
        }

        csr = db.rawQuery("SELECT p.pid, p.name, p.description, p.amount, p.isSubtracted FROM UserPromotions u, Promotion p WHERE p.pid = u.pid AND u.uid = " + uid);
        if (csr.moveToFirst()) {
            while (!csr.isAfterLast()) {
                System.out.println("DONE");
                String details = csr.getString(csr.getColumnIndex("description"));
                String name = csr.getString(csr.getColumnIndex("name"));
                Double amt = csr.getDouble(csr.getColumnIndex("amount"));
                Integer isSub = csr.getInt(csr.getColumnIndex("isSubtracted"));
                Integer pid = csr.getInt(csr.getColumnIndex("pid"));
                tv.setText(tv.getText() + "\n\nPromo ID: " + pid + "\nName: " + name + "\nDetails: " + details + "\nAmount: ");
                if(isSub == 0) {
                    tv.setText(tv.getText() + "\n" + amt + "%");
                }
                else {
                    tv.setText(tv.getText() + "\n$" + String.format("%.2f", amt));
                }
                csr.moveToNext();
            }
        }
    }
}
