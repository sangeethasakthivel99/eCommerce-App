package com.example.furniturefinal.adapters;

import android.content.Context;
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
import com.example.furniturefinal.database.AppDatabase;
import com.example.furniturefinal.database.CartProduct;
import com.example.furniturefinal.database.CartProductDAO;

import java.util.List;

public class DisplayCartAdapter extends RecyclerView.Adapter<DisplayCartAdapter.ViewHolder> {
    Context context;
    List<CartProduct> cartList;
    private TextView textCount;
    private CartProductDAO cartProductDAO;
    AppDatabase database;

    public DisplayCartAdapter(Context context, List<CartProduct> cartList, AppDatabase database) {
        this.context = context;
        this.cartList = cartList;
        this.database = database;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,final int position) {

        final int index = position;
        holder.productName.setText(cartList.get(position).getProductName());
        holder.productPrice.setText(String.valueOf(cartList.get(position).getProductPrice()));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), CartActivity.class);
//                context.startActivity(intent);
//            }
//        });

        if (cartList.get(position).getQuantityBrought() >= 1) {
            holder.textCount.setText(String.valueOf(cartList.get(position).getQuantityBrought()));
        }
        holder.increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cartList.get(index).setQuantityBrought(cartList.get(index).getQuantityBrought() + 1);
                holder.textCount.setText(String.valueOf(cartList.get(index).getQuantityBrought()));
                cartProductDAO = database.getCartProductDAO();
                CartProduct product = cartProductDAO.getItemById(cartList.get(index).getProductId(), cartList.get(index).getMerchantId());
                product.setProductPrice((product.getProductPrice()/product.getQuantityBrought()) * (product.getQuantityBrought() + 1));
                product.setQuantityBrought(product.getQuantityBrought() + 1);
                cartProductDAO.update(product);
                cartList.set(position, product);
                cartList.get(position).setQuantityBrought(product.getQuantityBrought());
                notifyItemChanged(position);
            }
        });

        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(String.valueOf(holder.textCount.getText()));
                cartProductDAO = database.getCartProductDAO();

                    if (cartList.get(index).getQuantityBrought() > 1) {
                        cartList.get(index).setQuantityBrought(cartList.get(index).getQuantityBrought() - 1);
                        holder.textCount.setText(String.valueOf(cartList.get(index).getQuantityBrought()));

                        CartProduct product = cartProductDAO.getItemById(cartList.get(index).getProductId(), cartList.get(index).getMerchantId());
                        product.setProductPrice((product.getProductPrice() / product.getQuantityBrought()) * (product.getQuantityBrought() - 1));
                        product.setQuantityBrought(product.getQuantityBrought() - 1);
                        cartProductDAO.update(product);
                        cartList.set(position, product);
                        cartList.get(position).setQuantityBrought(product.getQuantityBrought());
                        notifyItemChanged(position);
                    } else if (cartList.get(index).getQuantityBrought() == 1) {
                        CartProduct cartProduct = cartProductDAO.getItemById(cartList.get(index).getProductId(), cartList.get(index).getMerchantId());
                        cartProductDAO.delete(cartProduct);
                        cartList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, getItemCount());
                    }

            }
        });

        Glide.with(holder.img.getContext()).load(cartList.get(position).getImageUrl())
                .into(holder.img);

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
            img = itemView.findViewById(R.id.smallImageUrl);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            increment = itemView.findViewById(R.id.increase);
            decrease = itemView.findViewById(R.id.decrease);
            textCount = itemView.findViewById(R.id.textCount);

        }
    }
}
