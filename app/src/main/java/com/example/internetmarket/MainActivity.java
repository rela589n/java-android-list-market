package com.example.internetmarket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import com.example.internetmarket.database.generic.Database;
import com.example.internetmarket.database.product.Laptop;
import com.example.internetmarket.database.product.Product;
import com.example.internetmarket.database.product.ProductAdapter;
import com.example.internetmarket.database.product.ProductCategory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static Database products;
    public static ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddProductBase.class);
                startActivity(intent);
            }

        });
        try {
            products = new Database(getApplicationContext(), "products.dat");
        } catch (ClassNotFoundException | IOException e) {
            Toast.makeText(getApplicationContext(), "Unable connect to datasource.", Toast.LENGTH_SHORT).show();
            return;
        }

        productAdapter = new ProductAdapter(this, products, MainActivity.this);
        refreshProductsList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.menuFilter:
                Intent intent = new Intent(MainActivity.this, FilterActivity.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshProductsList();
    }

    public void refreshProductsList() {
        ListView lvMain = (ListView) findViewById(R.id.productsList);//знаходимо ліст
        lvMain.setAdapter(productAdapter);//заносимо дані із колекції
    }

    public void filterClickListener(View view) {
        Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();
    }


}
