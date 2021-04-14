package com.chaerul.uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chaerul.uts.database.OrderDB;
import com.chaerul.uts.model.Item;
import com.chaerul.uts.model.Order;
import com.chaerul.uts.recycleView.ItemAdapterRecycler;
import com.chaerul.uts.recycleView.MyOrderAdapter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MyOrderActivity extends AppCompatActivity {

    ArrayList<Item> items;

    ArrayList<Order> orders;
    RecyclerView rvItems;
    OrderDB orderDB;
    TextView tvTotalPrice;
    int totalPrice;
    Item item;
    Button btBuy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        showPrice();

        int totalPrice = 0;
        for(int i = 0; i < orders.size(); i++) {
            totalPrice = totalPrice + (orders.get(i).item.price * orders.get(i).quantity);

            item = orders.get(i).item;
            items.add(item);
        }

        MyOrderAdapter adapter;
        adapter = new MyOrderAdapter(this, items, orders);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        rvItems.setAdapter(adapter);
    }

    public void showPrice() {
        orderDB = new OrderDB(this);
        orders = orderDB.getOrders();
        items = new ArrayList<Item>();
        rvItems = findViewById(R.id.rvItems);

        totalPrice = 0;
        for(int i = 0; i < orders.size(); i++) {
            totalPrice = totalPrice + (orders.get(i).item.price * orders.get(i).quantity);
        }

        Locale localeID = new Locale("in", "ID");
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(localeID);


        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        if(totalPrice == 0) {
            tvTotalPrice.setText("You Haven't Order Anything Yet");
            btBuy = findViewById(R.id.btBuy);
            btBuy.setVisibility(View.GONE);
        }
        else tvTotalPrice.setText(rupiah.format((double)totalPrice));

        //tvTotalPrice.setText("Rp. " + totalPrice);
    }

    public void buy(View view) {
        Intent intent = new Intent(this, SuccessActivity.class);
        intent.putExtra("Price", totalPrice);
        startActivity(intent);
        finish();
    }
}