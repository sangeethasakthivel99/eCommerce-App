package com.example.furniturefinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.furniturefinal.R;
import com.example.furniturefinal.pojoclass.HistoryModel;
import com.example.furniturefinal.retrofit.Endpoint;
import com.example.furniturefinal.retrofit.RetrofitClass;
import java.util.List;
public class ViewHistoryDetails extends AppCompatActivity {
    private ImageView image;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Bundle savedInstanceState;
    private TextView productName;
    private TextView productPrice,tv_price,productQuantity,tv_quantity;
    private Button back;
    private List<HistoryModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history_details);
        Endpoint service = RetrofitClass.getRetrofit().create(Endpoint.class);
        Intent intent = getIntent();
        final String productId = intent.getStringExtra("productId");
        productName=findViewById(R.id.product_name);
        productPrice=findViewById(R.id.product_price);
        productQuantity=findViewById(R.id.product_quantity);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewHistoryDetails.this,OrderListActivity.class);
                startActivity(intent);
            }
        });
    }
}
