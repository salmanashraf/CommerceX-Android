package com.sa.feature.cart.domain.repository

import com.sa.feature.cart.domain.model.CartItem
import com.sa.feature.cart.domain.model.CartProductPayload
import kotlinx.coroutines.flow.StateFlow

interface CartRepository {
    val cartItems: StateFlow<List<CartItem>>
    suspend fun refresh()
    suspend fun addToCart(payload: CartProductPayload)
    suspend fun updateQuantity(productId: Int, quantity: Int)
    suspend fun removeFromCart(productId: Int)
    suspend fun clearCart()
    suspend fun restoreItem(item: CartItem)
}
