package com.sa.feature.cart.domain.model

data class CartItem(
    val productId: Int,
    val title: String,
    val thumbnailUrl: String,
    val price: Double,
    val discountPercent: Double,
    val quantity: Int
) {
    val unitPrice: Double
        get() = if (discountPercent > 0) price * (1 - discountPercent / 100) else price

    val subtotal: Double
        get() = unitPrice * quantity
}
