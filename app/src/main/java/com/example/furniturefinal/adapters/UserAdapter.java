package com.example.furniturefinal.adapters;

/*import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.furniturefinal.R;
import com.example.furniturefinal.pojoclass.PopularProducts;
import com.example.furniturefinal.pojoclass.UserModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<UserModel> userList;

    public UserAdapter(Context context, List<UserModel> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public CategoryAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item,parent,false);
        RecyclerView.ViewHolder viewHolder=new UserAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.image.getRootView() .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserCommunication.onClick(userList.get(position));
            }
        });

        Glide.with(holder.image.getContext()).load(userList.get(position).getImage())
                .into(holder.image);

        holder.name.setText(userList.get(position).getName();
        holder.address.setText(String.valueOf(userList.get(position).getAddress()));
        holder.email.setText(String.valueOf(userList.get(position).getEmail()));
        holder.contactNo.setText(String.valueOf(userList.get(position).getEmail()));

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends UserAdapter.ViewHolder {
       ImageView imageUrl;
       EditText name,address,email;
       EditText contactNo;
        public ViewHolder(@NonNull View itemView) {
                super(itemView);

                imageUrl = itemView.findViewsWithText(R.id.image);
                name = itemView.findViewById(R.id.editName);
                address = itemView.findViewById(R.id.editAddress);
                contactNo= itemView.findViewById(R.id.editPhoneNumber);
                email=itemView.findViewById(R.id.editEmail);

            }
        }
    public interface UserCommunication{
        static void onClick(UserModel userModel);
    }
        }
    }
}
*/