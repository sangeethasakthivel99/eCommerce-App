package com.example.furniturefinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniturefinal.R;
import com.example.furniturefinal.adapters.CategoryAdapter;
import com.example.furniturefinal.adapters.PopularProductsAdapter;
import com.example.furniturefinal.pojoclass.Categories;
import com.example.furniturefinal.pojoclass.PopularProducts;
import com.example.furniturefinal.retrofit.Endpoint;
import com.example.furniturefinal.retrofit.RetrofitClass;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.HORIZONTAL;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private PopularProductsAdapter popularProductsAdapter;
    private FirebaseAuth auth;

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

        auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();


        TextView tv = findViewById(R.id.login);

        if(firebaseUser == null)
            tv.setText("Log in");

        String logTextBoxStatus = tv.getText().toString();

        if(logTextBoxStatus == "Log in")
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        else{

        }

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
