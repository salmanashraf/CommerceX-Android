package com.sa.feature.cart.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sa.feature.cart.domain.model.CartItem
import com.sa.feature.cart.domain.model.CartProductPayload
import com.sa.feature.cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<CartEvent>()
    val events: SharedFlow<CartEvent> = _events.asSharedFlow()

    init {
        viewModelScope.launch { cartRepository.refresh() }
        observeCart()
    }

    private fun observeCart() {
        viewModelScope.launch {
            cartRepository.cartItems.collectLatest { items ->
                _uiState.update {
                    it.copy(
                        items = items,
                        itemCount = items.sumOf { item -> item.quantity },
                        totalAmount = items.sumOf { item -> item.subtotal }
                    )
                }
            }
        }
    }

    fun addToCart(payload: CartProductPayload) {
        viewModelScope.launch {
            cartRepository.addToCart(payload)
            _events.emit(CartEvent.Message("Added to cart"))
        }
    }

    fun updateQuantity(productId: Int, quantity: Int) {
        viewModelScope.launch {
            cartRepository.updateQuantity(productId, quantity)
        }
    }

    fun removeItem(item: CartItem) {
        viewModelScope.launch {
            cartRepository.removeFromCart(item.productId)
            _events.emit(CartEvent.ItemRemoved(item))
        }
    }

    fun undoRemove(item: CartItem) {
        viewModelScope.launch {
            cartRepository.restoreItem(item)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            cartRepository.clearCart()
        }
    }

    fun checkout() {
        viewModelScope.launch {
            cartRepository.clearCart()
            _events.emit(CartEvent.CheckoutCompleted("Checkout successful. Cart cleared."))
        }
    }
}

data class CartUiState(
    val items: List<CartItem> = emptyList(),
    val itemCount: Int = 0,
    val totalAmount: Double = 0.0
)

sealed interface CartEvent {
    data class Message(val message: String) : CartEvent
    data class ItemRemoved(val item: CartItem) : CartEvent
    data class CheckoutCompleted(val message: String) : CartEvent
}
