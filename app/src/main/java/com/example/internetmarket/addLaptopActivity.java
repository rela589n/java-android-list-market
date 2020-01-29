package com.example.internetmarket;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.internetmarket.database.generic.DatabaseEntity;
import com.example.internetmarket.database.product.Laptop;
import com.example.internetmarket.database.product.ProductCategory;

import java.util.Calendar;
import java.util.HashMap;

public class addLaptopActivity extends AppCompatActivity {

    private Integer editId = null;

    private EditText name;
    private EditText description;
    private EditText price;
    private EditText count;
    private EditText category;

    private CheckBox inStock;

    private EditText year;
    private EditText month;
    private EditText day;

    private EditText lModel;
    private EditText lYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_laptop);

        this.name = (EditText) findViewById(R.id.addProductName);
        this.description = (EditText) findViewById(R.id.addProductDescription);
        this.price = (EditText) findViewById(R.id.addProductPrice);
        this.count = (EditText) findViewById(R.id.addProductCount);
        this.category = (EditText) findViewById(R.id.addProductCategory);
        this.inStock = (CheckBox) findViewById(R.id.addProductInStock);
        this.year = (EditText) findViewById(R.id.addProductYear);
        this.month = (EditText) findViewById(R.id.addProductMonth);
        this.day = (EditText) findViewById(R.id.addProductDay);

        // for laptop
        this.lModel = (EditText) findViewById(R.id.addLaptopModel);
        this.lYear = (EditText) findViewById(R.id.addLaptopYear);

        // extract values from bundle
        Bundle b = getIntent().getExtras();

        if (b != null) {
            // extract edit id
            this.editId = b.getInt("editId");

            this.name.setText(b.getString("name", ""));
            this.description.setText(b.getString("description", ""));
            this.price.setText(b.getString("price", ""));
            this.count.setText(b.getString("count", ""));
            this.category.setText(b.getString("category", ""));

            this.inStock.setChecked(b.getBoolean("inStock", false));

            this.year.setText(b.getString("year", ""));
            this.month.setText(b.getString("month", ""));
            this.day.setText(b.getString("day", ""));

            this.lModel.setText(b.getString("lModel", ""));
            this.lYear.setText(b.getString("lYear", ""));

        }

    }

    public void onAdd(View v) {

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

            if (this.editId == null) {
                MainActivity.products.create(laptop);
            }
            else { // update
                HashMap<Integer, DatabaseEntity> hashMap = MainActivity.products.readAll();

                if (hashMap.containsKey(editId)) {
                    hashMap.remove(editId);
                    hashMap.put(editId, laptop);
//                    hashMap.replace(editId, laptop);
                }
            }

            MainActivity.products.save();
            this.finish();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Someting is wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
