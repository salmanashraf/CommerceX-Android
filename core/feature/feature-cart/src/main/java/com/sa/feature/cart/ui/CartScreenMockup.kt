package com.sa.feature.cart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sa.core.ui.component.CartItemCard
import com.sa.core.ui.component.EmptyCartState
import com.sa.core.ui.component.OrderSummary
import com.sa.core.ui.component.PrimaryButton
import com.sa.core.ui.component.SimpleTopAppBar
import com.sa.core.ui.theme.*

/**
 * Cart Screen Mockup
 *
 * US-0.5: Screen Mockups - Shopping Cart
 */
@Preview(
    name = "Cart Screen - Items",
    showBackground = true,
    widthDp = 448,
    heightDp = 900
)
@Composable
fun CartScreenPreview() {
    CommerceXTheme {
        CartScreenMockup()
    }
}

@Preview(
    name = "Cart Screen - Empty",
    showBackground = true,
    widthDp = 448,
    heightDp = 900
)
@Composable
fun CartScreenEmptyPreview() {
    CommerceXTheme {
        CartScreenEmptyMockup()
    }
}

@Composable
fun CartScreenMockup(
    onBackClick: () -> Unit = {}
) {
    val cartItems = remember {
        listOf(
            CartMockItem("iPhone 9", 549.0, 1),
            CartMockItem("MacBook Pro", 1749.0, 1),
            CartMockItem("Brown Perfume", 40.0, 2)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = Spacing.lg)
                .padding(bottom = 140.dp)
        ) {
            SimpleTopAppBar(
                title = "My Cart",
                onBackClick = onBackClick,
                actions = {
                    TextButton(onClick = { /* Clear all */ }) {
                        Text(
                            text = "Clear All",
                            color = SaleColor,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(Spacing.lg))

            Column(verticalArrangement = Arrangement.spacedBy(Spacing.md)) {
                cartItems.forEach { item ->
                    CartItemCard(
                        imageUrl = "https://cdn.dummyjson.com/products/1/thumbnail.jpg",
                        title = item.title,
                        price = item.price,
                        quantity = item.quantity,
                        onQuantityChange = { /* Quantity change */ },
                        onDeleteClick = { /* Delete */ }
                    )
                }
            }
        }

        CartSummaryBar(
            subtotal = cartItems.sumOf { it.price * it.quantity },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
        )
    }
}

@Composable
fun CartScreenEmptyMockup() {
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
            EmptyCartState(onStartShopping = { /* Navigate to home */ })
        }
    }
}

@Composable
private fun CartSummaryBar(
    subtotal: Double,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = SurfaceColor,
        tonalElevation = CommerceXElevation.level3,
        shadowElevation = CommerceXElevation.level3
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.lg),
            verticalArrangement = Arrangement.spacedBy(Spacing.md)
        ) {
            OrderSummary(subtotal = subtotal)

            PrimaryButton(
                text = "Proceed to Checkout",
                onClick = { /* Checkout action */ },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

private data class CartMockItem(
    val title: String,
    val price: Double,
    val quantity: Int
)
