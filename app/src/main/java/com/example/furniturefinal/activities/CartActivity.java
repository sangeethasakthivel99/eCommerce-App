package com.example.furniturefinal.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniturefinal.pojoclass.CartModel;
import com.example.furniturefinal.R;
import com.example.furniturefinal.adapters.DisplayCartAdapter;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Bundle savedInstanceState;
    int count=1;
    private Button increment;
    private Button decrease;
    private TextView textCount;
    private List<CartModel>list;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        increment=(Button)findViewById(R.id.increase);
        decrease=(Button)findViewById(R.id.decrease);
        textCount=(TextView)findViewById(R.id.textCount);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        for(int i=0;i<10;i++) {
            CartModel cartModel = new CartModel(
                    123,
                    "ProductName" + i + 1,
                    "2000000"
            );

            list.add(cartModel);
            adapter=new DisplayCartAdapter(this,list);
            recyclerView.setAdapter(adapter);
        }
            Button checkout=findViewById(R.id.checkout);
            checkout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    displayAlert();
                    return false;
                }

            public  void displayAlert()
            {
                new AlertDialog.Builder(CartActivity.this).setMessage("Check Your Mail")
                        .setTitle("Email Invoice")
                        .setCancelable(true)
                        .setNeutralButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton){
                                        finish();
                                    }
                                })
                        .show();
            }
        });
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                textCount.setText(String.valueOf(count));

            }
        });

      decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                textCount.setText(String.valueOf(count));

            }
        });
    }

}
