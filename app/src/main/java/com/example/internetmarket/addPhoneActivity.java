package com.example.internetmarket;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.internetmarket.database.generic.DatabaseEntity;
import com.example.internetmarket.database.product.Laptop;
import com.example.internetmarket.database.product.Phone;
import com.example.internetmarket.database.product.ProductCategory;

import java.util.Calendar;
import java.util.HashMap;

public class addPhoneActivity extends AppCompatActivity {
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

    private EditText pWidth;
    private EditText pHeight;
    private EditText pBattery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone);

        this.name = (EditText) findViewById(R.id.addProductName);
        this.description = (EditText) findViewById(R.id.addProductDescription);
        this.price = (EditText) findViewById(R.id.addProductPrice);
        this.count = (EditText) findViewById(R.id.addProductCount);
        this.category = (EditText) findViewById(R.id.addProductCategory);
        this.inStock = (CheckBox) findViewById(R.id.addProductInStock);
        this.year = (EditText) findViewById(R.id.addProductYear);
        this.month = (EditText) findViewById(R.id.addProductMonth);
        this.day = (EditText) findViewById(R.id.addProductDay);

        // for phone
        this.pWidth = (EditText) findViewById(R.id.addPhoneWidth);
        this.pHeight = (EditText) findViewById(R.id.addPhoneHeight);
        this.pBattery = (EditText) findViewById(R.id.addPhoneBattery);

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

            this.pWidth.setText(b.getString("pWidth", ""));
            this.pHeight.setText(b.getString("pHeight", ""));
            this.pBattery.setText(b.getString("pBattery", ""));
        }
    }

    public void onAddClick(View v) {

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

            Phone phone = new Phone(name.getText().toString(),
                    description.getText().toString(),
                    Double.parseDouble(price.getText().toString()),
                    inStock.isChecked(),
                    Integer.parseInt(count.getText().toString()),
                    date,
                    new ProductCategory(category.getText().toString()),
                    Integer.parseInt(pWidth.getText().toString()),
                    Integer.parseInt(pHeight.getText().toString()),
                    Integer.parseInt(pBattery.getText().toString())
            );

            if (this.editId == null) {
                MainActivity.products.create(phone);
            }
            else { // update
                HashMap<Integer, DatabaseEntity> hashMap = MainActivity.products.readAll();

                if (hashMap.containsKey(editId)) {
                    hashMap.remove(editId);
                    hashMap.put(editId, phone);
//                    hashMap.replace(editId, laptop);
                }
            }

//            MainActivity.products.create(phone);
//            MainActivity.products.save();
            this.finish();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Someting is wrong", Toast.LENGTH_SHORT).show();
        }

    }
}
