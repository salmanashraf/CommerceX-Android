package com.sa.feature.cart.data.repository

import com.sa.feature.cart.data.local.CartDao
import com.sa.feature.cart.domain.model.CartItem
import com.sa.feature.cart.domain.model.CartProductPayload
import com.sa.feature.cart.domain.repository.CartRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class CartRepositoryImpl(
    private val cartDao: CartDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CartRepository {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    override val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    override suspend fun refresh() {
        withContext(ioDispatcher) {
            refreshSnapshot()
        }
    }

    override suspend fun addToCart(payload: CartProductPayload) {
        withContext(ioDispatcher) {
            val existing = cartDao.findByProductId(payload.productId)
            if (existing == null) {
                cartDao.upsert(payload.toEntity())
            } else {
                cartDao.updateQuantity(payload.productId, existing.quantity + payload.quantity)
            }
            refreshSnapshot()
        }
    }

    override suspend fun updateQuantity(productId: Int, quantity: Int) {
        withContext(ioDispatcher) {
            if (quantity <= 0) return@withContext
            cartDao.updateQuantity(productId, quantity)
            refreshSnapshot()
        }
    }

    override suspend fun removeFromCart(productId: Int) {
        withContext(ioDispatcher) {
            cartDao.deleteByProductId(productId)
            refreshSnapshot()
        }
    }

    override suspend fun clearCart() {
        withContext(ioDispatcher) {
            cartDao.clearAll()
            refreshSnapshot()
        }
    }

    override suspend fun restoreItem(item: CartItem) {
        withContext(ioDispatcher) {
            cartDao.upsert(item.toEntity())
            refreshSnapshot()
        }
    }

    private fun refreshSnapshot() {
        _cartItems.value = cartDao.getAll().map { it.toDomain() }
    }
}
