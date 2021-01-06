package com.example.foodorderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.foodorderapp.Adapters.MainAdapter;
import com.example.foodorderapp.Models.MainModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.burger,"Burger","5","Yummy simple Burger"));
        list.add(new MainModel(R.drawable.chickenpizza,"chicken Pizza","10","Yummy chicken Pizza"));
        list.add(new MainModel(R.drawable.doublelayerburger,"doublelayerburger","15","Yummy double layer burger"));
        list.add(new MainModel(R.drawable.frenchfries,"frenchfries","25","Yummy french fries "));
        list.add(new MainModel(R.drawable.frozenpizza,"Burfrozenpizzager","35","Yummy frozen pizza "));
        list.add(new MainModel(R.drawable.isolatepizza,"isolatepizza","45","Yummy isolate pizza"));
        list.add(new MainModel(R.drawable.pizza,"pizza","55","Yummy pizza"));


        mainAdapter = new MainAdapter(list,this);
        recyclerView.setAdapter(mainAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.orders :
                startActivity(new Intent(MainActivity.this,OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
