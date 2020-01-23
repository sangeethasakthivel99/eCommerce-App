package com.example.furniturefinal.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniturefinal.R;
import com.example.furniturefinal.pojoclass.Merchant;

import java.util.List;

public class MerchantDisplayInProductPageAdapter extends RecyclerView.Adapter<MerchantDisplayInProductPageAdapter.CustomViewHolder> {

    private List<Merchant> dataList;
    private MerchantDisplayInProductPageCommunication merchantDisplayInProductPageCommunication;
    private int index = -1;

    public MerchantDisplayInProductPageAdapter(MerchantDisplayInProductPageCommunication merchantDisplayInProductPageCommunication, List<Merchant> merchantList) {
        this.dataList = merchantList;
        this.merchantDisplayInProductPageCommunication = merchantDisplayInProductPageCommunication;
    }

    @NonNull
    @Override
    public MerchantDisplayInProductPageAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.merchant_view, parent,false);
        return new MerchantDisplayInProductPageAdapter.CustomViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        holder.merchantDetails.getRootView() .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                merchantDisplayInProductPageCommunication.onClick(dataList.get(position));
            }
        });
        holder.merchantDetails.setText(dataList.get(position).getName()+": " + String.valueOf(dataList.get(position).getProductsPrice()));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = position;
                notifyDataSetChanged();
            }
        });

        if(index==position){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#FF4081"));
            holder.merchantDetails.setTextColor(Color.parseColor("#FFFFFF"));
        }else{
            holder.linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.merchantDetails.setTextColor(Color.parseColor("#000000"));
        }
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView merchantDetails;
        LinearLayout linearLayout;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout);
            merchantDetails = itemView.findViewById(R.id.merchant_details);
        }
    }

    public interface MerchantDisplayInProductPageCommunication{
        void onClick(Merchant merchant);
    }

}

