package com.example.predictiveaddordering;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener
{
    private RecyclerView rView;
    private RecyclerView.Adapter adpt;
    private RecyclerView.LayoutManager layoutManager;
    private Integer uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        ManagingDB.getInstance().thisContext = this;
        uid = getIntent().getIntExtra("uid", 0);

        rView = (RecyclerView) findViewById(R.id.itemsRView);

        rView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rView.setLayoutManager(layoutManager);
        adpt = new ItemRecyclerViewAdapter(InventoryWarehouse.getInstance().getProductsList());
        rView.setAdapter(adpt);
    }

    @Override
    public void onClick(View v) {
        TextView tv = (TextView)v;
        Intent intent = new Intent(getApplicationContext(), ProductViewActivity.class);
        intent.putExtra("name", tv.getText());
        intent.putExtra("type", "P");
        intent.putExtra("uid", uid);
        startActivity(intent);
    }
}