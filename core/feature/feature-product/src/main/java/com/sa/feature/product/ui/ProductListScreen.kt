package com.sa.feature.product.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.sa.core.ui.component.BottomNavItem
import com.sa.core.ui.component.BottomNavigationBar
import com.sa.core.ui.component.GenericErrorState
import com.sa.core.ui.component.GlassmorphicAppBar
import com.sa.core.ui.component.LoadingProductGridState
import com.sa.core.ui.component.NoProductsAvailableState
import com.sa.core.ui.component.ProductCard
import com.sa.core.ui.theme.CommerceXTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductListRoute(
    onProductClick: (Int) -> Unit = {},
    onSearchClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    viewModel: ProductListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val products = viewModel.products.collectAsLazyPagingItems()

    val isInitialLoading = products.loadState.refresh is LoadState.Loading
    val isError = products.loadState.refresh is LoadState.Error
    val isEmpty = products.loadState.refresh is LoadState.NotLoading && products.itemCount == 0

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            GlassmorphicAppBar(
                title = "CommerceX",
                cartItemCount = 0,
                onSearchClick = onSearchClick,
                onCartClick = onCartClick
            )

            when {
                isInitialLoading -> {
                    LoadingProductGridState(modifier = Modifier.weight(1f))
                }

                isError -> {
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        GenericErrorState(onRetry = { products.retry() })
                    }
                }

                isEmpty -> {
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        NoProductsAvailableState(onRefresh = { products.refresh() })
                    }
                }

                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(top = 12.dp, bottom = 88.dp)
                    ) {
                        items(
                            count = products.itemCount,
                            key = products.itemKey { it.id }
                        ) { index ->
                            val item = products[index] ?: return@items
                            ProductCard(
                                imageUrl = item.thumbnailUrl,
                                title = item.title,
                                price = item.price,
                                rating = item.rating,
                                reviewCount = 0,
                                onClick = { onProductClick(item.id) }
                            )
                        }

                        if (products.loadState.append is LoadState.Loading) {
                            item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(maxLineSpan) }) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                                }
                            }
                        }
                    }
                }
            }
        }

        BottomNavigationBar(
            selectedItem = uiState.selectedNavItem,
            onItemSelected = { item ->
                viewModel.onBottomNavItemSelected(item)
                when (item) {
                    BottomNavItem.CART -> onCartClick()
                    BottomNavItem.SEARCH -> onSearchClick()
                    BottomNavItem.PROFILE -> onProfileClick()
                    else -> Unit
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
        )
    }
}

@Preview(showBackground = true, widthDp = 448, heightDp = 900)
@Composable
private fun ProductListPreview() {
    CommerceXTheme {
        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            Text(
                text = "Product list loads from network at runtime",
                modifier = Modifier.align(Alignment.Center).padding(24.dp),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
