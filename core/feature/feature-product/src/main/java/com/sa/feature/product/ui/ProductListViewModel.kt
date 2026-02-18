package com.sa.feature.product.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sa.core.ui.component.BottomNavItem
import com.sa.feature.product.domain.model.Product
import com.sa.feature.product.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class ProductListViewModel(
    getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductListUiState())
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    val products: Flow<PagingData<ProductListItem>> = getProductsUseCase()
        .map { pagingData -> pagingData.map { it.toUiModel() } }
        .cachedIn(viewModelScope)

    fun onBottomNavItemSelected(item: BottomNavItem) {
        _uiState.update { it.copy(selectedNavItem = item) }
    }
}

private fun Product.toUiModel(): ProductListItem = ProductListItem(
    id = id,
    title = title,
    price = price,
    rating = rating,
    thumbnailUrl = thumbnailUrl
)
