package com.example.foodorderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodorderapp.Models.OrderModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    final static String DBNAME = "foodOrder.db";
    final static int DBVERSION = 1;


    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table orders " +
                "(id integer primary key autoincrement,name text,phone text,price int,image int,quantity int ,description text,foodname text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP table if exists orders");
        onCreate(db);
    }

    public boolean insertOrder(String name, String phone, int price, int image, int quantity, String decs, String foodname) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

       /* id=0
       name = 1
       phone = 2
       price = 3
       image = 4
       quantity = 5
       desc = 6
       foodname = 7
        */

        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("quantity", quantity);
        values.put("description", decs);
        values.put("foodname", foodname);

        long id = db.insert("orders", null, values);
        if (id <= 0) {
            return false;
        } else
            return true;
    }

    public ArrayList<OrderModel> getOrders() {
        ArrayList<OrderModel> orders = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select id,foodname,image,price from orders", null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                OrderModel model = new OrderModel();
                model.setOrderNumber(cursor.getInt(0) + "");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3) + "");
                orders.add(model);
            }
        }
        cursor.close();
        db.close();
        return orders;
    }

    public Cursor getOrderById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from orders where id = " + id, null);

        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    public boolean updateOrder(String name, String phone, int price, int image, int quantity, String decs, String foodname, int id)
    {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

       /* id=0
       name = 1
       phone = 2
       price = 3
       image = 4
       quantity = 5
       desc = 6
       foodname = 7
        */

        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("quantity", quantity);
        values.put("description", decs);
        values.put("foodname", foodname);

        long row = db.update("orders", values,"id = " +id,null);
        if (row <= 0) {
            return false;
        } else
            return true;
    }

    public int deleteOrder(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("orders","id = "+id,null);
    }

}
