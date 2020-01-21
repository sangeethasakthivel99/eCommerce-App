package com.example.furniturefinal.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniturefinal.viewHolder.CartModel;
import com.example.furniturefinal.R;
import com.example.furniturefinal.adapters.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Bundle savedInstanceState;
    int minteger=0;
    private List<CartModel>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        for(int i=0;i<10;i++){
            CartModel cartModel=new CartModel(
                    123,
                    "ProductName"+ i+1,
                    "2000000"
            );
            list.add(cartModel);
        }
        adapter=new RecyclerAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }
    public void increaseInteger(View view) {
        minteger = minteger + 1;

        display(minteger);

    }public void decreaseInteger(View view) {
        minteger = minteger - 1;
        display(minteger);
    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.integer_number);
        displayInteger.setText("" + number);
    }
}
