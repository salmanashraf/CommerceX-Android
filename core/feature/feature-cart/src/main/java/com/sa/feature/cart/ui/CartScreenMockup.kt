package com.sa.feature.cart.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.core.tween
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
import com.sa.core.ui.component.OrderSummary
import com.sa.core.ui.component.PrimaryButton
import com.sa.core.ui.component.SimpleTopAppBar
import com.sa.core.ui.theme.*
import kotlinx.coroutines.delay

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
        mutableStateListOf(
            CartMockItem(id = "1", title = "iPhone 9", price = 549.0, quantity = 1),
            CartMockItem(id = "2", title = "MacBook Pro", price = 1749.0, quantity = 1),
            CartMockItem(id = "3", title = "Brown Perfume", price = 40.0, quantity = 2)
        )
    }
    val removingItemIds = remember { mutableStateListOf<String>() }

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
                    AnimatedVisibility(
                        visible = !removingItemIds.contains(item.id),
                        exit = slideOutHorizontally(
                            animationSpec = tween(240),
                            targetOffsetX = { -it }
                        ) + fadeOut(animationSpec = tween(220)) + shrinkVertically(animationSpec = tween(220))
                    ) {
                        CartItemCard(
                            imageUrl = "https://cdn.dummyjson.com/products/1/thumbnail.jpg",
                            title = item.title,
                            price = item.price,
                            quantity = item.quantity,
                            onQuantityChange = { newQty ->
                                val index = cartItems.indexOfFirst { it.id == item.id }
                                if (index >= 0) {
                                    cartItems[index] = cartItems[index].copy(quantity = newQty)
                                }
                            },
                            onDeleteClick = {
                                if (!removingItemIds.contains(item.id)) {
                                    removingItemIds.add(item.id)
                                }
                            }
                        )
                    }
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

    LaunchedEffect(removingItemIds.size) {
        if (removingItemIds.isNotEmpty()) {
            val id = removingItemIds.first()
            delay(240)
            cartItems.removeAll { it.id == id }
            removingItemIds.remove(id)
        }
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
                .fillMaxSize()
                .padding(horizontal = Spacing.lg),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(SurfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Empty cart",
                    tint = TextSecondaryColor,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.height(Spacing.lg))

            Text(
                text = "Your cart is empty",
                style = MaterialTheme.typography.titleLarge,
                color = TextPrimaryColor,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(Spacing.sm))

            Text(
                text = "Looks like you have not added anything yet.",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondaryColor,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(Spacing.lg))

            PrimaryButton(
                text = "Start Shopping",
                onClick = { /* Navigate to home */ }
            )
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
    val id: String,
    val title: String,
    val price: Double,
    val quantity: Int
)
