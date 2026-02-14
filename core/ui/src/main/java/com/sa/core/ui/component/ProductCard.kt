package com.sa.core.ui.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sa.core.ui.theme.*

/**
 * Product Card Component - Grid Variant
 *
 * A card component displaying product information in a grid layout.
 * Features:
 * - Square aspect ratio product image
 * - Hover scale animation (1.05x)
 * - Discount badge (top-left, red)
 * - Wishlist heart icon (top-right)
 * - Title with 2-line clamp
 * - Price with strikethrough original price
 * - Star rating with orange star
 */
@Composable
fun ProductCard(
    imageUrl: String,
    title: String,
    price: Double,
    originalPrice: Double? = null,
    discountPercent: Int? = null,
    rating: Double,
    reviewCount: Int,
    isWishlisted: Boolean = false,
    onWishlistClick: () -> Unit = {},
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isHovered by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.05f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .scale(scale),
        shape = CustomShapes.md,
        elevation = CardDefaults.cardElevation(
            defaultElevation = CommerceXElevation.level1
        ),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceColor
        )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                // Product Image with aspect ratio 1:1
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    // Discount Badge (top-left)
                    if (discountPercent != null && discountPercent > 0) {
                        Box(
                            modifier = Modifier
                                .padding(Spacing.sm)
                                .background(
                                    color = SaleColor,
                                    shape = CustomShapes.sm
                                )
                                .padding(horizontal = Spacing.sm, vertical = Spacing.xs)
                                .align(Alignment.TopStart)
                        ) {
                            Text(
                                text = "-$discountPercent%",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Wishlist Heart (top-right)
                    IconButton(
                        onClick = onWishlistClick,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(Spacing.xs)
                            .size(32.dp)
                            .background(
                                color = Color.White.copy(alpha = 0.9f),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = if (isWishlisted) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = if (isWishlisted) "Remove from wishlist" else "Add to wishlist",
                            tint = if (isWishlisted) SaleColor else TextSecondaryColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                // Product Details
                Column(
                    modifier = Modifier.padding(Spacing.md)
                ) {
                    // Title (2-line clamp)
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        color = TextPrimaryColor,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(Spacing.xs))

                    // Price
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(Spacing.sm)
                    ) {
                        Text(
                            text = "$${"%.2f".format(price)}",
                            style = MaterialTheme.typography.titleLarge,
                            color = PrimaryColor,
                            fontWeight = FontWeight.Bold
                        )

                        if (originalPrice != null && originalPrice > price) {
                            Text(
                                text = "$${"%.2f".format(originalPrice)}",
                                style = MaterialTheme.typography.bodySmall,
                                color = TextSecondaryColor,
                                textDecoration = TextDecoration.LineThrough
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(Spacing.xs))

                    // Rating
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(Spacing.xs)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = AccentColor,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "%.1f".format(rating),
                            style = MaterialTheme.typography.labelMedium,
                            color = TextPrimaryColor,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "($reviewCount)",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondaryColor
                        )
                    }
                }
            }
        }
    }
}

