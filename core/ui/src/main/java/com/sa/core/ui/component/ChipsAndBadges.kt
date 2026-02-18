package com.sa.core.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sa.core.ui.theme.*

/**
 * Category Chip Component
 *
 * Features:
 * - Pill-shaped chips
 * - Animated selection state with spring physics
 * - Primary purple for selected state
 * - Horizontal scrollable list
 */
@Composable
fun CategoryChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) PrimaryColor else SurfaceVariant,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "chip_background"
    )

    val textColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else TextPrimaryColor,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "chip_text"
    )

    Box(
        modifier = modifier
            .clip(CustomShapes.full)
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = Spacing.lg, vertical = Spacing.sm),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = textColor,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium
        )
    }
}

/**
 * Category Chips Row Component
 *
 * Features:
 * - Horizontal scrollable row of category chips
 * - Animated pill indicator on selection (spring animation)
 * - Default categories: All, Electronics, Jewelry, Men's Clothing, Women's Clothing
 */
@Composable
fun CategoryChipsRow(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
        contentPadding = PaddingValues(horizontal = Spacing.lg)
    ) {
        items(categories) { category ->
            CategoryChip(
                label = category,
                isSelected = category == selectedCategory,
                onClick = { onCategorySelected(category) }
            )
        }
    }
}

/**
 * Badge Component
 *
 * Features:
 * - Small circular badge with count
 * - Used for cart count and notifications
 * - Red/accent background
 * - White text
 * - Scales with animation when count changes
 */
@Composable
fun Badge(
    count: Int,
    modifier: Modifier = Modifier,
    backgroundColor: Color = SaleColor,
    textColor: Color = Color.White
) {
    if (count > 0) {
        var pulseTarget by remember { mutableStateOf(1f) }
        val animatedScale by animateFloatAsState(
            targetValue = pulseTarget,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessMedium
            ),
            label = "badge_scale"
        )

        LaunchedEffect(count) {
            pulseTarget = 1.25f
            kotlinx.coroutines.delay(120)
            pulseTarget = 1f
        }

        Box(
            modifier = modifier
                .scale(animatedScale)
                .size(20.dp)
                .background(
                    color = backgroundColor,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (count > 99) "99+" else count.toString(),
                style = MaterialTheme.typography.labelSmall,
                color = textColor,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp
            )
        }
    }
}

/**
 * Notification Badge Component
 *
 * Features:
 * - Similar to Badge but with different styling
 * - Used for notification counts
 * - Primary purple background
 */
@Composable
fun NotificationBadge(
    count: Int,
    modifier: Modifier = Modifier
) {
    Badge(
        count = count,
        modifier = modifier,
        backgroundColor = PrimaryColor,
        textColor = Color.White
    )
}

/**
 * Discount Badge Component
 *
 * Features:
 * - Shows discount percentage
 * - Red background
 * - Used on product cards
 */
@Composable
fun DiscountBadge(
    discountPercent: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = SaleColor,
                shape = CustomShapes.sm
            )
            .padding(horizontal = Spacing.sm, vertical = Spacing.xs),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "-$discountPercent%",
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * Stock Status Badge Component
 *
 * Features:
 * - Shows in stock/out of stock status
 * - Green dot for in stock
 * - Red dot for out of stock
 */
@Composable
fun StockStatusBadge(
    inStock: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.xs)
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(
                    color = if (inStock) SuccessColor else SaleColor,
                    shape = CircleShape
                )
        )
        Text(
            text = if (inStock) "In Stock" else "Out of Stock",
            style = MaterialTheme.typography.labelMedium,
            color = if (inStock) SuccessColor else SaleColor,
            fontWeight = FontWeight.Medium
        )
    }
}
