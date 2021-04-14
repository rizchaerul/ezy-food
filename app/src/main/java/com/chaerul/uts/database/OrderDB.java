package com.chaerul.uts.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chaerul.uts.model.Item;
import com.chaerul.uts.model.Order;

import java.util.ArrayList;

public class OrderDB {

    DBHelper dbHelper;

    public OrderDB(Context ctx) {
        dbHelper = new DBHelper(ctx);
    }

    public long insertOrder(Order product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_PRODUCT_NAME, product.item.name);
        cv.put(DBHelper.FIELD_PRODUCT_PRICE, product.item.price);
        cv.put(DBHelper.FIELD_PRODUCT_IMAGE, product.item.image);
        cv.put(DBHelper.FIELD_PRODUCT_QUANTITY, product.quantity);

        return db.insert(DBHelper.TABLE_NAME_PRODUCTS, null, cv);
    }

    public void delete() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.delete(db);
    }

    public void removeAllItem() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(DBHelper.TABLE_NAME_PRODUCTS, null, null);
    }

    public ArrayList<Order> getOrders() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //String selection = "id=?";
        //String[] selectionArgs = {"" + id};
        Cursor cursor = db.query(DBHelper.TABLE_NAME_PRODUCTS, null, null, null, null, null, null);

        ArrayList<Order> orders = new ArrayList<Order>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Order order = new Order(new Item());

                order.id = cursor.getLong(cursor.getColumnIndex(DBHelper.FIELD_PRODUCT_ID));
                order.item.name = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_PRODUCT_NAME));
                order.quantity = cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_PRODUCT_QUANTITY));
                order.item.price = cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_PRODUCT_PRICE));
                order.item.image = cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_PRODUCT_IMAGE));

                orders.add(order);
                cursor.moveToNext();
            }
        }

        return orders;
    }



}