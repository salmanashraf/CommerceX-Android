package com.sa.feature.product.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sa.core.ui.component.BottomNavItem
import com.sa.core.ui.component.BottomNavigationBar
import com.sa.core.ui.component.Badge
import com.sa.core.ui.component.CategoryChipsRow
import com.sa.core.ui.component.ProductCard
import com.sa.core.ui.component.ShimmerProductGrid
import com.sa.core.ui.theme.BackgroundColor
import com.sa.core.ui.theme.CommerceXGradients
import com.sa.core.ui.theme.CommerceXTheme
import com.sa.core.ui.theme.DividerColor
import com.sa.core.ui.theme.PrimaryColor
import com.sa.core.ui.theme.Spacing
import com.sa.core.ui.theme.TextPrimaryColor
import com.sa.core.ui.theme.TextSecondaryColor
import kotlinx.coroutines.delay

/**
 * Home Screen Mockup - Light Mode
 *
 * US-0.3: Screen Mockups - Home & Product List
 */
@Preview(
    name = "Home Screen - Light Mode",
    showBackground = true,
    widthDp = 448,
    heightDp = 900
)
@Composable
fun HomeScreenLightPreview() {
    CommerceXTheme {
        HomeScreenMockup()
    }
}

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun HomeScreenMockup(
    onProductClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    var selectedCategory by remember { mutableStateOf("All") }
    var cartItemCount by remember { mutableStateOf(3) }
    var selectedNavItem by remember { mutableStateOf<BottomNavItem>(BottomNavItem.HOME) }
    var isRefreshing by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }

    val products = remember {
        listOf(
            MockProduct("iPhone 9", 549.0, 699.0, 12, 4.69, 94, "Electronics"),
            MockProduct("Samsung Universe", 1249.0, 1549.0, 19, 4.09, 36, "Electronics"),
            MockProduct("OPPOF19", 280.0, null, null, 4.3, 123, "Electronics"),
            MockProduct("Huawei P30", 499.0, 699.0, 28, 4.09, 258, "Electronics"),
            MockProduct("MacBook Pro", 1749.0, 1999.0, 12, 4.57, 70, "Electronics"),
            MockProduct("Samsung Galaxy", 899.0, null, null, 4.8, 145, "Electronics"),
            MockProduct("Perfume Oil", 13.0, null, null, 4.26, 65, "Women's Clothing"),
            MockProduct("Brown Perfume", 40.0, 55.0, 27, 4.0, 52, "Jewelry")
        )
    }

    val categories = listOf("All", "Electronics", "Jewelry", "Men's Clothing", "Women's Clothing")

    LaunchedEffect(Unit) {
        delay(900)
        isLoading = false
    }

    val refreshProducts: () -> Unit = {
        if (!isRefreshing) isRefreshing = true
    }

    LaunchedEffect(isRefreshing) {
        if (isRefreshing) {
            delay(950)
            cartItemCount += 1
            isRefreshing = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            GlassmorphicHeader(
                cartItemCount = cartItemCount,
                onSearchClick = onSearchClick,
                onCartClick = onCartClick
            )

            HeroBanner()

            Spacer(modifier = Modifier.height(Spacing.lg))
            CategoryChipsRow(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it }
            )

            PullToRefreshStrip(
                isRefreshing = isRefreshing,
                onRefresh = refreshProducts
            )

            Spacer(modifier = Modifier.height(Spacing.lg))

            Box(modifier = Modifier.weight(1f)) {
                if (isLoading) {
                    ShimmerProductGrid(modifier = Modifier.fillMaxSize())
                } else {
                    AnimatedContent(
                        targetState = selectedCategory,
                        transitionSpec = { fadeIn(tween(180)) with fadeOut(tween(180)) },
                        label = "category_layout_transition"
                    ) { targetCategory ->
                        ProductGridWithAnimation(
                            products = if (targetCategory == "All") {
                                products
                            } else {
                                products.filter { it.category == targetCategory }
                            },
                            modifier = Modifier.fillMaxSize(),
                            onProductClick = onProductClick
                        )
                    }
                }
            }
        }

        BottomNavigationBar(
            selectedItem = selectedNavItem,
            cartItemCount = cartItemCount,
            onItemSelected = { item ->
                selectedNavItem = item
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

@Composable
private fun PullToRefreshStrip(
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    var dragDistance by remember { mutableStateOf(0f) }
    val progress = (dragDistance / 150f).coerceIn(0f, 1f)
    val arrowRotation by animateFloatAsState(
        targetValue = progress * 180f,
        animationSpec = tween(durationMillis = 120),
        label = "pull_arrow_rotation"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.lg, vertical = Spacing.sm)
            .pointerInput(isRefreshing) {
                detectVerticalDragGestures(
                    onVerticalDrag = { _, dragAmount ->
                        if (!isRefreshing) {
                            dragDistance = (dragDistance + dragAmount).coerceIn(0f, 180f)
                        }
                    },
                    onDragEnd = {
                        if (!isRefreshing && progress > 0.85f) onRefresh()
                        dragDistance = 0f
                    },
                    onDragCancel = { dragDistance = 0f }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        if (isRefreshing) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp
                )
                Text(
                    text = "Refreshing products...",
                    style = MaterialTheme.typography.labelLarge,
                    color = TextSecondaryColor
                )
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.spacedBy(Spacing.xs),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (progress > 0.5f) Icons.Filled.Refresh else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Pull to refresh",
                    tint = PrimaryColor,
                    modifier = Modifier.graphicsLayer(rotationZ = arrowRotation)
                )
                Text(
                    text = if (progress > 0.85f) "Release to refresh" else "Pull down to refresh",
                    style = MaterialTheme.typography.labelLarge,
                    color = TextSecondaryColor
                )
            }
        }
    }
}

@Composable
fun GlassmorphicHeader(
    cartItemCount: Int,
    onSearchClick: () -> Unit,
    onCartClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        color = Color.White.copy(alpha = 0.8f),
        shadowElevation = 0.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Spacing.lg),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "CommerceX",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor
                )

                Row(horizontalArrangement = Arrangement.spacedBy(Spacing.sm)) {
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = TextPrimaryColor
                        )
                    }

                    Box {
                        IconButton(onClick = onCartClick) {
                            Icon(
                                imageVector = Icons.Filled.ShoppingCart,
                                contentDescription = "Cart",
                                tint = TextPrimaryColor
                            )
                        }
                        if (cartItemCount > 0) {
                            Badge(
                                count = cartItemCount,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .offset(x = 4.dp, y = 8.dp)
                            )
                        }
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.align(Alignment.BottomCenter),
                thickness = 1.dp,
                color = DividerColor
            )
        }
    }
}

@Composable
fun HeroBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(brush = CommerceXGradients.heroGradient)
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .offset(x = (-20).dp, y = 20.dp)
                .background(color = Color.White.copy(alpha = 0.1f), shape = CircleShape)
        )

        Box(
            modifier = Modifier
                .size(80.dp)
                .offset(x = 320.dp, y = 100.dp)
                .background(color = Color.White.copy(alpha = 0.15f), shape = CircleShape)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Spacing.xl),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Summer Collection",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(Spacing.xs))

            Text(
                text = "Up to 50% OFF",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White.copy(alpha = 0.95f),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun ProductGridWithAnimation(
    products: List<MockProduct>,
    modifier: Modifier = Modifier,
    onProductClick: () -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.lg),
        horizontalArrangement = Arrangement.spacedBy(Spacing.md),
        verticalArrangement = Arrangement.spacedBy(Spacing.lg),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        itemsIndexed(products) { index, product ->
            AnimatedProductCard(
                product = product,
                index = index,
                onClick = onProductClick
            )
        }
    }
}

@Composable
fun AnimatedProductCard(
    product: MockProduct,
    index: Int,
    onClick: () -> Unit = {}
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(index * 50L)
        isVisible = true
    }

    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 400),
        label = "card_alpha"
    )

    val offsetY by animateFloatAsState(
        targetValue = if (isVisible) 0f else 40f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "card_offset"
    )

    Box(
        modifier = Modifier
            .alpha(alpha)
            .offset(y = offsetY.dp)
    ) {
        ProductCard(
            imageUrl = "https://cdn.dummyjson.com/products/${index + 1}/thumbnail.jpg",
            title = product.title,
            price = product.price,
            originalPrice = product.originalPrice,
            discountPercent = product.discountPercent,
            rating = product.rating,
            reviewCount = product.reviewCount,
            isWishlisted = false,
            onWishlistClick = { },
            onClick = onClick
        )
    }
}

data class MockProduct(
    val title: String,
    val price: Double,
    val originalPrice: Double?,
    val discountPercent: Int?,
    val rating: Double,
    val reviewCount: Int,
    val category: String
)
