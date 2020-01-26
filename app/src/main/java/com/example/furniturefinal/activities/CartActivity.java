package com.example.furniturefinal.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.furniturefinal.R;
import com.example.furniturefinal.adapters.DisplayCartAdapter;
import com.example.furniturefinal.adapters.DisplayCartAdapterBackend;
import com.example.furniturefinal.database.AppDatabase;
import com.example.furniturefinal.database.CartProduct;
import com.example.furniturefinal.database.CartProductDAO;
import com.example.furniturefinal.pojoclass.CartModel;
import com.example.furniturefinal.pojoclass.DummyCartDto;
import com.example.furniturefinal.pojoclass.DummyCartListDto;
import com.example.furniturefinal.pojoclass.ResponseDto;
import com.example.furniturefinal.retrofit.Endpoint;
import com.example.furniturefinal.retrofit.RetrofitClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        final Endpoint service = RetrofitClass.getRetrofit().create(Endpoint.class);
//        Endpoint service = RetrofitClass.getRetrofit().create(Endpoint.class);
        auth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = auth.getCurrentUser();

        final AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "CartProduct")
                .allowMainThreadQueries()
                .build();
        CartProductDAO cartProductDAO = database.getCartProductDAO();
        List<CartProduct> cartProducts = cartProductDAO.getCartProducts();
        if(firebaseUser != null){

            //API call to add to cart
            List<DummyCartListDto> list = new ArrayList<>();
            DummyCartDto dummyCartDto = new DummyCartDto();
            for( CartProduct cartProduct: cartProducts) {
                DummyCartListDto dummyCartListDto = new DummyCartListDto();
                dummyCartListDto.setProductId(cartProduct.getProductId());
                dummyCartListDto.setMerchantId(cartProduct.getMerchantId());
                dummyCartListDto.setQuantityBrought(cartProduct.getQuantityBrought());

                cartProductDAO.delete(cartProduct);

                list.add(dummyCartListDto);
            }
            cartProducts.clear();
//            adapter.notifyDataSetChanged();
            dummyCartDto.setCartDtoList(list);
            Call<ResponseDto<String>> responseDtoCall = service.addToCartBackend(dummyCartDto);
            responseDtoCall.enqueue(new Callback<ResponseDto<String>>() {
                @Override
                public void onResponse(Call<ResponseDto<String>> call, Response<ResponseDto<String>> response) {
                    Toast.makeText(CartActivity.this, "Successfully added to backend!!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseDto<String>> call, Throwable t) {
                    Toast.makeText(CartActivity.this, "Failed to send to backend!!", Toast.LENGTH_SHORT).show();
                }
            });

        }
        recyclerView = (RecyclerView) findViewById(R.id.cart_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(firebaseUser == null) {
            adapter = new DisplayCartAdapter(this, cartProducts, database);
            recyclerView.setAdapter(adapter);
        }
        else
        {
//            {
//                FirebaseAuth.getInstance().getCurrentUser().getIdToken(true).addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
//                    @Override
//                    public void onSuccess(GetTokenResult getTokenResult) {
//                        String token = getTokenResult.getToken();
//                        System.out.println(token);
//                    }
//                });
//            }
            Call<ResponseDto<List<CartProduct>>> responseDtoCall = service.getCartFromBackend();
            responseDtoCall.enqueue(new Callback<ResponseDto<List<CartProduct>>>() {
                @Override
                public void onResponse(Call<ResponseDto<List<CartProduct>>> call, Response<ResponseDto<List<CartProduct>>> response) {
                    adapter = new DisplayCartAdapterBackend(CartActivity.this, response.body().getData());
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<ResponseDto<List<CartProduct>>> call, Throwable t) {
                    Toast.makeText(CartActivity.this, "Failed getting from backend!!", Toast.LENGTH_SHORT).show();
                }
            });
        }


        Button checkout = findViewById(R.id.checkout);
        checkout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (firebaseUser == null) {
                    Intent intent = new Intent(CartActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(CartActivity.this, ShipActivity.class);
                    startActivity(intent);
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
