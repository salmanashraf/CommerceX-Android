package com.sa.feature.cart.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CartItemEntity.class}, version = 1, exportSchema = false)
public abstract class CartDatabase extends RoomDatabase {
    public abstract CartDao cartDao();
}
