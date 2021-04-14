package com.chaerul.uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.chaerul.uts.database.OrderDB;
import com.chaerul.uts.model.Item;
import com.chaerul.uts.model.Order;
import com.chaerul.uts.recycleView.SuccessAdapter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class SuccessActivity extends AppCompatActivity {

    ArrayList<Item> items;

    ArrayList<Order> orders;
    RecyclerView rvItems;
    OrderDB orderDB;
    TextView tvTotalPrice;
    int totalPrice;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        showPrice();
        int totalPrice = 0;
        for(int i = 0; i < orders.size(); i++) {
            totalPrice = totalPrice + (orders.get(i).item.price * orders.get(i).quantity);

            item = orders.get(i).item;
            items.add(item);
        }

        SuccessAdapter adapter;
        adapter = new SuccessAdapter(this, items, orders);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        rvItems.setAdapter(adapter);
        orderDB.removeAllItem();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_success, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.myOrder) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
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
        //totalPrice = getIntent().getIntExtra("Price", 0);

        Locale localeID = new Locale("in", "ID");
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(localeID);
        //rupiah.format((double)totalPrice

        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        tvTotalPrice.setText("Total: " + rupiah.format((double)totalPrice));
        //tvTotalPrice.setText("Rp. " + totalPrice);
    }
}