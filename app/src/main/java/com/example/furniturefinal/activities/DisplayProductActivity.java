package com.example.furniturefinal.activities;

import android.content.Intent;
import android.content.SharedPreferences;
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
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.furniturefinal.R;
import com.example.furniturefinal.adapters.MerchantDisplayInProductPageAdapter;
import com.example.furniturefinal.database.AppDatabase;
import com.example.furniturefinal.database.CartProduct;
import com.example.furniturefinal.database.CartProductDAO;
import com.example.furniturefinal.pojoclass.CartModel;
import com.example.furniturefinal.pojoclass.Merchant;
import com.example.furniturefinal.pojoclass.ProductDetailResponse;
import com.example.furniturefinal.pojoclass.ResponseDto;
import com.example.furniturefinal.retrofit.Endpoint;
import com.example.furniturefinal.retrofit.RetrofitClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
    private Button increment;
    private Button decrement;
    private TextView textCount;
    private String merchantId;
    private int merchantProductPrice;
    private SharedPreferences sharedPreferences;
    private String imageUrl;
    private CartProductDAO cartProductDAO;
    private String productNameSave;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);
        final Endpoint service = RetrofitClass.getRetrofit().create(Endpoint.class);
        Intent intent = getIntent();
        final String productId = intent.getStringExtra("productId");

        auth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = auth.getCurrentUser();

        image = findViewById(R.id.imageUrl);
        ratingBar = findViewById(R.id.rating);
        ratingBar.setIsIndicator(true);
        productName = findViewById(R.id.productName);
        price = findViewById(R.id.productPrice);
        productDescription = findViewById(R.id.product_description);
        attibutes = findViewById(R.id.productAttributes);
        increment = findViewById(R.id.increase);
        decrement = findViewById(R.id.decrease);
        textCount = findViewById(R.id.textCount);
        final AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "CartProduct")
                .allowMainThreadQueries()
                .build();

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(String.valueOf(textCount.getText())) + 1;
                textCount.setText(String.valueOf(count));
//                cartList.get(index).setQuantityBrought(cartList.get(index).getQuantityBrought() + 1);
//                holder.textCount.setText(String.valueOf(cartList.get(index).getQuantityBrought()));

            }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(String.valueOf(textCount.getText()));

                if (count > 1)
                    count --;
                textCount.setText(String.valueOf(count));

            }
        });

        Call<ResponseDto<ProductDetailResponse>> productCall = service.getProductDetailsGeneric(productId);

        productCall.enqueue(new Callback<ResponseDto<ProductDetailResponse>>() {
            @Override
            public void onResponse(Call<ResponseDto<ProductDetailResponse>> call, Response<ResponseDto<ProductDetailResponse>> response) {

               boolean successMessage = response.body().getSuccess();
               if(!successMessage)
               {
                   Call<ResponseDto<ProductDetailResponse>> alternateProductCall = service.getProductDetailsBackup(productId);
                   alternateProductCall.enqueue(new Callback<ResponseDto<ProductDetailResponse>>() {
                       @Override
                       public void onResponse(Call<ResponseDto<ProductDetailResponse>> call, Response<ResponseDto<ProductDetailResponse>> response) {
                           binding(response);
                       }

                       @Override
                       public void onFailure(Call<ResponseDto<ProductDetailResponse>> call, Throwable t) {

                       }
                   });
               }
               else {
                   binding(response);
               }
            }

            @Override
            public void onFailure(Call<ResponseDto<ProductDetailResponse>> call, Throwable t) {
                Toast.makeText(DisplayProductActivity.this, "Redirecting..", Toast.LENGTH_LONG).show();
                Call<ResponseDto<ProductDetailResponse>> alternateProductCall = service.getProductDetailsBackup(productId);
                alternateProductCall.enqueue(new Callback<ResponseDto<ProductDetailResponse>>() {
                    @Override
                    public void onResponse(Call<ResponseDto<ProductDetailResponse>> call, Response<ResponseDto<ProductDetailResponse>> response) {
                        binding(response);
                    }

                    @Override
                    public void onFailure(Call<ResponseDto<ProductDetailResponse>> call, Throwable t) {

                    }
                });
            }
        });

       addToCart=findViewById(R.id.addToCart);
       addToCart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(DisplayProductActivity.this, CartActivity.class);

               cartProductDAO = database.getCartProductDAO();
               CartProduct cartProduct = new CartProduct();
               cartProduct.setProductId(productId);
               cartProduct.setMerchantId(merchantId);
               cartProduct.setProductPrice(merchantProductPrice * Integer.parseInt(textCount.getText().toString()));
               cartProduct.setQuantityBrought(Integer.parseInt(textCount.getText().toString()));
               cartProduct.setImageUrl(imageUrl);
               cartProduct.setProductName(productNameSave);

               CartProduct product = cartProductDAO.getItemById(productId, merchantId);
               if(product != null)
               {
                   product.setQuantityBrought(product.getQuantityBrought() + Integer.parseInt(textCount.getText().toString()));
                    cartProductDAO.update(product);
                   startActivity(intent);
               }
               else if(merchantId != null) {
                   cartProductDAO.insert(cartProduct);
//                   if (merchantId != null)
                       startActivity(intent);
               }
                else
                    Toast.makeText(DisplayProductActivity.this, "Please select merchant!!", Toast.LENGTH_SHORT).show();
           }
       });
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
        merchantId = merchant.getMerchantId();
        merchantProductPrice = merchant.getProductsPrice();
    }
    public void binding(Response<ResponseDto<ProductDetailResponse>> response){
        generateMerchantList(response.body().getData().getMerchantList());
        Glide.with(DisplayProductActivity.this).load(response.body().getData().getProduct().getImageUrl())
                .into(image);
        productNameSave = response.body().getData().getProduct().getProductName();
        productName.setText(productNameSave);
        ratingBar.setRating(response.body().getData().getProduct().getProductRating());
        price.setText("Price: " + String.valueOf(response.body().getData().getProduct().getProductPrice()));
        imageUrl = response.body().getData().getProduct().getImageUrl();
        productDescription.setText(response.body().getData().getProduct().getDescription());
        Map<String, String> getAttributes = response.body().getData().getProduct().getProductAttributes();
        StringBuilder tempAttributeStorage = new StringBuilder();
        for (Map.Entry<String,String> entry : getAttributes.entrySet())
            tempAttributeStorage.append(entry.getKey() + ": " + entry.getValue() + "\n");
        attibutes.setText(tempAttributeStorage.toString());
    }
}
