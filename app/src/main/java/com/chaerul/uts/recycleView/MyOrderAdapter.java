package com.chaerul.uts.recycleView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.chaerul.uts.MyOrderActivity;
import com.chaerul.uts.R;
import com.chaerul.uts.database.OrderDB;
import com.chaerul.uts.model.Item;
import com.chaerul.uts.model.Order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    Context ctx;
    ArrayList<Item> items;
    ArrayList<Order> orders;

    public MyOrderAdapter(Context ctx, ArrayList<Item> items, ArrayList<Order> orders) {
        this.items = items;
        this.ctx = ctx;
        this.orders = orders;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.order_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Item item = items.get(position);
        holder.tvName.setText(item.name);

        Locale localeID = new Locale("in", "ID");
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(localeID);
        String harga = rupiah.format((double) item.price);

        holder.tvPrice.setText(harga + " x " + orders.get(position).quantity);
        holder.imgMenu.setImageResource(item.image);

        Button btnDelete = holder.btnDelete.findViewById(R.id.btdelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderDB orderDB = new OrderDB(view.getContext());
                ArrayList<Order> newOrders = new ArrayList<Order>();

                newOrders = orderDB.getOrders();
                newOrders.remove(position);
                items.remove(position);
                orders.remove(position);


                orderDB.removeAllItem();

                for(Order order: newOrders){
                    orderDB.insertOrder(order);
                }

                notifyItemRemoved(position);
                notifyItemRangeChanged(position,orders.size());

                MyOrderActivity activity = (MyOrderActivity) view.getContext();
                activity.showPrice();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvPrice;
        ImageView imgMenu;
        Button btnDelete;


        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imgMenu = itemView.findViewById(R.id.imgMenu);
            btnDelete = itemView.findViewById(R.id.btdelete);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }
}