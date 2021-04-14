package com.chaerul.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chaerul.uts.database.OrderDB;
import com.chaerul.uts.model.Item;
import com.chaerul.uts.model.Order;

import java.text.NumberFormat;
import java.util.Locale;

public class OrderActivity extends AppCompatActivity {

    Item item;
    TextView tvName;
    TextView tvPrice;
    EditText etQuantity;
    OrderDB orderDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        item = (Item) getIntent().getSerializableExtra("Item");
        tvName = findViewById(R.id.tvName);
        tvPrice = findViewById(R.id.tvPrice);
        etQuantity = findViewById(R.id.etQuantity);

        Locale localeID = new Locale("in", "ID");
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(localeID);

        tvName.setText(item.name);
        tvPrice.setText(rupiah.format((double)item.price));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.myOrder) {
            Intent intent = new Intent(this, MyOrderActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public void addToOrder(View view) {
        Order order = new Order();
        orderDB = new OrderDB(this);

        order.item = new Item(item.name, item.price, item.image);



        try {
            order.quantity = Integer.parseInt(etQuantity.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, "You Must Input a Number!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(Integer.parseInt(etQuantity.getText().toString()) == 0) {
            Toast.makeText(this, "You Can't Add Zero!", Toast.LENGTH_SHORT).show();
            return;
        }

        //orderDB.delete();
        orderDB.insertOrder(order);

        Toast.makeText(this, "Succes!", Toast.LENGTH_SHORT).show();
        finish();
    }
}