package com.example.furniturefinal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.furniturefinal.R;
import com.example.furniturefinal.adapters.CategoryProductsAdapter;
import com.example.furniturefinal.pojoclass.CategoryProducts;
import com.example.furniturefinal.retrofit.Endpoint;
import com.example.furniturefinal.retrofit.RetrofitClass;

import java.util.ArrayList;
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

        Call<List<CategoryProducts>> products = service.getCategoryProduct();

        products.enqueue(new Callback<List<CategoryProducts>>() {
            @Override
            public void onResponse(Call<List<CategoryProducts>> call, Response<List<CategoryProducts>> response) {
                generateCategoryProductsList(response.body());
            }

            @Override
            public void onFailure(Call<List<CategoryProducts>> call, Throwable t) {
                Toast.makeText(DisplayCategoryProductsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

        List<CategoryProducts> categoriesChecklist = new ArrayList<>();
        for(int i = 0; i < 10; i++)
        {
            CategoryProducts c = new CategoryProducts();
            c.setImage("https://ii1.pepperfry.com/media/catalog/product/m/i/494x544/Minimalistic-Sheesham-Wood-Coffee-Table-16013-1341407138QXRrdA.jpg");
            c.setMerchantId("1ab");
            c.setMerchantName("ads");
            c.setPrice(1000);
            c.setRatings(2);
            c.setProduct_name("Wood");
            c.setProduct_id("1a");

            categoriesChecklist.add(c);
        }
        generateCategoryProductsList(categoriesChecklist);

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
        intent.putExtra("product_id", products.getProduct_id());
        intent.putExtra("merchant_id",products.getMerchantId());
        startActivity(intent);
    }
}
