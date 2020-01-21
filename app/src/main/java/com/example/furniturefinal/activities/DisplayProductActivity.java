package com.example.furniturefinal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import com.example.furniturefinal.viewHolder.CartModel;
import com.example.furniturefinal.R;

import java.util.ArrayList;
import java.util.List;

public class DisplayProductActivity extends AppCompatActivity {
    private RatingBar ratingBar;
    private Button addToCart;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Bundle savedInstanceState;
//    private Rad

    private List<CartModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);

       addToCart=findViewById(R.id.addToCart);
       addToCart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(DisplayProductActivity.this,CartActivity.class);
               startActivity(intent);
           }
       });

    }
}
