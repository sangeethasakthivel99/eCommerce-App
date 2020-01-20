package com.example.furniturefinal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.furniturefinal.R;
import com.example.furniturefinal.activities.HomeActivity;
import com.example.furniturefinal.jsonobjects.PopularProducts;

import java.util.List;

public class PopularProductsAdapter extends RecyclerView.Adapter<PopularProductsAdapter.CustomViewHolder> {

    private List<PopularProducts> dataList;

    public PopularProductsAdapter(HomeActivity homeActivity, List<PopularProducts> popularProducts) {
        this.dataList = popularProducts;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_products_view, parent,false);
        return new PopularProductsAdapter.CustomViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularProductsAdapter.CustomViewHolder holder, int position) {

        Glide.with(holder.imageView.getContext()).load(dataList.get(position).getMsg())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.popular_product);


        }
    }
}
