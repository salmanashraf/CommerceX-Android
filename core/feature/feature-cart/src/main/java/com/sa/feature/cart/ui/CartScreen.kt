package com.sa.feature.cart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.sa.core.ui.component.CartItemCard
import com.sa.core.ui.component.EmptyCartState
import com.sa.core.ui.component.OrderSummary
import com.sa.core.ui.component.PrimaryButton
import com.sa.core.ui.component.SimpleTopAppBar
import com.sa.core.ui.theme.CommerceXElevation
import com.sa.core.ui.theme.Spacing
import com.sa.core.ui.theme.SurfaceColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun CartRoute(
    onBackClick: () -> Unit = {},
    onContinueShopping: () -> Unit = {},
    viewModel: CartViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarScope = rememberCoroutineScope()
    val density = LocalDensity.current
    var summaryHeightPx by remember { mutableIntStateOf(0) }
    val listBottomPadding = with(density) { summaryHeightPx.toDp() + Spacing.lg }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is CartEvent.Message -> {
                    snackbarHostState.showSnackbar(message = event.message, duration = SnackbarDuration.Short)
                }
                is CartEvent.ItemRemoved -> {
                    val dismissJob = snackbarScope.launch {
                        delay(5000)
                        snackbarHostState.currentSnackbarData?.dismiss()
                    }
                    val result = snackbarHostState.showSnackbar(
                        message = "Item removed",
                        actionLabel = "Undo",
                        duration = SnackbarDuration.Indefinite
                    )
                    dismissJob.cancel()
                    if (result == androidx.compose.material3.SnackbarResult.ActionPerformed) {
                        viewModel.undoRemove(event.item)
                    }
                }
                is CartEvent.CheckoutCompleted -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = SurfaceColor,
        topBar = {
            SimpleTopAppBar(
                title = "My Cart",
                onBackClick = onBackClick,
                actions = {
                    if (uiState.items.isNotEmpty()) {
                        TextButton(onClick = viewModel::clearCart) {
                            Text("Clear All")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        if (uiState.items.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .statusBarsPadding(),
                contentAlignment = Alignment.Center
            ) {
                EmptyCartState(onStartShopping = onContinueShopping)
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = Spacing.lg),
                    verticalArrangement = Arrangement.spacedBy(Spacing.md),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(
                        top = Spacing.md,
                        bottom = listBottomPadding
                    )
                ) {
                    items(uiState.items, key = { it.productId }) { item ->
                        CartItemCard(
                            imageUrl = item.thumbnailUrl,
                            title = item.title,
                            price = item.unitPrice,
                            originalPrice = item.price.takeIf { item.discountPercent > 0 },
                            discountPercent = item.discountPercent,
                            quantity = item.quantity,
                            onQuantityChange = { qty -> viewModel.updateQuantity(item.productId, qty) },
                            onDeleteClick = { viewModel.removeItem(item) }
                        )
                    }
                }

                androidx.compose.material3.Surface(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .navigationBarsPadding()
                        .onSizeChanged { summaryHeightPx = it.height }
                        .fillMaxWidth(),
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
                        OrderSummary(subtotal = uiState.totalAmount, discountPercent = 0)
                        PrimaryButton(
                            text = "Proceed to Checkout",
                            onClick = viewModel::checkout,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
