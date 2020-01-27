package com.example.furniturefinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.furniturefinal.R;
import com.example.furniturefinal.adapters.CategoryAdapter;
import com.example.furniturefinal.adapters.PopularProductsAdapter;
import com.example.furniturefinal.database.AppDatabase;
import com.example.furniturefinal.database.CartProduct;
import com.example.furniturefinal.database.CartProductDAO;
import com.example.furniturefinal.pojoclass.Categories;
import com.example.furniturefinal.pojoclass.PopularProducts;
import com.example.furniturefinal.pojoclass.ResponseDto;
import com.example.furniturefinal.retrofit.Endpoint;
import com.example.furniturefinal.retrofit.RetrofitClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.recyclerview.widget.RecyclerView.HORIZONTAL;

public class HomeActivity extends AppCompatActivity implements PopularProductsAdapter.PopularProductsCommunication, CategoryAdapter.CategoryCommunication {

    private RecyclerView recyclerViewCategory;
    private RecyclerView recyclerViewPopularProducts;

    private CategoryAdapter categoryAdapter;
    private PopularProductsAdapter popularProductsAdapter;
    private FirebaseAuth auth;

    private TextView loginStatus;
    private TextView order;
    private SearchView searchView;
    private ImageButton cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Endpoint service = RetrofitClass.getRetrofit().create(Endpoint.class);
        order=findViewById(R.id.order_history);
        cart=findViewById(R.id.cart);

        final AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "CartProduct")
                .allowMainThreadQueries()
                .build();
        Call<ResponseDto<List<Categories>>> categoriesCall = service.getCategoriesGeneric();
        categoriesCall.enqueue(new Callback<ResponseDto<List<Categories>>>() {
            @Override
            public void onResponse(Call<ResponseDto<List<Categories>>> call, Response<ResponseDto<List<Categories>>> response) {
                generateCategoryList(response.body().getData());
            }

            @Override
            public void onFailure(Call<ResponseDto<List<Categories>>> call, Throwable t) {
               // Toast.makeText(HomeActivity.this, "Failed1", Toast.LENGTH_SHORT).show();
            }
        });

        Call<ResponseDto<List<PopularProducts>>> popularproductsCall = service.getPopularProductsGeneric();
        popularproductsCall.enqueue(new Callback<ResponseDto<List<PopularProducts>>>() {
            @Override
            public void onResponse(Call<ResponseDto<List<PopularProducts>>> call, Response<ResponseDto<List<PopularProducts>>> response) {
                generatePopularProductsList(response.body().getData());
            }

            @Override
            public void onFailure(Call<ResponseDto<List<PopularProducts>>> call, Throwable t) {
               // Toast.makeText(HomeActivity.this, "Failed2", Toast.LENGTH_LONG).show();
            }
        });

        auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();


        TextView loginStatus = findViewById(R.id.login);
        TextView user=findViewById(R.id.user);

        if (firebaseUser == null)
            loginStatus.setText("Log in");


        if (firebaseUser == null)
            loginStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        else {
            loginStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    auth.signOut();
                    CartProductDAO cartProductDAO = database.getCartProductDAO();
                    for(CartProduct cartProduct : cartProductDAO.getCartProducts())
                        cartProductDAO.delete(cartProduct);

                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            });
        }
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(HomeActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,OrderListActivity.class);
                startActivity(intent);

            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });




        searchView = findViewById(R.id.searchBar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(HomeActivity.this, SearchResultsActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void generateCategoryList(List<Categories> categoriesList) {
        recyclerViewCategory = findViewById(R.id.category_recycler_view);
        categoryAdapter = new CategoryAdapter(this, categoriesList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, HORIZONTAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setAdapter(categoryAdapter);
    }

    private void generatePopularProductsList(List<PopularProducts> popularProductsList) {
        recyclerViewPopularProducts = findViewById(R.id.popular_products_recycler_view);
        popularProductsAdapter = new PopularProductsAdapter(HomeActivity.this, popularProductsList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(HomeActivity.this, 2);
        recyclerViewPopularProducts.setLayoutManager(layoutManager);
        recyclerViewPopularProducts.setAdapter(popularProductsAdapter);
    }

    @Override
    public void onClick(PopularProducts popularProducts) {
        Intent intent = new Intent(HomeActivity.this, DisplayProductActivity.class);
        intent.putExtra("productId", popularProducts.getProductId());
//        intent.putExtra("merchantId", popularProducts.getMerchantId());
        startActivity(intent);
    }

    @Override
    public void onClick(Categories categories) {
        Intent intent = new Intent(HomeActivity.this, DisplayCategoryProductsActivity.class);
        intent.putExtra("categoryId", categories.getCategoryId());
        startActivity(intent);
    }
}
