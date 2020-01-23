package com.example.furniturefinal.retrofit;

import com.example.furniturefinal.pojoclass.Categories;
import com.example.furniturefinal.pojoclass.CategoryProducts;
import com.example.furniturefinal.pojoclass.PopularProducts;
import com.example.furniturefinal.pojoclass.ProductDetailResponse;
import com.example.furniturefinal.pojoclass.ResponseDto;
import com.example.furniturefinal.viewHolder.CartModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Endpoint {

    @GET("/product/getCategories")
    Call<ResponseDto<List<Categories>>> getCategoriesGeneric();

    @GET("/product/recommendations")
    Call<ResponseDto<List<PopularProducts>>> getPopularProductsGeneric();

    @GET("/product/getCategoryProducts/{categoryId}")
    Call<ResponseDto<List<CategoryProducts>>> getCategoryProduct(@Path("categoryId")String categoryId);

    @GET("/product/getProductDetails/{productId}")
    Call<ResponseDto<ProductDetailResponse>> getProductDetailsGeneric(@Path("productId")String productId);

    @GET("/getOrder/{customerId}")
    Call<List<CartModel>> getOrder(@Path("customerId")String customerId);

    @GET("search/searchQuery/{query}")
    Call<List<CategoryProducts>> getSearchedProducts(@Path("query")String query);
}
