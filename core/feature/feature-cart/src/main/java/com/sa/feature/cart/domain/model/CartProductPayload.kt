package com.sa.feature.cart.domain.model

data class CartProductPayload(
    val productId: Int,
    val title: String,
    val thumbnailUrl: String,
    val price: Double,
    val discountPercent: Double,
    val quantity: Int
)
