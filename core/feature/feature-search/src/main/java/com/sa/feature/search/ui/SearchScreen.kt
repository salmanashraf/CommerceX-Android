package com.sa.feature.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import com.sa.core.ui.component.Badge
import com.sa.core.ui.component.CategoryChipsRow
import com.sa.core.ui.component.GenericErrorState
import com.sa.core.ui.component.LoadingProductGridState
import com.sa.core.ui.component.NoProductsAvailableState
import com.sa.core.ui.component.NoSearchResultsState
import com.sa.core.ui.component.ProductCard
import com.sa.core.ui.component.SearchBar
import com.sa.core.ui.component.SimpleTopAppBar
import com.sa.core.ui.theme.Spacing
import com.sa.core.ui.theme.TextSecondaryColor
import com.sa.feature.cart.ui.CartViewModel
import org.koin.androidx.compose.koinViewModel
import kotlin.math.roundToInt

@Composable
fun SearchRoute(
    onBackClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onProductClick: (Int) -> Unit = {},
    viewModel: SearchViewModel = koinViewModel(),
    cartViewModel: CartViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val cartState by cartViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) {
        SimpleTopAppBar(
            title = "Search",
            onBackClick = onBackClick,
            actions = {
                Box {
                    IconButton(onClick = onCartClick) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Cart"
                        )
                    }
                    if (cartState.itemCount > 0) {
                        Badge(
                            count = cartState.itemCount,
                            modifier = Modifier.align(Alignment.TopEnd)
                        )
                    }
                }
            }
        )

        SearchBar(
            value = uiState.query,
            onValueChange = viewModel::onQueryChanged,
            onSearch = { viewModel.onSearchTriggered() },
            modifier = Modifier.padding(horizontal = Spacing.lg)
        )

        CategoryChipsRow(
            categories = uiState.categories,
            selectedCategory = uiState.selectedCategory,
            onCategorySelected = viewModel::onCategorySelected,
            modifier = Modifier.padding(top = Spacing.md)
        )

        when {
            uiState.errorMessage != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    GenericErrorState(onRetry = { viewModel.onSearchTriggered() })
                }
            }

            uiState.isLoading -> {
                LoadingProductGridState(modifier = Modifier.fillMaxSize())
            }

            uiState.results.isEmpty() && uiState.query.isNotBlank() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    NoSearchResultsState(query = uiState.query)
                }
            }

            uiState.results.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    NoProductsAvailableState()
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(Spacing.lg),
                    verticalArrangement = Arrangement.spacedBy(Spacing.md)
                ) {
                    item {
                        Text(
                            text = "${uiState.results.size} results",
                            style = MaterialTheme.typography.labelLarge,
                            color = TextSecondaryColor,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    items(uiState.results, key = { it.id }) { product ->
                        ProductCard(
                            imageUrl = product.thumbnailUrl,
                            title = product.title,
                            price = product.price,
                            discountPercent = product.discountPercent.roundToInt(),
                            rating = product.rating,
                            reviewCount = product.reviewCount,
                            onClick = { onProductClick(product.id) }
                        )
                    }
                }
            }
        }
    }
}
