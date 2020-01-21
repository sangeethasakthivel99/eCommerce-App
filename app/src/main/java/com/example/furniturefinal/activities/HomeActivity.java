package com.example.furniturefinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.recyclerview.widget.RecyclerView.HORIZONTAL;

public class HomeActivity extends AppCompatActivity implements PopularProductsAdapter.PopularProductsCommunication , CategoryAdapter.CategoryCommunication {

    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private PopularProductsAdapter popularProductsAdapter;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Endpoint service = RetrofitClass.getRetrofit().create(Endpoint.class);

        Call<List<Categories>> categories = service.getCategories();

        categories.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                generateCategoryList(response.body());
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

        Call<List<PopularProducts>> popularProducts = service.getPopularProducts();
        popularProducts.enqueue(new Callback<List<PopularProducts>>() {
            @Override
            public void onResponse(Call<List<PopularProducts>> call, Response<List<PopularProducts>> response) {
                generatePopularProductsList(response.body());
            }

            @Override
            public void onFailure(Call<List<PopularProducts>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

        auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();


        TextView loginStatus = findViewById(R.id.login);

        if(firebaseUser == null)
            loginStatus.setText("Log in");

        String logTextBoxStatus = loginStatus.getText().toString();

        if(logTextBoxStatus == "Log in")
        loginStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        else{
            loginStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    auth.signOut();
                    Intent intent=new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
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
        popularProductsAdapter = new PopularProductsAdapter(HomeActivity.this, popularProductsList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(HomeActivity.this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(popularProductsAdapter);
    }
    @Override
    public void onClick(PopularProducts popularProducts) {
        Intent intent=new Intent( HomeActivity.this, DisplayProductActivity.class);
        intent.putExtra("product_id", popularProducts.getProduct_id());
        intent.putExtra("merchant_id",popularProducts.getMerchantId());
        startActivity(intent);
    }

    @Override
    public void onClick(Categories categories) {
        Intent intent=new Intent( HomeActivity.this, DisplayCategoryProductsActivity.class);
        intent.putExtra("category_id", categories.getCategory_id());
        startActivity(intent);
    }
}
