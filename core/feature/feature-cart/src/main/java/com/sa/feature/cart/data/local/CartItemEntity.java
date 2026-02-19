package com.sa.feature.cart.data.local;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_items")
public class CartItemEntity {
    @PrimaryKey
    public int productId;

    @NonNull
    public String title;

    @NonNull
    public String thumbnailUrl;

    public double price;

    public double discountPercent;

    public int quantity;

    public CartItemEntity(int productId,
                          @NonNull String title,
                          @NonNull String thumbnailUrl,
                          double price,
                          double discountPercent,
                          int quantity) {
        this.productId = productId;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.price = price;
        this.discountPercent = discountPercent;
        this.quantity = quantity;
    }
}
