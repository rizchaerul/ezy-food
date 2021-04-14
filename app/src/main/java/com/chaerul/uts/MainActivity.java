package com.chaerul.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chaerul.uts.model.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    public void drinkMenu(View view) {
        Intent intent = new Intent(this, SelectMenuActivity.class);

        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("Air Mineral", 20000, R.drawable.drink));
        items.add(new Item("Jus Apel", 20000, R.drawable.drink));
        items.add(new Item("Jus Jeruk", 20000, R.drawable.drink));
        items.add(new Item("Jus Alpukat", 20000, R.drawable.drink));
        items.add(new Item("Jus Mangga", 20000, R.drawable.drink));
        items.add(new Item("Jus Melon", 20000, R.drawable.drink));
        items.add(new Item("Jus Tomat", 20000, R.drawable.drink));
        items.add(new Item("Jus Semangka", 20000, R.drawable.drink));


        intent.putExtra("Items", items);
        startActivity(intent);
    }

    public void snackMenu(View view) {
        Intent intent = new Intent(this, SelectMenuActivity.class);
        ArrayList<Item> items = new ArrayList<Item>();

        items.add(new Item("Ciki", 20000, R.drawable.snack));
        items.add(new Item("Kerupuk", 20000, R.drawable.snack));
        items.add(new Item("Lontong", 20000, R.drawable.snack));
        items.add(new Item("Bakwan", 20000, R.drawable.snack));
        items.add(new Item("Ikan", 20000, R.drawable.snack));
        items.add(new Item("Steak", 20000, R.drawable.snack));

        intent.putExtra("Items", items);
        startActivity(intent);
    }

    public void foodMenu(View view) {
        Intent intent = new Intent(this, SelectMenuActivity.class);
        ArrayList<Item> items = new ArrayList<Item>();

        items.add(new Item("Ayam", 20000, R.drawable.food));
        items.add(new Item("Mie", 20000, R.drawable.food));
        items.add(new Item("Telur", 20000, R.drawable.food));
        items.add(new Item("Kacang", 20000, R.drawable.food));

        intent.putExtra("Items", items);
        startActivity(intent);
    }

    public void myOrder(View view) {
        TextView test = (TextView) view;
        Intent intent = new Intent(this, MyOrderActivity.class);
        startActivity(intent);
    }

    public void topupMenu(View view) {
        Intent intent = new Intent(this, SelectMenuActivity.class);
        ArrayList<Item> items = new ArrayList<Item>();

        items.add(new Item("StayPay 50K", 20000, R.drawable.money));
        items.add(new Item("ReleasePay 50K", 20000, R.drawable.money));
        items.add(new Item("Pulsa XS 50k", 20000, R.drawable.money));
        items.add(new Item("Pulsa Empati 50k", 20000, R.drawable.money));

        intent.putExtra("Items", items);
        startActivity(intent);
    }
}