package com.sa.feature.cart.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CartDao {

    @Query("SELECT * FROM cart_items")
    List<CartItemEntity> getAll();

    @Query("SELECT * FROM cart_items WHERE productId = :productId LIMIT 1")
    CartItemEntity findByProductId(int productId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(CartItemEntity item);

    @Query("UPDATE cart_items SET quantity = :quantity WHERE productId = :productId")
    void updateQuantity(int productId, int quantity);

    @Query("DELETE FROM cart_items WHERE productId = :productId")
    void deleteByProductId(int productId);

    @Query("DELETE FROM cart_items")
    void clearAll();
}
