package com.example.furniturefinal.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.furniturefinal.R;
import com.example.furniturefinal.database.AppDatabase;
import com.example.furniturefinal.database.CartProductDAO;
import com.example.furniturefinal.pojoclass.Order;
import com.example.furniturefinal.pojoclass.OrderDto;
import com.example.furniturefinal.pojoclass.ResponseDto;
import com.example.furniturefinal.retrofit.Endpoint;
import com.example.furniturefinal.retrofit.RetrofitClass;

import retrofit2.Call;

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
                orderDto.setAddress(address);
                Call<ResponseDto<Order>> orderList = service.checkout(orderDto);
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
        });
    }
}
