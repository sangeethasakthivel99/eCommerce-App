package com.example.furniturefinal.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.furniturefinal.R;
import com.example.furniturefinal.database.AppDatabase;
import com.example.furniturefinal.database.CartProduct;
import com.example.furniturefinal.database.CartProductDAO;
import com.example.furniturefinal.pojoclass.Order;
import com.example.furniturefinal.pojoclass.OrderDto;
import com.example.furniturefinal.pojoclass.ResponseDto;
import com.example.furniturefinal.retrofit.Endpoint;
import com.example.furniturefinal.retrofit.RetrofitClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShipActivity extends AppCompatActivity {
private Button confirm ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship);
        EditText addressRow = findViewById(R.id.ship_Address);
        final String address = String.valueOf(addressRow.getText());
        confirm=findViewById(R.id.ship_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Endpoint service = RetrofitClass.getRetrofit().create(Endpoint.class);
                OrderDto orderDto = new OrderDto();
                final AppDatabase database = Room.databaseBuilder(ShipActivity.this, AppDatabase.class, "CartProduct")
                        .allowMainThreadQueries()
                        .build();
                CartProductDAO cartProductDAO = database.getCartProductDAO();
                orderDto.setOrderedItemDto(cartProductDAO.getCartProducts());
                for(CartProduct cartProduct: cartProductDAO.getCartProducts()){
                    cartProductDAO.delete(cartProduct);
                }
                orderDto.setAddress(address);
                final Call<ResponseDto<Order>> orderList = service.checkout(orderDto);
                orderList.enqueue(new Callback<ResponseDto<Order>>() {
                    @Override
                    public void onResponse(Call<ResponseDto<Order>> call, Response<ResponseDto<Order>> response) {
                        if (!response.body().getSuccess()) {
                            new AlertDialog.Builder(ShipActivity.this).setMessage("Some items are out of stock, please check again")
                                    .setTitle("Quantity error")
                                    .setCancelable(true)
                                    .setNeutralButton(android.R.string.ok,
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {
                                                    finish();
                                                }
                                            })
                                    .show();
                        } else {
                            new AlertDialog.Builder(ShipActivity.this).setMessage("Check Your Mail")
                                    .setTitle("Email Invoice")
                                    .setCancelable(true)
                                    .setNeutralButton(android.R.string.ok,
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {
                                                    finish();
                                                }
                                            })
                                    .show();
                        }
                        Intent intent = new Intent(ShipActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ResponseDto<Order>> call, Throwable t) {
                        Toast.makeText(ShipActivity.this, "Couldn't place order!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}