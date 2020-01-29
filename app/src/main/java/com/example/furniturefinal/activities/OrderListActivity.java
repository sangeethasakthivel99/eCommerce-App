package com.example.furniturefinal.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniturefinal.R;
import com.example.furniturefinal.adapters.DisplayHistoryAdapter;
import com.example.furniturefinal.pojoclass.HistoryElements;
import com.example.furniturefinal.pojoclass.HistoryModel;
import com.example.furniturefinal.pojoclass.ResponseDto;
import com.example.furniturefinal.retrofit.Endpoint;
import com.example.furniturefinal.retrofit.RetrofitClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Bundle savedInstanceState;
    private List<HistoryModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        recyclerView = (RecyclerView) findViewById(R.id.order_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Endpoint service = RetrofitClass.getRetrofit().create(Endpoint.class);
        Call<ResponseDto<List<HistoryElements>>>  orderHistory = service.getOrderHistory();
        orderHistory.enqueue(new Callback<ResponseDto<List<HistoryElements>>>() {
            @Override
            public void onResponse(Call<ResponseDto<List<HistoryElements>>> call, Response<ResponseDto<List<HistoryElements>>> response) {
                adapter = new DisplayHistoryAdapter(OrderListActivity.this, response.body().getData());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseDto<List<HistoryElements>>> call, Throwable t) {
                Toast.makeText(OrderListActivity.this, "Can't load order history...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
//        list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            HistoryModel historyModel = new HistoryModel(
//                    1234545,
//                    "123456",
//                    "12345678"
//            );
//            list.add(historyModel);
            //adapter = new DisplayHistoryAdapter(this, list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
