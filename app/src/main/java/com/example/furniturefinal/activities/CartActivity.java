package com.example.furniturefinal.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.furniturefinal.R;
import com.example.furniturefinal.adapters.DisplayCartAdapter;
import com.example.furniturefinal.database.AppDatabase;
import com.example.furniturefinal.database.CartProduct;
import com.example.furniturefinal.database.CartProductDAO;
import com.example.furniturefinal.pojoclass.CartModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class CartActivity extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Bundle savedInstanceState;

    private List<CartModel> list;
    private FirebaseAuth auth;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        final AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "CartProduct")
                .allowMainThreadQueries()
                .build();
        CartProductDAO cartProductDAO = database.getCartProductDAO();
        List<CartProduct> cartProducts = cartProductDAO.getCartProducts();

        recyclerView = (RecyclerView) findViewById(R.id.cart_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            CartModel cartModel = new CartModel(
//                    "123",
//                    "ProductName" + i + 1,
//                    "2000000",
//                     1
//            );
//
//            list.add(cartModel);
//        }
        adapter = new DisplayCartAdapter(this, cartProducts, database);
        recyclerView.setAdapter(adapter);
        auth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = auth.getCurrentUser();
        Button checkout = findViewById(R.id.checkout);
        checkout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (firebaseUser == null) {
                    Intent intent = new Intent(CartActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(CartActivity.this, ShipActivity.class);
                }
                //  displayAlert();
                return false;
            }

            /*public void displayAlert() {
                new AlertDialog.Builder(CartActivity.this).setMessage("Check Your Mail")
                        .setTitle("Email Invoice")
                        .setCancelable(true)
                        .setNeutralButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        finish();
                                    }
                                })
                        .show();
            }*/
        });
    }


}
