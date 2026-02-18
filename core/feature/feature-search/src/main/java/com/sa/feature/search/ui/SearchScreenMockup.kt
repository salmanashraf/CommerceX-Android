package com.sa.feature.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sa.core.ui.component.CategoryChip
import com.sa.core.ui.component.NoSearchResultsState
import com.sa.core.ui.component.ProductCard
import com.sa.core.ui.component.SearchBar
import com.sa.core.ui.theme.*

/**
 * Search Screen Mockup
 *
 * US-0.6: Screen Mockups - Search
 */
@Preview(
    name = "Search - Default",
    showBackground = true,
    widthDp = 448,
    heightDp = 900
)
@Composable
fun SearchScreenDefaultPreview() {
    CommerceXTheme {
        SearchScreenDefaultMockup()
    }
}

@Preview(
    name = "Search - Results",
    showBackground = true,
    widthDp = 448,
    heightDp = 900
)
@Composable
fun SearchScreenResultsPreview() {
    CommerceXTheme {
        SearchScreenResultsMockup()
    }
}

@Preview(
    name = "Search - No Results",
    showBackground = true,
    widthDp = 448,
    heightDp = 900
)
@Composable
fun SearchScreenNoResultsPreview() {
    CommerceXTheme {
        SearchScreenNoResultsMockup()
    }
}

@Composable
fun SearchScreenDefaultMockup() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Spacing.lg)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Spacing.lg)
        ) {
            SearchHeader()

            SearchBar(
                value = "",
                onValueChange = { /* No-op */ },
                onSearch = { /* No-op */ }
            )

            SectionTitle(text = "Recent Searches")
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.sm)) {
                RecentSearchRow("iphone 15 pro")
                RecentSearchRow("wireless earbuds")
                RecentSearchRow("laptop stand")
            }

            SectionTitle(text = "Trending Now")
            Row(horizontalArrangement = Arrangement.spacedBy(Spacing.sm)) {
                CategoryChip(label = "Smartphones", isSelected = false, onClick = {})
                CategoryChip(label = "Perfume", isSelected = false, onClick = {})
                CategoryChip(label = "Laptops", isSelected = false, onClick = {})
            }
        }
    }
}

@Composable
fun SearchScreenResultsMockup() {
    val products = listOf(
        MockSearchProduct("iPhone 9", 549.0, 699.0, 12, 4.69, 94),
        MockSearchProduct("MacBook Pro", 1749.0, 1999.0, 12, 4.57, 70),
        MockSearchProduct("Samsung Galaxy", 899.0, null, null, 4.8, 145),
        MockSearchProduct("Perfume Oil", 13.0, null, null, 4.26, 65)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Spacing.lg)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Spacing.lg)
        ) {
            SearchHeader()

            SearchBar(
                value = "iphone",
                onValueChange = { /* No-op */ },
                onSearch = { /* No-op */ }
            )

            Text(
                text = "4 results",
                style = MaterialTheme.typography.labelLarge,
                color = TextSecondaryColor
            )

            Column(verticalArrangement = Arrangement.spacedBy(Spacing.lg)) {
                products.forEachIndexed { index, product ->
                    ProductCard(
                        imageUrl = "https://cdn.dummyjson.com/products/${index + 1}/thumbnail.jpg",
                        title = product.title,
                        price = product.price,
                        originalPrice = product.originalPrice,
                        discountPercent = product.discountPercent,
                        rating = product.rating,
                        reviewCount = product.reviewCount,
                        isWishlisted = false,
                        onWishlistClick = { /* No-op */ },
                        onClick = { /* No-op */ }
                    )
                }
            }
        }
    }
}

@Composable
fun SearchScreenNoResultsMockup() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            NoSearchResultsState(query = "iphone")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchHeader() {
    TopAppBar(
        title = {
            Text(
                text = "Search",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* Back action */ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Back",
                    tint = TextPrimaryColor
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = SurfaceColor,
            titleContentColor = TextPrimaryColor,
            navigationIconContentColor = TextPrimaryColor
        )
    )
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = TextPrimaryColor,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
private fun RecentSearchRow(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.sm)
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Recent search",
            tint = TextSecondaryColor,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = TextPrimaryColor
        )
    }
}

private data class MockSearchProduct(
    val title: String,
    val price: Double,
    val originalPrice: Double?,
    val discountPercent: Int?,
    val rating: Double,
    val reviewCount: Int
)
