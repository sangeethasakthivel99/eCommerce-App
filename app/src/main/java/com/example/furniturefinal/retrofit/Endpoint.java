package com.example.furniturefinal.retrofit;

import com.example.furniturefinal.pojoclass.Categories;
import com.example.furniturefinal.pojoclass.CategoryProducts;
import com.example.furniturefinal.pojoclass.PopularProducts;
import com.example.furniturefinal.pojoclass.Products;
import com.example.furniturefinal.viewHolder.CartModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Endpoint {
    @GET("/getCategories")
    Call<List<Categories>> getCategories();

    @GET("/getPopularProducts")
    Call<List<PopularProducts>> getPopularProducts();

    @GET("/getCategoryProduct/{categoryId}")
    Call<List<CategoryProducts>> getCategoryProduct(@Path("categoryId")String categoryId);

    @GET("/getProductDetails/{productId}")
    Call<Products> getProductDetails(@Path("productId")String productId);

    @GET("/getOrder/{customerId}")
    Call<List<CartModel>> getOrder(@Path("customerId")String customerId);


}
