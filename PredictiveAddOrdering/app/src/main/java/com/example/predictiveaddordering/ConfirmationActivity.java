package com.example.predictiveaddordering;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.Random;

import javax.net.ssl.ManagerFactoryParameters;

public class ConfirmationActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout tLayout;
    private ViewPager2 vp2;
    private Integer uid;

    @Override
    protected void onCreate (Bundle sis){
        super.onCreate(sis);
        setContentView(R.layout.confirmation);
        uid = getIntent().getIntExtra("uid", 0);
        String name = getIntent().getStringExtra("name");
        String type = getIntent().getStringExtra("type");

        int iid = 0;
        ManagingDB db = ManagingDB.getInstance();
        if(type.equals("P")) {
            Integer quantity = getIntent().getIntExtra("quantity", 0);
            Cursor csr = db.rawQuery("SELECT iid FROM Product WHERE name = '" + name + "'");
            if (csr.moveToFirst()) {
                while (!csr.isAfterLast()) {
                    System.out.println("IID: " + iid);
                    iid = csr.getInt(csr.getColumnIndex("iid"));
                    csr.moveToNext();
                }
            }

            db.insertOrderData(iid, uid, java.util.Calendar.getInstance().getTime().toString(), "Quantity: quantity");
        }
        else {
            String stime = getIntent().getStringExtra("stime");
            String dayof = getIntent().getStringExtra("dayof");
            Cursor csr = db.rawQuery("SELECT iid FROM Service WHERE name = '" + name + "'");
            if (csr.moveToFirst()) {
                while (!csr.isAfterLast()) {
                    iid = csr.getInt(csr.getColumnIndex("iid"));
                    csr.moveToNext();
                }
            }

            db.insertOrderData(iid, uid, java.util.Calendar.getInstance().getTime().toString(), dayof + " @ " + stime);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra("uid", uid);
        startActivity(intent);
    }
}
