package com.example.furniturefinal.retrofit;

import com.example.furniturefinal.database.CartProduct;
import com.example.furniturefinal.pojoclass.Categories;
import com.example.furniturefinal.pojoclass.CategoryProducts;
import com.example.furniturefinal.pojoclass.CustomerDto;
import com.example.furniturefinal.pojoclass.DummyCartDto;
import com.example.furniturefinal.pojoclass.HistoryElements;
import com.example.furniturefinal.pojoclass.Order;
import com.example.furniturefinal.pojoclass.OrderDto;
import com.example.furniturefinal.pojoclass.OrderPageDto;
import com.example.furniturefinal.pojoclass.PopularProducts;
import com.example.furniturefinal.pojoclass.ProductDetailResponse;
import com.example.furniturefinal.pojoclass.ResponseDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET("/search/get/{productId}")
    Call<ResponseDto<ProductDetailResponse>> getProductDetailsBackup(@Path("productId")String productId);

    @GET("/search/searchQuery/{query}")
    Call<List<CategoryProducts>> getSearchedProducts(@Path("query")String query);

    @POST("/login/")
    Call<ResponseDto> logIn(@Body CustomerDto customerDto);

    @POST("/cartandorder/cart")
    Call<ResponseDto<String>> addToCartBackend(@Body DummyCartDto dummyCartDto);

    @GET("/cartandorder/cart")
    Call<ResponseDto<List<CartProduct>>> getCartFromBackend();

    @POST("/cartandorder/order")
    Call<ResponseDto<Order>> checkout(@Body OrderDto orderDto);

    @GET("/cartandorder/order")
    Call<ResponseDto<List<HistoryElements>>> getOrderHistory();

    @GET("/cartandorder/orderedItem/{orderId}")
    Call<ResponseDto<List<OrderPageDto>>>

    getOrderedItems(@Path("orderId")String orderId);
}
