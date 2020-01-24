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

import com.example.furniturefinal.activities.CartActivity;
import com.example.furniturefinal.pojoclass.CartModel;
import com.example.furniturefinal.R;

import java.util.List;

public class DisplayCartAdapter extends RecyclerView.Adapter<DisplayCartAdapter.ViewHolder> {
    Context context;
    List<CartModel> cartList;
    private TextView textCount;


    public DisplayCartAdapter(Context context, List<CartModel> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
//        holder.img.setImageResource(cartList.get(position).getImage());
        final int index = position;
        holder.productName.setText(cartList.get(position).getProductName());
        holder.productPrice.setText(cartList.get(position).getProductPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CartActivity.class);
                context.startActivity(intent);
            }
        });

        if (cartList.get(position).getQuantity() == 1) {
            holder.textCount.setText(String.valueOf(cartList.get(position).getQuantity()));
        }
        holder.increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int count= Integer.parseInt(String.valueOf(holder.txtQuantity.getText()));
//                count++;
                cartList.get(index).setQuantity(cartList.get(index).getQuantity() + 1);
                holder.textCount.setText(String.valueOf(cartList.get(index).getQuantity()));

            }
        });

        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(String.valueOf(holder.textCount.getText()));

                if (cartList.get(index).getQuantity() > 1)
                    cartList.get(index).setQuantity(cartList.get(index).getQuantity() - 1);
                holder.textCount.setText(String.valueOf(cartList.get(index).getQuantity()));

            }
        });


    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button increment;
        private Button decrease;
        private TextView textCount;
        ImageView img;
        TextView productName, productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageUrl);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            increment = itemView.findViewById(R.id.increase);
            decrease = itemView.findViewById(R.id.decrease);
            textCount = itemView.findViewById(R.id.textCount);

        }
    }
}
