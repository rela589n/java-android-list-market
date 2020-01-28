package com.example.internetmarket;

import android.os.Build;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.internetmarket.database.generic.DatabaseEntity;
import com.example.internetmarket.database.product.Product;

import java.util.HashMap;
import java.util.Map;

public class FilterActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        ((Switch) findViewById(R.id.filterInStockEnabled)).setChecked(inStock != null);
        ((CheckBox) findViewById(R.id.filterInStock)).setChecked(inStock != null ? inStock : false);

        ((Switch) findViewById(R.id.filterCategoryEnabled)).setChecked(categoryName != null);
        ((EditText) findViewById(R.id.filterCategory)).setText((categoryName != null && categoryName.length() != 0) ? categoryName : "");

    }

    private static Boolean inStock = null;
    private static String categoryName = null;

    public void validate() {
        Switch inStockSwitch = (Switch) findViewById(R.id.filterInStockEnabled);
        CheckBox inStockCheck = (CheckBox) findViewById(R.id.filterInStock);

        Switch categorySwitch = (Switch) findViewById(R.id.filterCategoryEnabled);
        EditText categoryEdit = (EditText) findViewById(R.id.filterCategory);

        if (inStockSwitch.isChecked()) {
            inStock = inStockCheck.isChecked();
        } else {
            inStock = null;
        }
        if (categorySwitch.isChecked()) {
            categoryName = categoryEdit.getText().toString().trim();
        }
        else {
            categoryName = null;
        }
    }

    public void onFilterClick(View v) {
        validate();

        HashMap<Integer, DatabaseEntity> resutSet = new HashMap<>();
        for (Map.Entry<Integer, DatabaseEntity> entry : MainActivity.products.readAll().entrySet()) {
            Integer key = entry.getKey();
            DatabaseEntity value = entry.getValue();

            if (filter(value)) {
                resutSet.put(key, value);
            }
        }

        MainActivity.productAdapter.setProducts(resutSet);
        this.finish();
    }

    private boolean filter(DatabaseEntity value) {
        Product product = (Product) value;
        return filterInStock(product) && filterCategory(product);
    }

    private boolean filterInStock(Product product) {
        if (inStock == null) {
            return true;
        }

        return product.getInStock().equals(inStock);
    }

    private boolean filterCategory(Product product) {
        if (categoryName == null) {
            return true;
        }

        return product.getCategory().getName().trim().equals(categoryName);
    }
}
