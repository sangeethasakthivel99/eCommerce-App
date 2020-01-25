package com.example.furniturefinal.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CartProduct.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CartProductDAO getCartProductDAO();
}