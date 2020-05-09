package com.example.predictiveaddordering;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductViewActivity extends AppCompatActivity implements View.OnClickListener
{
    private String name, type;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewproduct);

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

    @Override
    public void onClick(View v) {
        TextView quantityText = findViewById(R.id.quantityText);
        TextView errorText = findViewById(R.id.errorText);
        try {
            Integer quantity = Integer.parseInt(quantityText.getText().toString());
            if(quantity > 0) {
                errorText.setText("");
                Intent intent = new Intent(getApplicationContext(), ConfirmationActivity.class);
                intent.putExtra("quantity", quantity);
                intent.putExtra("name", name);
                intent.putExtra("uid", uid);
                intent.putExtra("type", type);
                startActivity(intent);
            }
            else {
                errorText.setText("Error: Cannot Buy 0 Quantity");
            }
        } catch(Exception ex) {
            errorText.setText("Error: No Quantity Specified");
        }
    }
}