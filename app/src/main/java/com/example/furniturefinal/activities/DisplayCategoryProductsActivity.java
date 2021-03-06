package com.example.furniturefinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniturefinal.R;
import com.example.furniturefinal.adapters.CategoryProductsAdapter;
import com.example.furniturefinal.pojoclass.CategoryProducts;
import com.example.furniturefinal.pojoclass.ResponseDto;
import com.example.furniturefinal.retrofit.Endpoint;
import com.example.furniturefinal.retrofit.RetrofitClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayCategoryProductsActivity extends AppCompatActivity implements CategoryProductsAdapter.CategoryProductsCommunication {

    private RecyclerView recyclerView;
    private CategoryProductsAdapter categoryProductsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_category);
        Endpoint service = RetrofitClass.getRetrofit().create(Endpoint.class);
        Intent intent = getIntent();
        String categoryId = intent.getStringExtra("categoryId");
        Call<ResponseDto<List<CategoryProducts>>> products = service.getCategoryProduct(categoryId);

        products.enqueue(new Callback<ResponseDto<List<CategoryProducts>>>() {
            @Override
            public void onResponse(Call<ResponseDto<List<CategoryProducts>>> call, Response<ResponseDto<List<CategoryProducts>>> response) {
                generateCategoryProductsList(response.body().getData());
            }

            @Override
            public void onFailure(Call<ResponseDto<List<CategoryProducts>>> call, Throwable t) {
                Toast.makeText(DisplayCategoryProductsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void generateCategoryProductsList(List<CategoryProducts> productsList) {
        recyclerView = findViewById(R.id.category_products_recycler_view);
        categoryProductsAdapter = new CategoryProductsAdapter(DisplayCategoryProductsActivity.this,productsList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(DisplayCategoryProductsActivity.this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(categoryProductsAdapter);
    }

    @Override
    public void onClick(CategoryProducts products) {
        Intent intent=new Intent( DisplayCategoryProductsActivity.this, DisplayProductActivity.class);
        intent.putExtra("productId", products.getProductId());
        startActivity(intent);
    }
}
