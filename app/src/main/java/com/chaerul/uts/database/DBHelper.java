package com.chaerul.uts.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "OrderDB";
    public static final int DB_VERSION = 1;

    //Field
    public static final String TABLE_NAME_PRODUCTS = "MyOrder";
    public static final String FIELD_PRODUCT_ID = "id";
    public static final String FIELD_PRODUCT_NAME = "name";
    public static final String FIELD_PRODUCT_QUANTITY = "quantity";
    public static final String FIELD_PRODUCT_PRICE = "price";
    public static final String FIELD_PRODUCT_IMAGE = "image";

    //DDL
    public static final String createProducts =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_PRODUCTS + " (" +
            FIELD_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FIELD_PRODUCT_NAME + " TEXT," +
            FIELD_PRODUCT_IMAGE + " INTEGER," +
            FIELD_PRODUCT_PRICE + " INTEGER," +
            FIELD_PRODUCT_QUANTITY + " INTEGER)";

    public static final String dropProducts =
            "DROP TABLE IF EXISTS " + TABLE_NAME_PRODUCTS;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createProducts);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropProducts);
        onCreate(db);
    }

    public void delete(SQLiteDatabase db) {
        db.execSQL(dropProducts);
        onCreate(db);
    }

}