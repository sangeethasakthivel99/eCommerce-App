package com.example.furniturefinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.furniturefinal.R;
import com.example.furniturefinal.adapters.MerchantDisplayInProductPageAdapter;
import com.example.furniturefinal.pojoclass.Merchant;
import com.example.furniturefinal.pojoclass.ProductDetailResponse;
import com.example.furniturefinal.pojoclass.ResponseDto;
import com.example.furniturefinal.retrofit.Endpoint;
import com.example.furniturefinal.retrofit.RetrofitClass;
import com.example.furniturefinal.viewHolder.CartModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayProductActivity extends AppCompatActivity implements MerchantDisplayInProductPageAdapter.MerchantDisplayInProductPageCommunication{
    private MerchantDisplayInProductPageAdapter merchantDisplayInProductPageAdapter;
    private RatingBar ratingBar;
    private Button addToCart;
    private ImageView image;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Bundle savedInstanceState;
    private TextView productName;
    private TextView price;
    private TextView productDescription;
    private List<CartModel> list;
    private TextView attibutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);
        Endpoint service = RetrofitClass.getRetrofit().create(Endpoint.class);
        Intent intent = getIntent();
        String productId = intent.getStringExtra("productId");

        image = findViewById(R.id.imageUrl);
        ratingBar = findViewById(R.id.rating);
        ratingBar.setIsIndicator(true);
        productName = findViewById(R.id.productName);
        price = findViewById(R.id.productPrice);
        productDescription = findViewById(R.id.product_description);
        attibutes = findViewById(R.id.productAttributes);

//        Call<Products> productDetails = service.getProductDetails(productId);
//        productDetails.enqueue(new Callback<Products>() {
//            @Override
//            public void onResponse(Call<Products> call, Response<Products> response) {
//                generateMerchantList(response.body().getMerchantList());
//                if(response != null){
//                    Glide.with(DisplayProductActivity.this).load(response.body().getImageUrl())
//                            .into(image);
//                    productName.setText(response.body().getProductName());
//                    ratingBar.setNumStars(response.body().getProductRating());
//                    price.setText(String.valueOf(response.body().getProductPrice()));
//
//                    productDescription.setText(response.body().getDescription());
//                    Map<String, String> getAttributes = response.body().getProductAttributes();
//                    StringBuilder tempAttributeStorage = new StringBuilder();
//                    for (Map.Entry<String,String> entry : getAttributes.entrySet())
//                        tempAttributeStorage.append(entry.getKey() + ": " + entry.getValue() + "\n");
//                    attibutes.setText(tempAttributeStorage.toString());
//                }
//            }
//            @Override
//            public void onFailure(Call<Products> call, Throwable t) {
//                Toast.makeText(DisplayProductActivity.this, "Something went wrong with products...Please try later!", Toast.LENGTH_SHORT).show();
//            }
//        });

        Call<ResponseDto<ProductDetailResponse>> productCall = service.getProductDetailsGeneric(productId);
        productCall.enqueue(new Callback<ResponseDto<ProductDetailResponse>>() {
            @Override
            public void onResponse(Call<ResponseDto<ProductDetailResponse>> call, Response<ResponseDto<ProductDetailResponse>> response) {
                generateMerchantList(response.body().getData().getMerchantList());
                Glide.with(DisplayProductActivity.this).load(response.body().getData().getProduct().getImageUrl())
                            .into(image);
                    productName.setText(response.body().getData().getProduct().getProductName());
                    ratingBar.setRating(response.body().getData().getProduct().getProductRating());
                    price.setText("Price: " + String.valueOf(response.body().getData().getProduct().getProductPrice()));

                    productDescription.setText(response.body().getData().getProduct().getDescription());
                    Map<String, String> getAttributes = response.body().getData().getProduct().getProductAttributes();
                    StringBuilder tempAttributeStorage = new StringBuilder();
                    for (Map.Entry<String,String> entry : getAttributes.entrySet())
                        tempAttributeStorage.append(entry.getKey() + ": " + entry.getValue() + "\n");
                    attibutes.setText(tempAttributeStorage.toString());
            }

            @Override
            public void onFailure(Call<ResponseDto<ProductDetailResponse>> call, Throwable t) {
                Toast.makeText(DisplayProductActivity.this, "Failed3", Toast.LENGTH_LONG).show();
            }
        });

       addToCart=findViewById(R.id.addToCart);
       addToCart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(DisplayProductActivity.this,CartActivity.class);
               startActivity(intent);
           }
       });

//        Glide.with(DisplayProductActivity.this).load("https://ii1.pepperfry.com/media/catalog/product/m/i/494x544/Minimalistic-Sheesham-Wood-Coffee-Table-16013-1341407138QXRrdA.jpg")
//                .into(image);
//       ratingBar.setRating(3.5f);
//       productName.setText("Table");
//       price.setText("Price : 49999");
//       productDescription.setText("This is descriptions");
//        Map<String, String> hm = new HashMap<>();
//        hm.put("Attribute 1", "Good");
//        hm.put("Attribute 2", "Great");
//        hm.put("Attribute 3", "Awesome");
//
//        StringBuilder tempAttributeStorage1 = new StringBuilder();
//        for (Map.Entry<String,String> entry : hm.entrySet())
//            tempAttributeStorage1.append(entry.getKey() + ": " + entry.getValue() + "\n");
//        attibutes.setText(tempAttributeStorage1.toString());
//
//        List<Merchant> merchantCheckList = new ArrayList<>();
//
//
//            Merchant m1 = new Merchant();
//            m1.setMerchantId(1);
//            m1.setMerchantName("AA");
//            m1.setProductsPrice(100);
//            Merchant m2 = new Merchant();
//            m2.setMerchantName("BB");
//            m2.setMerchantId(2);
//            m2.setProductsPrice(200);
//
//
//            merchantCheckList.add(m1);
//            merchantCheckList.add(m2);

       // generateMerchantList(merchantCheckList);
    }
    private void generateMerchantList(List<Merchant> merchantList) {
        recyclerView = findViewById(R.id.merchant_recycler_view);
        merchantDisplayInProductPageAdapter = new MerchantDisplayInProductPageAdapter(DisplayProductActivity.this, merchantList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DisplayProductActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(merchantDisplayInProductPageAdapter);
    }

    @Override
    public void onClick(Merchant merchant) {

    }
}
