package com.example.predictiveaddordering;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager2.widget.ViewPager2;

public class HomeActivity extends AppCompatActivity {
    private TabLayout tLayout;
    private ViewPager2 vp2;
    private Integer uid;

    @Override
    protected void onCreate (Bundle sis){
        super.onCreate(sis);
        setContentView(R.layout.activity_home);
        uid = getIntent().getIntExtra("uid", 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.getItem(0).setVisible(true);
        menu.getItem(1).setVisible(true);
        menu.getItem(2).setVisible(true);
        menu.getItem(3).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent intent;
        switch(item.getItemId())
        {
            case R.id.products:
                intent = new Intent(getApplicationContext(), ProductActivity.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
                return(true);
            case R.id.services:
                intent = new Intent(getApplicationContext(), ServiceActivity.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
                return(true);
            case R.id.promotions:
                intent = new Intent(getApplicationContext(), PromotionsActivity.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
                return(true);
            case R.id.orders:
                intent = new Intent(getApplicationContext(), OrderActivity.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }
}
