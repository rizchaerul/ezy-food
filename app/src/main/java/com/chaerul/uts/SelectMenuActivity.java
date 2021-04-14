package com.chaerul.uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.chaerul.uts.model.Item;
import com.chaerul.uts.recycleView.ItemAdapterRecycler;

import java.util.ArrayList;

public class SelectMenuActivity extends AppCompatActivity {

    ArrayList<Item> items;
    RecyclerView rvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu);

        items = (ArrayList<Item>) getIntent().getSerializableExtra("Items");

        rvItems = findViewById(R.id.rvItems);
        //GridLayoutManager manager = new GridLayoutManager(this,3);
        rvItems.setLayoutManager(new GridLayoutManager(this, 2));
        rvItems.setAdapter(new ItemAdapterRecycler(this, items));
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
}