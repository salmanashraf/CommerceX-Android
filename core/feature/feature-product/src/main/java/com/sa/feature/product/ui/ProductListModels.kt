package com.sa.feature.product.ui

import com.sa.core.ui.component.BottomNavItem

data class ProductListItem(
    val id: Int,
    val title: String,
    val price: Double,
    val rating: Double,
    val thumbnailUrl: String
)

data class ProductListUiState(
    val selectedNavItem: BottomNavItem = BottomNavItem.HOME
)
