package com.sa.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sa.core.ui.theme.*

/**
 * Cart Item Card Component
 *
 * Features:
 * - 80px square thumbnail
 * - Product title
 * - Price display
 * - Quantity controls (+/-)
 * - Delete/trash icon
 * - Slide-left exit animation on remove
 */
@Composable
fun CartItemCard(
    imageUrl: String,
    title: String,
    price: Double,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = CustomShapes.md,
        colors = CardDefaults.cardColors(
            containerColor = SurfaceColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = CommerceXElevation.level1
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.md),
            horizontalArrangement = Arrangement.spacedBy(Spacing.md)
        ) {
            // Product Thumbnail (80px square) - Placeholder for now
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CustomShapes.sm)
                    .background(SurfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "📦",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            // Product Details
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Title
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimaryColor,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(Spacing.xs))

                // Price
                Text(
                    text = "$${"%.2f".format(price)}",
                    style = MaterialTheme.typography.titleLarge,
                    color = PrimaryColor,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(Spacing.sm))

                // Quantity Controls and Delete
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Quantity Selector (compact version)
                    QuantitySelector(
                        quantity = quantity,
                        onQuantityChange = onQuantityChange,
                        modifier = Modifier.width(120.dp)
                    )

                    // Delete Button
                    TextButton(
                        onClick = onDeleteClick,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = SaleColor
                        )
                    ) {
                        Text(
                            text = "🗑️",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
        }
    }
}

/**
 * Order Summary Component
 *
 * Features:
 * - Subtotal row
 * - Discount row (green, -10%)
 * - Shipping row (FREE)
 * - Bold total
 * - Fixed at bottom of cart screen
 */
@Composable
fun OrderSummary(
    subtotal: Double,
    discountPercent: Int = 10,
    shipping: String = "FREE",
    modifier: Modifier = Modifier
) {
    val discount = subtotal * (discountPercent / 100.0)
    val total = subtotal - discount

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = CustomShapes.md,
        colors = CardDefaults.cardColors(
            containerColor = SurfaceColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = CommerceXElevation.level3
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.lg),
            verticalArrangement = Arrangement.spacedBy(Spacing.md)
        ) {
            Text(
                text = "Order Summary",
                style = MaterialTheme.typography.titleLarge,
                color = TextPrimaryColor,
                fontWeight = FontWeight.Bold
            )

            HorizontalDivider(color = DividerColor)

            // Subtotal
            SummaryRow(
                label = "Subtotal",
                value = "$${"%.2f".format(subtotal)}",
                valueColor = TextPrimaryColor
            )

            // Discount
            SummaryRow(
                label = "Discount ($discountPercent%)",
                value = "-$${"%.2f".format(discount)}",
                valueColor = SuccessColor
            )

            // Shipping
            SummaryRow(
                label = "Shipping",
                value = shipping,
                valueColor = SuccessColor
            )

            HorizontalDivider(color = DividerColor)

            // Total
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total",
                    style = MaterialTheme.typography.titleLarge,
                    color = TextPrimaryColor,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$${"%.2f".format(total)}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = PrimaryColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun SummaryRow(
    label: String,
    value: String,
    valueColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = TextSecondaryColor
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = valueColor,
            fontWeight = FontWeight.SemiBold
        )
    }
}

