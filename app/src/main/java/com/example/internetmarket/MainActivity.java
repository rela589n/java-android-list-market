package com.example.internetmarket;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Product> products;
    private ProductAdapter productAdapter;

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

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }

        });
        products = new ArrayList<>();

        Calendar date = Calendar.getInstance();
        date.set(2020, 1, 12);
        Laptop l = new Laptop(
                "Lorem",
                "Lorem ipsum dolor sit amet",
                12345.12,
                true,
                10,
                date,
                new ProductCategory("Aspire"),
                "Lorem",
                2016
        );

        products.add(l);

        Laptop l2 = new Laptop(
                "Ipsum",
                "Lorem ipsum dolor sit amet",
                123451.12,
                false,
                123,
                date,
                new ProductCategory("Some"),
                "Model",
                2011
        );
        products.add(l2);

        productAdapter = new ProductAdapter(this, products);
        ListView lvMain = findViewById(R.id.productsList);
        lvMain.setAdapter(productAdapter);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView lvMain = (ListView) findViewById(R.id.productsList);//знаходимо ліст
        lvMain.setAdapter(productAdapter);//заносимо дані із колекції
    }

}
