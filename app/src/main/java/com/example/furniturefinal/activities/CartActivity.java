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

import com.example.furniturefinal.interfaces.CartActivityInterface;
import com.example.furniturefinal.viewHolder.CartModel;
import com.example.furniturefinal.R;
import com.example.furniturefinal.adapters.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartActivityInterface {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Bundle savedInstanceState;
    int minteger=1;
    private List<CartModel>list;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
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
            adapter=new RecyclerAdapter(this,list);
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
                        .setTitle("Invoice Email")
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
    }
    @Override
    public void increaseInteger(View view) {
        minteger = minteger + 1;

        display(minteger);

    }
    @Override public void decreaseInteger(View view) {
        if(minteger-1<1)
            display(1);
        else {
            minteger = minteger - 1;
            display(minteger);
        }
    }
@Override
    public void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.integer_number);
        displayInteger.setText("" + number);
    }
}
