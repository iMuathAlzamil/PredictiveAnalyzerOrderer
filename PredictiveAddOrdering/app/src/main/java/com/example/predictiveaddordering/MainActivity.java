package com.example.predictiveaddordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ManagingDB db = ManagingDB.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db.thisContext = this;
        db.open();
    }

    @Override
    public void onClick(View view) {
        TextView nameText = findViewById(R.id.usernameText);
        TextView descriptionText = findViewById(R.id.passwordText);
        TextView resultText = findViewById(R.id.resultText);
        String args[] = {nameText.getText().toString(), descriptionText.getText().toString()};
        String nameCol[] = {"uid"};
        Cursor cs = db.query("Users", nameCol, "username = ? AND password = ?", args);
        if(cs.moveToFirst()) {
            int id = cs.getInt(0);
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.putExtra("uid", id);
            startActivity(intent);
        }
        else {
            resultText.setText("Error: Incorrect Username/Password.");
        }
    }
}
