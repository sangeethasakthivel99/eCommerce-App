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
import com.example.furniturefinal.pojoclass.Products;
import com.example.furniturefinal.retrofit.Endpoint;
import com.example.furniturefinal.retrofit.RetrofitClass;
import com.example.furniturefinal.viewHolder.CartModel;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);
        Endpoint service = RetrofitClass.getRetrofit().create(Endpoint.class);
        Intent intent = new Intent();
        String productId = intent.getStringExtra("product_id");
        Call<Products> productDetails = service.getProductDetails(productId);
        image = findViewById(R.id.productImage);
        ratingBar = findViewById(R.id.rating);
        ratingBar.setIsIndicator(true);
        productName = findViewById(R.id.productName);
        price = findViewById(R.id.productPrice);
        productDescription = findViewById(R.id.product_description);
        productDetails.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                generateMerchantList(response.body().getOtherMerchants());
                if(response != null){
                    Glide.with(DisplayProductActivity.this).load("https://ii1.pepperfry.com/media/catalog/product/m/i/494x544/Minimalistic-Sheesham-Wood-Coffee-Table-16013-1341407138QXRrdA.jpg")
                            .into(image);//response.body().getImage()
                    productName.setText("Product1");//response.body().getProductName()
                    ratingBar.setNumStars(3);//response.body().getProductRating()
                    price.setText(String.valueOf(100));//response.body().getPrice())
//                    Map<String, String> hm = new HashMap<String, String>();
//                    hm.put("USA", "Washington");
//                    hm.put("United Kingdom", "London");
//                    hm.put("India", "New Delhi");
                    productDescription.setText("Random Description");
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Toast.makeText(DisplayProductActivity.this, "Something went wrong with products...Please try later!", Toast.LENGTH_SHORT).show();
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

        Glide.with(DisplayProductActivity.this).load("https://ii1.pepperfry.com/media/catalog/product/m/i/494x544/Minimalistic-Sheesham-Wood-Coffee-Table-16013-1341407138QXRrdA.jpg")
                .into(image);//response.body().getImage()
       ratingBar.setRating(3.5f);
       productName.setText("Sofa 4 seat");
       price.setText("Price : 49999");
       productDescription.setText("This is descriptions");

        List<Merchant> merchantCheckList = new ArrayList<>();


            Merchant m1 = new Merchant();
            m1.setMerchantId(1);
            m1.setMerchantName("AA");
            m1.setPrice(100);
            Merchant m2 = new Merchant();
            m2.setMerchantName("BB");
            m2.setMerchantId(2);
            m2.setPrice(200);


            merchantCheckList.add(m1);
            merchantCheckList.add(m2);

        generateMerchantList(merchantCheckList);
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
