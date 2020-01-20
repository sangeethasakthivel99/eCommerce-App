package com.example.furniturefinal.retrofit;

import com.example.furniturefinal.jsonobjects.Categories;
import com.example.furniturefinal.jsonobjects.PopularProducts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Endpoint {
    @GET("/getCategories")
    Call<List<Categories>> getAllCategories();

    @GET("/getPopularProducts")
    Call<List<PopularProducts>> getAllPopularProducts();
}
