package com.example.furniturefinal.interfaces;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.furniturefinal.viewHolder.CartModel;

import java.util.List;

public interface CartActivityInterface {
     public void increaseInteger(View view);
    public void decreaseInteger(View view);
    public  void display(int number);

}
