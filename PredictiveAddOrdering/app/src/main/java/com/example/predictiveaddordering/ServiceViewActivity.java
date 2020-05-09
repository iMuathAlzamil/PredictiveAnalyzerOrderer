package com.example.predictiveaddordering;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class ServiceViewActivity extends AppCompatActivity implements View.OnClickListener
{
    private String name, type;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewservice);

        InventoryWarehouse iw = InventoryWarehouse.getInstance();
        name = getIntent().getStringExtra("name");
        type = getIntent().getStringExtra("type");
        uid = getIntent().getIntExtra("uid", 0);
        Item it = iw.getItem(name);

        TextView nameText = findViewById(R.id.productNameText);
        TextView descriptionText = findViewById(R.id.descriptionText);
        TextView priceText = findViewById(R.id.priceText);
        nameText.setText(it.getName());
        descriptionText.setText(it.getDescription());
        priceText.setText("$" + String.format("%.2f", it.getPrice()));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        TimePicker sTimer = findViewById(R.id.startTimePicker);
        DatePicker dPicker = findViewById(R.id.dayOfPicker);
        Intent intent = new Intent(getApplicationContext(), ConfirmationActivity.class);
        intent.putExtra("stime", sTimer.getHour() + ":" + sTimer.getMinute());
        intent.putExtra("dayof", dPicker.getMonth() + "/" + dPicker.getDayOfMonth() + "/" + dPicker.getYear());
        intent.putExtra("name", name);
        intent.putExtra("uid", uid);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}