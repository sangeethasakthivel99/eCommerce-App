package com.example.furniturefinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniturefinal.R;
import com.example.furniturefinal.adapters.SearchResultsAdapter;
import com.example.furniturefinal.pojoclass.CategoryProducts;
import com.example.furniturefinal.retrofit.Endpoint;
import com.example.furniturefinal.retrofit.RetrofitClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultsActivity extends AppCompatActivity implements SearchResultsAdapter.SearchResultsCommunication{
    private RecyclerView recyclerView;
    private SearchResultsAdapter searchResultsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Endpoint service = RetrofitClass.getRetrofit().create(Endpoint.class);
        Intent intent = getIntent();
        String query = intent.getStringExtra("query");
        Call<List<CategoryProducts>> products = service.getSearchedProducts(query);

        products.enqueue(new Callback<List<CategoryProducts>>() {
            @Override
            public void onResponse(Call<List<CategoryProducts>> call, Response<List<CategoryProducts>> response) {
                generateSearchProductsList(response.body());
            }

            @Override
            public void onFailure(Call<List<CategoryProducts>> call, Throwable t) {
                Toast.makeText(SearchResultsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void generateSearchProductsList(List<CategoryProducts> productsList) {
        recyclerView = findViewById(R.id.search_products_recycler_view);
        searchResultsAdapter = new SearchResultsAdapter(SearchResultsActivity.this, productsList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(SearchResultsActivity.this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(searchResultsAdapter);
    }

    @Override
    public void onClick(CategoryProducts products) {
        Intent intent=new Intent( SearchResultsActivity.this, DisplayProductActivity.class);
        intent.putExtra("productId", products.getProductId());
        startActivity(intent);
    }
}
