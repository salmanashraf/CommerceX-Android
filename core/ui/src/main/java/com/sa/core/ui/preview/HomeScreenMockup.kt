package com.sa.core.ui.preview

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sa.core.ui.component.*
import com.sa.core.ui.theme.*

/**
 * Home Screen Mockup - Light Mode
 *
 * US-0.3: Screen Mockups - Home & Product List
 *
 * Features:
 * - Sticky glassmorphic header
 * - Hero banner with gradient
 * - Category chips (horizontal scrollable)
 * - 2-column product grid
 * - Fixed bottom navigation
 * - Staggered fade-up animations
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
fun HomeScreenMockup() {
    var selectedCategory by remember { mutableStateOf("All") }
    var cartItemCount by remember { mutableStateOf(3) }
    var selectedNavItem by remember { mutableStateOf<BottomNavItem>(BottomNavItem.HOME) }

    // Sample product data for mockup
    val products = remember {
        listOf(
            MockProduct("iPhone 9", 549.0, 699.0, 12, 4.69, 94),
            MockProduct("Samsung Universe", 1249.0, 1549.0, 19, 4.09, 36),
            MockProduct("OPPOF19", 280.0, null, null, 4.3, 123),
            MockProduct("Huawei P30", 499.0, 699.0, 28, 4.09, 258),
            MockProduct("MacBook Pro", 1749.0, 1999.0, 12, 4.57, 70),
            MockProduct("Samsung Galaxy", 899.0, null, null, 4.8, 145),
            MockProduct("Perfume Oil", 13.0, null, null, 4.26, 65),
            MockProduct("Brown Perfume", 40.0, 55.0, 27, 4.0, 52)
        )
    }

    val categories = listOf("All", "Electronics", "Jewelry", "Men's Clothing", "Women's Clothing")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Glassmorphic Header
            GlassmorphicHeader(
                cartItemCount = cartItemCount,
                onSearchClick = { /* Search action */ },
                onCartClick = { /* Cart action */ }
            )

            // Hero Banner
            HeroBanner()

            // Category Chips
            Spacer(modifier = Modifier.height(Spacing.lg))
            CategoryChipsRow(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it }
            )

            Spacer(modifier = Modifier.height(Spacing.lg))

            // Product Grid with staggered animation
            ProductGridWithAnimation(
                products = products,
                modifier = Modifier.weight(1f)
            )
        }

        // Fixed Bottom Navigation
        BottomNavigationBar(
            selectedItem = selectedNavItem,
            cartItemCount = cartItemCount,
            onItemSelected = { selectedNavItem = it },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

/**
 * Glassmorphic Header Component
 *
 * Features:
 * - White 80% opacity background
 * - Backdrop blur effect
 * - Gradient text logo
 * - Search and cart icons
 * - Bottom border
 */
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
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Spacing.lg),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // App Title with Gradient
                Text(
                    text = "CommerceX",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.sm)
                ) {
                    // Search Icon
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = TextPrimaryColor
                        )
                    }

                    // Cart Icon with Badge
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

            // Bottom border
            HorizontalDivider(
                modifier = Modifier.align(Alignment.BottomCenter),
                thickness = 1.dp,
                color = DividerColor
            )
        }
    }
}

/**
 * Hero Banner Component
 *
 * Features:
 * - Purple to Orange gradient background
 * - "Summer Collection / Up to 50% OFF" text
 * - Decorative translucent circles
 */
@Composable
fun HeroBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(
                brush = CommerceXGradients.heroGradient
            )
    ) {
        // Decorative circles
        Box(
            modifier = Modifier
                .size(120.dp)
                .offset(x = (-20).dp, y = 20.dp)
                .background(
                    color = Color.White.copy(alpha = 0.1f),
                    shape = CircleShape
                )
        )

        Box(
            modifier = Modifier
                .size(80.dp)
                .offset(x = 320.dp, y = 100.dp)
                .background(
                    color = Color.White.copy(alpha = 0.15f),
                    shape = CircleShape
                )
        )

        // Banner content
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

/**
 * Product Grid with Staggered Fade-Up Animation
 *
 * Features:
 * - 2-column grid layout
 * - Staggered entrance animations
 * - Fade-up effect with delay per item
 */
@Composable
fun ProductGridWithAnimation(
    products: List<MockProduct>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.lg),
        horizontalArrangement = Arrangement.spacedBy(Spacing.md),
        verticalArrangement = Arrangement.spacedBy(Spacing.lg),
        contentPadding = PaddingValues(bottom = 80.dp) // Space for bottom nav
    ) {
        itemsIndexed(products) { index, product ->
            AnimatedProductCard(
                product = product,
                index = index
            )
        }
    }
}

/**
 * Animated Product Card with staggered entrance
 */
@Composable
fun AnimatedProductCard(
    product: MockProduct,
    index: Int
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(index * 50L) // 50ms delay per item
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
            onWishlistClick = { /* Wishlist action */ },
            onClick = { /* Navigate to detail */ }
        )
    }
}

/**
 * Mock Product Data Class
 */
data class MockProduct(
    val title: String,
    val price: Double,
    val originalPrice: Double?,
    val discountPercent: Int?,
    val rating: Double,
    val reviewCount: Int
)

