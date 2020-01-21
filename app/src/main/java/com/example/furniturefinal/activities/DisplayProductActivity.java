package com.example.furniturefinal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RatingBar;

import com.example.furniturefinal.viewHolder.CartModel;
import com.example.furniturefinal.R;

import java.util.ArrayList;
import java.util.List;

public class DisplayProductActivity extends AppCompatActivity {
    private RatingBar ratingBar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Bundle savedInstanceState;
//    private Rad

    private List<CartModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);
        ratingBar=findViewById(R.id.rating);
        ratingBar.setRating((float) 3.5);
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();

    }
}
