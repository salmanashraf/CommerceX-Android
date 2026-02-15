package com.sa.feature.product.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sa.core.ui.component.CategoryChip
import com.sa.core.ui.component.DiscountBadge
import com.sa.core.ui.component.QuantitySelector
import com.sa.core.ui.component.StockStatusBadge
import com.sa.core.ui.theme.*
import kotlinx.coroutines.delay

/**
 * Product Detail Screen Mockup
 *
 * US-0.4: Screen Mockups - Product Detail
 */
@Preview(
    name = "Product Detail - Light",
    showBackground = true,
    widthDp = 448,
    heightDp = 900
)
@Composable
fun ProductDetailLightPreview() {
    CommerceXTheme {
        ProductDetailMockup()
    }
}

@Preview(
    name = "Product Detail - Dark",
    showBackground = true,
    widthDp = 448,
    heightDp = 900,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ProductDetailDarkPreview() {
    CommerceXTheme {
        ProductDetailMockup()
    }
}

@Preview(
    name = "Product Detail - Added State",
    showBackground = true,
    widthDp = 448,
    heightDp = 900
)
@Composable
fun ProductDetailAddedPreview() {
    CommerceXTheme {
        ProductDetailMockup(simulateAdd = true)
    }
}

@Composable
fun ProductDetailMockup(
    simulateAdd: Boolean = false,
    onBackClick: () -> Unit = {},
    onShareClick: () -> Unit = {}
) {
    var quantity by remember { mutableStateOf(1) }
    var isAdded by remember { mutableStateOf(false) }

    LaunchedEffect(simulateAdd) {
        if (simulateAdd) {
            delay(1500)
            isAdded = true
        }
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
                .padding(bottom = 96.dp)
        ) {
            ProductImageCarousel(
                imageUrl = "https://cdn.dummyjson.com/products/2/1.jpg",
                onBackClick = onBackClick,
                onShareClick = onShareClick
            )

            Spacer(modifier = Modifier.height(Spacing.lg))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Spacing.lg),
                verticalArrangement = Arrangement.spacedBy(Spacing.md)
            ) {
                Text(
                    text = "iPhone 9",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimaryColor,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                PriceAndDiscountRow(
                    price = 549.0,
                    originalPrice = 699.0,
                    discountPercent = 21
                )

                RatingRow(rating = 4.8, reviewCount = 215)

                Row(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.md),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StockStatusBadge(inStock = true)
                    CategoryChip(
                        label = "Smartphones",
                        isSelected = true,
                        onClick = { /* No-op */ }
                    )
                }

                DescriptionSection(
                    text = "An apple mobile which is nothing like apple. " +
                        "Lightweight, fast, and built for a premium everyday experience."
                )

                QuantitySection(
                    quantity = quantity,
                    onQuantityChange = { quantity = it }
                )
            }
        }

        ProductDetailBottomBar(
            price = 549.0,
            isAdded = isAdded,
            onAddClick = { isAdded = true },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
        )
    }
}

@Composable
private fun ProductImageCarousel(
    imageUrl: String,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .background(SurfaceVariant)
    ) {
        androidx.compose.foundation.Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = "Product image",
            modifier = Modifier
                .fillMaxSize()
                .background(SurfaceVariant)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.lg),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FloatingIconButton(
                icon = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                onClick = onBackClick
            )
            FloatingIconButton(
                icon = Icons.Filled.Share,
                contentDescription = "Share",
                onClick = onShareClick
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = Spacing.md),
            horizontalArrangement = Arrangement.spacedBy(Spacing.xs)
        ) {
            repeat(4) { index ->
                Box(
                    modifier = Modifier
                        .size(if (index == 0) 10.dp else 8.dp)
                        .clip(CircleShape)
                        .background(
                            color = if (index == 0) PrimaryColor else Color.White.copy(alpha = 0.5f)
                        )
                )
            }
        }
    }
}

@Composable
private fun FloatingIconButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    Surface(
        shape = CircleShape,
        color = Color.White.copy(alpha = 0.85f),
        shadowElevation = CommerceXElevation.level1
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = TextPrimaryColor
            )
        }
    }
}

@Composable
private fun PriceAndDiscountRow(
    price: Double,
    originalPrice: Double,
    discountPercent: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Spacing.md),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$${"%.2f".format(price)}",
            style = MaterialTheme.typography.headlineSmall,
            color = PrimaryColor,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$${"%.2f".format(originalPrice)}",
            style = MaterialTheme.typography.titleMedium,
            color = TextSecondaryColor
        )
        DiscountBadge(discountPercent = discountPercent)
    }
}

@Composable
private fun RatingRow(rating: Double, reviewCount: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Spacing.xs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "Rating",
            tint = AccentColor
        )
        Text(
            text = "${"%.1f".format(rating)}",
            style = MaterialTheme.typography.labelLarge,
            color = TextPrimaryColor,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "($reviewCount)",
            style = MaterialTheme.typography.labelLarge,
            color = TextSecondaryColor
        )
    }
}

@Composable
private fun DescriptionSection(text: String) {
    Column(verticalArrangement = Arrangement.spacedBy(Spacing.xs)) {
        Text(
            text = "Description",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimaryColor
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondaryColor
        )
        Text(
            text = "Read more",
            style = MaterialTheme.typography.labelLarge,
            color = PrimaryColor,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun QuantitySection(
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(Spacing.sm)) {
        Text(
            text = "Quantity",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimaryColor
        )
        QuantitySelector(
            quantity = quantity,
            onQuantityChange = onQuantityChange,
            modifier = Modifier.width(160.dp)
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ProductDetailBottomBar(
    price: Double,
    isAdded: Boolean,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = SurfaceColor,
        tonalElevation = CommerceXElevation.level3,
        shadowElevation = CommerceXElevation.level3
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.lg)
        ) {
            Button(
                onClick = onAddClick,
                modifier = Modifier.fillMaxWidth(),
                shape = CustomShapes.md,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = CommerceXElevation.level2,
                    pressedElevation = CommerceXElevation.level1
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(brush = CommerceXGradients.buttonGradient, shape = CustomShapes.md)
                        .padding(vertical = Spacing.md),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedContent(
                        targetState = isAdded,
                        transitionSpec = { fadeIn(tween(150)) with fadeOut(tween(150)) },
                        label = "add_to_cart_state"
                    ) { added ->
                        if (added) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = "Added",
                                    tint = Color.White
                                )
                                Text(
                                    text = "Added to Cart",
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        } else {
                            Text(
                                text = "Add to Cart - $${"%.2f".format(price)}",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

