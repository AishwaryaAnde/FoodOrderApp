package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodorderapp.Adapters.OrderAdapter;
import com.example.foodorderapp.Models.MainModel;
import com.example.foodorderapp.Models.OrderModel;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    RecyclerView recyclerViewOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        recyclerViewOrder = findViewById(R.id.recyclerViewOrder);

       /* ArrayList<OrderModel> list = new ArrayList<>();

        list.add(new OrderModel(R.drawable.burger,"Burger","5"," 12345"));
        list.add(new OrderModel(R.drawable.burger,"Burger","5"," 67890"));
        list.add(new OrderModel(R.drawable.burger,"Burger","5"," 12345"));
        list.add(new OrderModel(R.drawable.burger,"Burger","5"," 67890"));
        list.add(new OrderModel(R.drawable.burger,"Burger","5"," 143143"));
*/

       DBHelper helper = new DBHelper(this);
        ArrayList<OrderModel> list = helper.getOrders();

        OrderAdapter orderAdapter = new OrderAdapter(list,this);
        recyclerViewOrder.setAdapter(orderAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewOrder.setLayoutManager(layoutManager);

    }
}
