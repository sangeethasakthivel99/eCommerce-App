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
import com.example.furniturefinal.pojoclass.Categories;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CustomViewHolder> {

        private List<Categories> dataList;
        CategoryCommunication categoryCommunication;

        public CategoryAdapter(CategoryCommunication categoryCommunication, List<Categories> categoriesList) {
            this.dataList = categoriesList;
            this.categoryCommunication = categoryCommunication;
        }

        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_view, parent,false);
            return new CategoryAdapter.CustomViewHolder(root);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder holder,final int position) {

            holder.categoryImageUrl.getRootView() .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    categoryCommunication.onClick(dataList.get(position));
                }
            });

            holder.categoryName.setText(dataList.get(position).getCategoryName());
            Glide.with(holder.categoryImageUrl.getContext()).load(dataList.get(position).getCategoryImageUrl())
                    .into(holder.categoryImageUrl);
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder {
            TextView categoryName;
            ImageView categoryImageUrl;

            public CustomViewHolder(@NonNull View itemView) {
                super(itemView);

                categoryName = itemView.findViewById(R.id.categoryName);
                categoryImageUrl = itemView.findViewById(R.id.categoryImageUrl);

            }
        }

        public interface CategoryCommunication{
            void onClick(Categories categories);
        }
    }