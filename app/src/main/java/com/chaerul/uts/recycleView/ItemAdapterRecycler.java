package com.chaerul.uts.recycleView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chaerul.uts.OrderActivity;
import com.chaerul.uts.R;
import com.chaerul.uts.model.Item;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ItemAdapterRecycler extends RecyclerView.Adapter<ItemAdapterRecycler.ViewHolder> {

    Context ctx;
    ArrayList<Item> items;

    public ItemAdapterRecycler(Context ctx, ArrayList<Item> items) {
        this.items = items;
        this.ctx = ctx;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.tvName.setText(item.name);

        Locale localeID = new Locale("in", "ID");
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(localeID);
        holder.tvPrice.setText(rupiah.format((double)item.price));

        holder.imgMenu.setImageResource(item.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvPrice;
        ImageView imgMenu;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imgMenu = itemView.findViewById(R.id.imgMenu);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), OrderActivity.class);
            intent.putExtra("Item", items.get(getAdapterPosition()));
            v.getContext().startActivity(intent);
        }
    }
}