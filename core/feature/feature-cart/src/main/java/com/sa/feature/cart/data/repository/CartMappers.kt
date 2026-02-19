package com.sa.feature.cart.data.repository

import com.sa.feature.cart.data.local.CartItemEntity
import com.sa.feature.cart.domain.model.CartItem
import com.sa.feature.cart.domain.model.CartProductPayload

fun CartItemEntity.toDomain(): CartItem = CartItem(
    productId = productId,
    title = title,
    thumbnailUrl = thumbnailUrl,
    price = price,
    discountPercent = discountPercent,
    quantity = quantity
)

fun CartProductPayload.toEntity(): CartItemEntity = CartItemEntity(
    productId,
    title,
    thumbnailUrl,
    price,
    discountPercent,
    quantity
)

fun CartItem.toEntity(): CartItemEntity = CartItemEntity(
    productId,
    title,
    thumbnailUrl,
    price,
    discountPercent,
    quantity
)
