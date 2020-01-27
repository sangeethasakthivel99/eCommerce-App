package com.example.furniturefinal.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniturefinal.R;
import com.example.furniturefinal.activities.OrderListActivity;
import com.example.furniturefinal.activities.ViewHistoryDetails;
import com.example.furniturefinal.pojoclass.HistoryElements;

import java.util.List;
public class DisplayHistoryAdapter extends RecyclerView.Adapter<DisplayHistoryAdapter.ViewHolder> {
    Context context;
    List<HistoryElements> orderHistoryList;
    public DisplayHistoryAdapter(Context context, List<HistoryElements> orderHistoryList) {
        this.context = context;
        this.orderHistoryList = orderHistoryList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_order_history, parent, false);
        DisplayHistoryAdapter.ViewHolder viewHolder = new DisplayHistoryAdapter.ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int index = position;
        holder.orderId.setText(orderHistoryList.get(position).getOrderId());
        holder.date.setText(String.valueOf(orderHistoryList.get(position).getDate()));
        holder.totalPrice.setText(String.valueOf(orderHistoryList.get(position).getTotalPrice()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OrderListActivity.class);
                context.startActivity(intent);
            }
        });
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ViewHistoryDetails.class);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return orderHistoryList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button view;
        private TextView date,orderId,totalPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView.findViewById(R.id.view_details);
            date=itemView.findViewById(R.id.date);
            orderId=itemView.findViewById(R.id.order_id);
            totalPrice=itemView.findViewById(R.id.total_price);
        }
    }
}
