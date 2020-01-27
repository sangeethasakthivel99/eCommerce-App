package com.example.furniturefinal.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.furniturefinal.R;
import com.example.furniturefinal.activities.OrderListActivity;
import com.example.furniturefinal.pojoclass.OrderPageDto;

import java.util.List;
public class DisplayHistoryDetailAdapter extends RecyclerView.Adapter<DisplayHistoryDetailAdapter.ViewHolder> {
    Context context;
    List<OrderPageDto> historyDetailModelList;
    public DisplayHistoryDetailAdapter(Context context, List<OrderPageDto> historyDetailModelList) {
        this.context = context;
        this.historyDetailModelList = historyDetailModelList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_view_history_details, parent, false);
        DisplayHistoryDetailAdapter.ViewHolder viewHolder = new DisplayHistoryDetailAdapter.ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int index = position;
        Glide.with(holder.imageUrl.getContext()).load(historyDetailModelList.get(position).getImageUrl())
                .into(holder.imageUrl);
        holder.productName.setText(historyDetailModelList.get(position).getProductName());
        holder.productPrice.setText(String.valueOf(historyDetailModelList.get(position).getProductPrice()));
        holder.quantityBrought.setText(String.valueOf(historyDetailModelList.get(position).getQuantityBrought()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OrderListActivity.class);
                context.startActivity(intent);
            }
        });
        holder.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, OrderListActivity.class);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return historyDetailModelList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button back;
        private TextView productName,quantityBrought,productPrice;
        ImageView imageUrl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            back=itemView.findViewById(R.id.back);
            productName=itemView.findViewById(R.id.product_name);
            productPrice=itemView.findViewById(R.id.product_price);
            quantityBrought=itemView.findViewById(R.id.product_quantity);
            imageUrl=itemView.findViewById(R.id.product_image);
        }
    }
}