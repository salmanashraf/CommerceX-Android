package com.sa.feature.product.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sa.feature.product.domain.model.ProductDetail
import com.sa.feature.product.domain.usecase.GetProductDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productId: Int,
    private val getProductDetailUseCase: GetProductDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailUiState(isLoading = true))
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()

    init {
        loadProductDetail()
    }

    fun loadProductDetail() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            runCatching { getProductDetailUseCase(productId) }
                .onSuccess { product ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            product = product,
                            errorMessage = null
                        )
                    }
                }
                .onFailure {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            product = null,
                            errorMessage = "Unable to load product details."
                        )
                    }
                }
        }
    }

    fun onQuantityChange(quantity: Int) {
        _uiState.update { it.copy(quantity = quantity) }
    }

    fun onAddToCart() {
        _uiState.update { it.copy(isAddedToCart = true) }
    }
}

data class ProductDetailUiState(
    val isLoading: Boolean = false,
    val product: ProductDetail? = null,
    val quantity: Int = 1,
    val isAddedToCart: Boolean = false,
    val errorMessage: String? = null
)
