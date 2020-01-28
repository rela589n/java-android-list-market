package com.example.internetmarket;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AddProductBase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_base);

        findViewById(R.id.goBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddProductBase.this.finish();
            }
        });

        findViewById(R.id.addLaptop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddProductBase.this, addLaptopActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.addPhone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddProductBase.this, addPhoneActivity.class);
                startActivity(intent);
            }
        });

    }
}
