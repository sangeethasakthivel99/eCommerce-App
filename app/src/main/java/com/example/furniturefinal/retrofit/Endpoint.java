package com.example.furniturefinal.retrofit;

import com.example.furniturefinal.pojoclass.Categories;
import com.example.furniturefinal.pojoclass.CategoryProducts;
import com.example.furniturefinal.pojoclass.PopularProducts;
import com.example.furniturefinal.pojoclass.Products;
import com.example.furniturefinal.viewHolder.CartModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Endpoint {
    @GET("/getCategories")
    Call<List<Categories>> getCategories();

    @GET("/getPopularProducts")
    Call<List<PopularProducts>> getPopularProducts();

    @GET("/getCategoryProduct")
    Call<List<CategoryProducts>> getCategoryProduct();

    @GET("/getOrder/{customer_id}")
    Call<List<CartModel>> getOrder(@Path("customer_id")String customer_id);


}
