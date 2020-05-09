package com.example.predictiveaddordering;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {
    private Integer uid;

    @Override
    protected void onCreate (Bundle sis){
        super.onCreate(sis);
        setContentView(R.layout.activity_order);
        TextView tv = findViewById(R.id.orderDetailsText);
        uid = getIntent().getIntExtra("uid", 0);

        ManagingDB db = ManagingDB.getInstance();
        Cursor csr = db.rawQuery("SELECT o.details, p.name, o.purchaseDate FROM UserOrder o, Product p WHERE o.oid = p.iid AND o.iid = " + uid);
        int orderNum = 1;
        tv.setText("");
        if (csr.moveToFirst()) {
            while (!csr.isAfterLast()) {
                String details = csr.getString(csr.getColumnIndex("details"));
                String name = csr.getString(csr.getColumnIndex("name"));
                String pdate = csr.getString(csr.getColumnIndex("purchaseDate"));
                tv.setText(tv.getText() + "\n\nID: " + orderNum + "\nProduct Name: " + name + "\n" + details + "\nPurchased On: " + pdate);
                csr.moveToNext();
                orderNum++;
            }
        }

        csr = db.rawQuery("SELECT o.details, s.name, o.purchaseDate FROM UserOrder o, Service s WHERE o.oid = s.iid AND o.iid = " + uid);
        orderNum = 100;
        if (csr.moveToFirst()) {
            while (!csr.isAfterLast()) {
                String details = csr.getString(csr.getColumnIndex("details"));
                String name = csr.getString(csr.getColumnIndex("name"));
                String pdate = csr.getString(csr.getColumnIndex("purchaseDate"));
                tv.setText(tv.getText() + "\n\nID: " + orderNum + "\nService Name: " + name + "\nMeet Time/Date: " + details + "\nPurchased On: " + pdate);
                csr.moveToNext();
                orderNum++;
            }
        }
    }

}
