package com.example.furniturefinal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.furniturefinal.R;
import com.example.furniturefinal.pojoclass.PopularProducts;

import java.util.List;

public class PopularProductsAdapter extends RecyclerView.Adapter<PopularProductsAdapter.CustomViewHolder> {

    private List<PopularProducts> dataList;
    private PopularProductsCommunication popularProductsCommunication;

    public PopularProductsAdapter(PopularProductsCommunication popularProductsCommunication, List<PopularProducts> popularProducts) {
        this.dataList = popularProducts;
        this.popularProductsCommunication = popularProductsCommunication;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_products_view, parent,false);
        return new PopularProductsAdapter.CustomViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularProductsAdapter.CustomViewHolder holder, final int position) {
        holder.productImage.getRootView() .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popularProductsCommunication.onClick(dataList.get(position));
            }
        });

        Glide.with(holder.productImage.getContext()).load(dataList.get(position).getImageUrl())
                .into(holder.productImage);
        holder.productName.setText(dataList.get(position).getProductName());
        holder.price.setText("Price : INR "+ String.valueOf(dataList.get(position).getProductPrice()));
        holder.ratings.setText("Rating : "+ String.valueOf(dataList.get(position).getProductRating()));


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView ratings;
        TextView price;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.imageUrl);
            productName = itemView.findViewById(R.id.productName);
            ratings = itemView.findViewById(R.id.productRating);
            price = itemView.findViewById(R.id.productPrice);
        }
    }

    public interface PopularProductsCommunication{
        void onClick(PopularProducts popularProducts);
    }
}
