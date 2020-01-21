package com.example.furniturefinal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.furniturefinal.R;
import com.example.furniturefinal.activities.HomeActivity;
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

            holder.categoryName.getRootView() .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    categoryCommunication.onClick(dataList.get(position));
                }
            });

            holder.categoryName.setText(dataList.get(position).getCategory_name());

        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder {
            TextView categoryName;

            public CustomViewHolder(@NonNull View itemView) {
                super(itemView);

                categoryName = itemView.findViewById(R.id.category);


            }
        }

        public interface CategoryCommunication{
            void onClick(Categories categories);
        }
    }