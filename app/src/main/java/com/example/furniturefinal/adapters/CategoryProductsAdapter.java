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
import com.example.furniturefinal.pojoclass.CategoryProducts;

import java.util.List;

public class CategoryProductsAdapter extends RecyclerView.Adapter<CategoryProductsAdapter.CustomViewHolder> {

    private List<CategoryProducts> dataList;
    private CategoryProductsCommunication categoryProductsCommunication;

    public CategoryProductsAdapter(CategoryProductsCommunication categoryProductsCommunication, List<CategoryProducts> productsList) {
        this.dataList = productsList;
        this.categoryProductsCommunication = categoryProductsCommunication;
    }

    @NonNull
    @Override
    public CategoryProductsAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_products_view, parent,false);
        return new CategoryProductsAdapter.CustomViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryProductsAdapter.CustomViewHolder holder, final int position) {

        holder.productImage.getRootView() .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryProductsCommunication.onClick(dataList.get(position));
            }
        });

        Glide.with(holder.productImage.getContext()).load(dataList.get(position).getImage())
                .into(holder.productImage);

        holder.productName.setText(dataList.get(position).getProductName());
        holder.price.setText(String.valueOf(dataList.get(position).getPrice()));
        holder.rating.setText(String.valueOf(dataList.get(position).getRatings()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView price;
        TextView rating;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.imageUrl);
            productName = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.productPrice);
            rating = itemView.findViewById(R.id.rating);
        }
    }

    public interface CategoryProductsCommunication{
        void onClick(CategoryProducts products);
    }

}
