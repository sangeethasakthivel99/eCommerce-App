package com.example.furniturefinal.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniturefinal.R;
import com.example.furniturefinal.adapters.CategoryAdapter;
import com.example.furniturefinal.adapters.PopularProductsAdapter;
import com.example.furniturefinal.jsonobjects.Categories;
import com.example.furniturefinal.jsonobjects.PopularProducts;
import com.example.furniturefinal.retrofit.Endpoint;
import com.example.furniturefinal.retrofit.RetrofitClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.recyclerview.widget.RecyclerView.HORIZONTAL;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private PopularProductsAdapter popularProductsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Endpoint service = RetrofitClass.getRetrofit().create(Endpoint.class);

//        Call<List<Categories>> categories = service.getAllCategories();
//
//        categories.enqueue(new Callback<List<Categories>>() {
//            @Override
//            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
////                progressDoalog.dismiss();
//                generateCategoryList(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<Categories>> call, Throwable t) {
////                progressDoalog.dismiss();
//                Toast.makeText(HomeActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//            }
//        });

//        Call<List<PopularProducts>> popularProducts = service.getAllPopularProducts();
//        popularProducts.enqueue(new Callback<List<PopularProducts>>() {
//            @Override
//            public void onResponse(Call<List<PopularProducts>> call, Response<List<PopularProducts>> response) {
////                progressDoalog.dismiss();
//                generatePopularProductsList(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<PopularProducts>> call, Throwable t) {
////                progressDoalog.dismiss();
//                Toast.makeText(HomeActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//            }
//        });

        }

    private void generateCategoryList(List<Categories> categoriesList) {
        recyclerView = findViewById(R.id.category_recycler_view);
        categoryAdapter = new CategoryAdapter(this, categoriesList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(categoryAdapter);
    }

    private void generatePopularProductsList(List<PopularProducts> popularProductsList) {
        recyclerView = findViewById(R.id.popular_products_recycler_view);
        popularProductsAdapter = new PopularProductsAdapter(this, popularProductsList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(HomeActivity.this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(popularProductsAdapter);
    }

}
