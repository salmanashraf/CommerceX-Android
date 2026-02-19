package com.sa.feature.product.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sa.core.ui.component.Badge
import com.sa.core.ui.component.CategoryChip
import com.sa.core.ui.component.GenericErrorState
import com.sa.core.ui.component.QuantitySelector
import com.sa.core.ui.component.SimpleTopAppBar
import com.sa.core.ui.component.StockStatusBadge
import com.sa.core.ui.theme.CommerceXGradients
import com.sa.core.ui.theme.CustomShapes
import com.sa.core.ui.theme.PrimaryColor
import com.sa.core.ui.theme.Spacing
import com.sa.core.ui.theme.SurfaceColor
import com.sa.core.ui.theme.SurfaceVariant
import com.sa.core.ui.theme.TextPrimaryColor
import com.sa.core.ui.theme.TextSecondaryColor
import com.sa.feature.cart.domain.model.CartProductPayload
import com.sa.feature.cart.ui.CartEvent
import com.sa.feature.cart.ui.CartViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ProductDetailRoute(
    productId: Int,
    onBackClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    cartViewModel: CartViewModel = koinViewModel()
) {
    val viewModel: ProductDetailViewModel = koinViewModel(
        key = "product_detail_$productId",
        parameters = { parametersOf(productId) }
    )
    val uiState by viewModel.uiState.collectAsState()
    val cartState by cartViewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        cartViewModel.events.collect { event ->
            if (event is CartEvent.Message) {
                snackbarHostState.showSnackbar(
                    message = event.message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.errorMessage != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    GenericErrorState(onRetry = viewModel::loadProductDetail)
                }
            }

            uiState.product != null -> {
                val product = uiState.product ?: return@Scaffold
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(MaterialTheme.colorScheme.background)
                        .statusBarsPadding()
                ) {
                    SimpleTopAppBar(
                        title = "Product Details",
                        onBackClick = onBackClick,
                        actions = {
                            IconButton(onClick = onShareClick) {
                                Icon(
                                    imageVector = Icons.Filled.Share,
                                    contentDescription = "Share"
                                )
                            }
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

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = 88.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(product.thumbnailUrl),
                            contentDescription = product.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(280.dp)
                                .background(SurfaceVariant),
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Spacing.lg),
                            verticalArrangement = Arrangement.spacedBy(Spacing.md)
                        ) {
                            Text(
                                text = product.title,
                                style = MaterialTheme.typography.headlineSmall,
                                color = TextPrimaryColor,
                                fontWeight = FontWeight.Bold
                            )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(Spacing.md)
                        ) {
                            Text(
                                text = "$${"%.2f".format(product.price)}",
                                style = MaterialTheme.typography.headlineSmall,
                                color = PrimaryColor,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "-${"%.0f".format(product.discountPercent)}%",
                                style = MaterialTheme.typography.labelLarge,
                                color = Color.White,
                                modifier = Modifier
                                    .background(PrimaryColor, CustomShapes.sm)
                                    .padding(horizontal = Spacing.sm, vertical = Spacing.xs)
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(Spacing.md)
                        ) {
                            Text(
                                text = "★ ${"%.1f".format(product.rating)}",
                                color = TextPrimaryColor,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            StockStatusBadge(inStock = product.stock > 0)
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(Spacing.sm)) {
                            CategoryChip(label = product.category, isSelected = true, onClick = {})
                            CategoryChip(label = product.brand, isSelected = false, onClick = {})
                        }

                        Text(
                            text = "Description",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextPrimaryColor,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = product.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondaryColor
                        )

                            Spacer(modifier = Modifier.height(Spacing.sm))
                            QuantitySelector(
                                quantity = uiState.quantity,
                                onQuantityChange = viewModel::onQuantityChange,
                                modifier = Modifier.width(180.dp)
                            )
                        }
                    }

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .navigationBarsPadding(),
                        color = SurfaceColor
                    ) {
                        Button(
                            onClick = {
                                cartViewModel.addToCart(
                                    CartProductPayload(
                                        productId = product.id,
                                        title = product.title,
                                        thumbnailUrl = product.thumbnailUrl,
                                        price = product.price,
                                        discountPercent = product.discountPercent,
                                        quantity = uiState.quantity
                                    )
                                )
                                viewModel.onAddToCart()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Spacing.lg),
                            shape = CustomShapes.md,
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(brush = CommerceXGradients.buttonGradient, shape = CustomShapes.md)
                                    .padding(vertical = Spacing.md),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (uiState.isAddedToCart) "Added to Cart" else "Add to Cart",
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
