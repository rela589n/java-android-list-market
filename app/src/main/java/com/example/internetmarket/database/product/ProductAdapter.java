package com.example.internetmarket.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.internetmarket.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ProductAdapter extends BaseAdapter {
    protected Context ctx;
    protected LayoutInflater lInflater;
    protected ArrayList<Product> products;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        ctx = context;
        this.products = products;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }

        Product p = getProduct(position);

        if (p instanceof Laptop) {

        }

        ((TextView) view.findViewById(R.id.productName)).setText(p.name);
        ((TextView) view.findViewById(R.id.productPrice)).setText(p.price + "грн/шт.");
        ((TextView) view.findViewById(R.id.productsCount)).setText(p.count + "шт.");
        ((CheckBox) view.findViewById(R.id.productInStock)).setChecked(p.inStock);

        Calendar date = p.getDeliveryDate();

        ((TextView) view.findViewById(R.id.productDate)).setText(
                String.format(
                        "%d.%d.%d",
                        date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH),
                        date.get(Calendar.DAY_OF_MONTH)
                )
        );

        ((TextView) view.findViewById(R.id.productCategoryName)).setText(p.getCategory().getName());
        ((TextView) view.findViewById(R.id.productDescription)).setText(p.getDescription());

        LinearLayout dynamicContent = view.findViewById(R.id.dynamic_content);

        View additional = null;
        if (p instanceof Laptop) {
            additional = lInflater.inflate(R.layout.laptop_content, dynamicContent, false);

            ((TextView)additional.findViewById(R.id.laptopModelName)).setText(((Laptop) p).getModel());
            ((TextView)additional.findViewById(R.id.laptopYear)).setText(((Laptop) p).getYear().toString());
        }
        else if (p instanceof Phone) {
            additional = lInflater.inflate(R.layout.phone_content, dynamicContent, false);

            ((TextView)additional.findViewById(R.id.phoneHeight)).setText(((Phone) p).getHeight().toString());
            ((TextView)additional.findViewById(R.id.phoneWidth)).setText(((Phone) p).getWidth().toString());
            ((TextView)additional.findViewById(R.id.phoneBattery)).setText(((Phone) p).getBattery().toString());
        }

        dynamicContent.addView(additional);
        return view;
    }

    private Product getProduct(int position) {
        return ((Product) getItem(position));
    }
}
