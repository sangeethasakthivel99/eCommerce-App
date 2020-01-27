package com.example.furniturefinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniturefinal.R;
import com.example.furniturefinal.adapters.DisplayHistoryDetailAdapter;
import com.example.furniturefinal.pojoclass.HistoryDetailModel;
import com.example.furniturefinal.pojoclass.OrderPageDto;
import com.example.furniturefinal.pojoclass.ResponseDto;
import com.example.furniturefinal.retrofit.Endpoint;
import com.example.furniturefinal.retrofit.RetrofitClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewHistoryDetails extends AppCompatActivity {
    private ImageView image;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Bundle savedInstanceState;
    private TextView productName;
    private TextView productPrice, tv_price, productQuantity, tv_quantity;
    private Button back;
    private List<HistoryDetailModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_recycler);
        recyclerView = (RecyclerView) findViewById(R.id.order_recyclerview);
        recyclerView.setHasFixedSize(true);
        Endpoint service = RetrofitClass.getRetrofit().create(Endpoint.class);
        Intent intent = getIntent();
        String orderId = intent.getStringExtra("orderId");
        Call<ResponseDto<List<OrderPageDto>>> responseDtoCall = service.getOrderedItems(orderId);
        responseDtoCall.enqueue(new Callback<ResponseDto<List<OrderPageDto>>>() {
            @Override
            public void onResponse(Call<ResponseDto<List<OrderPageDto>>> call, Response<ResponseDto<List<OrderPageDto>>> response) {
                adapter = new DisplayHistoryDetailAdapter(ViewHistoryDetails.this, response.body().getData());
                recyclerView.setLayoutManager(new LinearLayoutManager(ViewHistoryDetails.this));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseDto<List<OrderPageDto>>> call, Throwable t) {

            }
        });
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            HistoryDetailModel historyDetailModel = new HistoryDetailModel(
//                    "prod name",
//                    "img",
//                    1,
//                    1234567
//            );
//            list.add(historyDetailModel);

        }
    }
