package com.example.furniturefinal.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CartProductDAO {
    @Insert
    public void insert(CartProduct... cartProducts);

    @Update
    public void update(CartProduct... cartProducts);

    @Delete
    public void delete(CartProduct cartProduct);

    @Query("SELECT * FROM CartProduct")
    public List<CartProduct> getCartProducts();

    @Query("SELECT * from CartProduct WHERE productId= :productId and merchantId= :merchantId")
    CartProduct getItemById(String productId, String merchantId );
}
