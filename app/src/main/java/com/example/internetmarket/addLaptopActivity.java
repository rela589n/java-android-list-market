package com.example.internetmarket;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.internetmarket.database.product.Laptop;
import com.example.internetmarket.database.product.ProductCategory;

import java.util.Calendar;

public class addLaptopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_laptop);
    }

    public void onAdd(View v) {
        EditText name = (EditText) findViewById(R.id.addProductName);
        EditText description = (EditText) findViewById(R.id.addProductDescription);

        EditText price = (EditText) findViewById(R.id.addProductPrice);
        EditText count = (EditText) findViewById(R.id.addProductCount);
        EditText category = (EditText) findViewById(R.id.addProductCategory);
        CheckBox inStock = (CheckBox) findViewById(R.id.addProductInStock);

        EditText year = (EditText) findViewById(R.id.addProductYear);
        EditText month = (EditText) findViewById(R.id.addProductMonth);
        EditText day = (EditText) findViewById(R.id.addProductDay);

        Calendar date = Calendar.getInstance();
        try {
            date.set(Calendar.YEAR, Integer.parseInt(year.getText().toString()));
            date.set(Calendar.MONTH, Integer.parseInt(month.getText().toString()));
            date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day.getText().toString()));

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Invalid date format", Toast.LENGTH_SHORT).show();
            return;
        }

        /////////////////////////////////////////////////////////////

        try {
            EditText lModel = (EditText) findViewById(R.id.addLaptopModel);
            EditText lYear = (EditText) findViewById(R.id.addLaptopYear);

            Laptop laptop = new Laptop(name.getText().toString(),
                    description.getText().toString(),
                    Double.parseDouble(price.getText().toString()),
                    inStock.isChecked(),
                    Integer.parseInt(count.getText().toString()),
                    date,
                    new ProductCategory(category.getText().toString()),
                    lModel.getText().toString(),
                    Integer.parseInt(lYear.getText().toString())
            );

            MainActivity.products.add(laptop);
            this.finish();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Someting is wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
