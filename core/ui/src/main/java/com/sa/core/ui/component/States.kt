package com.sa.core.ui.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sa.core.ui.theme.BackgroundColor
import com.sa.core.ui.theme.BorderColor
import com.sa.core.ui.theme.CommerceXTheme
import com.sa.core.ui.theme.ErrorColor
import com.sa.core.ui.theme.PrimaryColor
import com.sa.core.ui.theme.Spacing
import com.sa.core.ui.theme.SurfaceColor
import com.sa.core.ui.theme.SurfaceVariant
import com.sa.core.ui.theme.TextPrimaryColor
import com.sa.core.ui.theme.TextSecondaryColor

@Composable
fun EmptyCartState(
    onStartShopping: () -> Unit = {}
) {
    StateContent(
        icon = Icons.Filled.ShoppingCart,
        iconTint = TextSecondaryColor,
        iconContainerColor = SurfaceVariant,
        title = "Your cart is empty",
        message = "Looks like you have not added anything yet.",
        primaryAction = "Start Shopping",
        onPrimaryAction = onStartShopping
    )
}

@Composable
fun NoSearchResultsState(
    query: String = "headphones"
) {
    StateContent(
        icon = Icons.Filled.Search,
        iconTint = TextSecondaryColor,
        iconContainerColor = SurfaceVariant,
        title = "No results found",
        message = "No products matched \"$query\". Try a different keyword."
    )
}

@Composable
fun NoProductsAvailableState(
    onRefresh: () -> Unit = {}
) {
    StateContent(
        icon = Icons.Filled.Inventory2,
        iconTint = TextSecondaryColor,
        iconContainerColor = SurfaceVariant,
        title = "No products available",
        message = "There are no products to show right now.",
        primaryAction = "Refresh",
        onPrimaryAction = onRefresh
    )
}

@Composable
fun OfflineState(
    onRetry: () -> Unit = {}
) {
    StateContent(
        icon = Icons.Filled.CloudOff,
        iconTint = PrimaryColor,
        iconContainerColor = PrimaryColor.copy(alpha = 0.12f),
        title = "You're offline",
        message = "Check your connection and try again.",
        primaryAction = "Retry",
        onPrimaryAction = onRetry
    )
}

@Composable
fun EmptyWishlistState(
    onDiscover: () -> Unit = {}
) {
    StateContent(
        icon = Icons.Filled.FavoriteBorder,
        iconTint = PrimaryColor,
        iconContainerColor = PrimaryColor.copy(alpha = 0.12f),
        title = "Your wishlist is empty",
        message = "Save products you love and find them here instantly.",
        primaryAction = "Discover Products",
        onPrimaryAction = onDiscover
    )
}

@Composable
fun GenericErrorState(
    onRetry: () -> Unit = {}
) {
    StateContent(
        icon = Icons.Filled.ErrorOutline,
        iconTint = ErrorColor,
        iconContainerColor = ErrorColor.copy(alpha = 0.12f),
        title = "Something went wrong",
        message = "We couldn't load this content. Please try again.",
        primaryAction = "Try Again",
        onPrimaryAction = onRetry
    )
}

@Composable
fun PullToRefreshIndicator(
    isRefreshing: Boolean,
    progress: Float,
    modifier: Modifier = Modifier
) {
    val normalizedProgress = progress.coerceIn(0f, 1f)
    val arrowScale by animateFloatAsState(
        targetValue = if (isRefreshing) 0f else (0.8f + normalizedProgress * 0.4f),
        animationSpec = spring(),
        label = "pull_scale"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Spacing.sm),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isRefreshing) {
            CircularProgressIndicator(
                modifier = Modifier.size(18.dp),
                strokeWidth = 2.dp
            )
            Text(
                text = "Refreshing...",
                modifier = Modifier.padding(start = Spacing.sm),
                color = TextSecondaryColor,
                style = MaterialTheme.typography.labelLarge
            )
        } else {
            Icon(
                imageVector = if (normalizedProgress > 0.8f) Icons.Filled.Refresh else Icons.Filled.KeyboardArrowDown,
                contentDescription = "Pull to refresh",
                modifier = Modifier.scale(arrowScale),
                tint = PrimaryColor
            )
            Text(
                text = if (normalizedProgress > 0.8f) "Release to refresh" else "Pull to refresh",
                modifier = Modifier.padding(start = Spacing.xs),
                color = TextSecondaryColor,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
fun LoadingProductGridState(
    modifier: Modifier = Modifier,
    itemCount: Int = 6
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Spacing.md),
        verticalArrangement = Arrangement.spacedBy(Spacing.lg),
        contentPadding = PaddingValues(horizontal = Spacing.lg, vertical = Spacing.sm)
    ) {
        items((1..itemCount).toList()) {
            LoadingProductCard()
        }
    }
}

@Composable
private fun LoadingProductCard() {
    val shimmer = rememberShimmerBrush()
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(colors.surface)
            .padding(Spacing.md),
        verticalArrangement = Arrangement.spacedBy(Spacing.sm)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(shimmer)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(14.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(shimmer)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(14.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(shimmer)
        )
    }
}

@Composable
private fun rememberShimmerBrush(): Brush {
    val colors = MaterialTheme.colorScheme
    val transition = rememberInfiniteTransition(label = "state_shimmer")
    val xProgress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_progress"
    )

    return Brush.linearGradient(
        colors = listOf(
            colors.surfaceVariant,
            colors.surface,
            colors.surfaceVariant
        ),
        start = Offset(x = xProgress * 900f - 450f, y = 0f),
        end = Offset(x = xProgress * 900f, y = 280f)
    )
}

@Composable
private fun StateContent(
    icon: ImageVector,
    iconTint: Color,
    iconContainerColor: Color,
    title: String,
    message: String,
    primaryAction: String? = null,
    onPrimaryAction: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(iconContainerColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = iconTint,
                modifier = Modifier.size(52.dp)
            )
        }

        Text(
            text = title,
            modifier = Modifier.padding(top = Spacing.lg),
            style = MaterialTheme.typography.titleLarge,
            color = TextPrimaryColor,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = message,
            modifier = Modifier.padding(top = Spacing.sm),
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondaryColor,
            textAlign = TextAlign.Center
        )

        if (primaryAction != null) {
            PrimaryButton(
                text = primaryAction,
                onClick = onPrimaryAction,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Spacing.lg)
            )
        }
    }
}

@Preview(name = "State - Empty Cart", showBackground = true, widthDp = 448, heightDp = 900)
@Composable
private fun EmptyCartStatePreview() {
    CommerceXTheme {
        Box(modifier = Modifier.background(BackgroundColor).padding(top = 180.dp)) {
            EmptyCartState()
        }
    }
}

@Preview(name = "State - Search Empty", showBackground = true, widthDp = 448, heightDp = 900)
@Composable
private fun SearchEmptyStatePreview() {
    CommerceXTheme {
        Box(modifier = Modifier.background(BackgroundColor).padding(top = 180.dp)) {
            NoSearchResultsState()
        }
    }
}

@Preview(name = "State - Offline", showBackground = true, widthDp = 448, heightDp = 900)
@Composable
private fun OfflineStatePreview() {
    CommerceXTheme {
        Box(modifier = Modifier.background(BackgroundColor).padding(top = 180.dp)) {
            OfflineState()
        }
    }
}

@Preview(name = "State - Generic Error", showBackground = true, widthDp = 448, heightDp = 900)
@Composable
private fun GenericErrorPreview() {
    CommerceXTheme {
        Box(modifier = Modifier.background(BackgroundColor).padding(top = 180.dp)) {
            GenericErrorState()
        }
    }
}

@Preview(name = "State - Wishlist Empty", showBackground = true, widthDp = 448, heightDp = 900)
@Composable
private fun WishlistEmptyPreview() {
    CommerceXTheme {
        Box(modifier = Modifier.background(BackgroundColor).padding(top = 180.dp)) {
            EmptyWishlistState()
        }
    }
}

@Preview(name = "State - Loading Products", showBackground = true, widthDp = 448, heightDp = 900)
@Composable
private fun LoadingStatePreview() {
    CommerceXTheme {
        Box(modifier = Modifier.background(BackgroundColor)) {
            LoadingProductGridState()
        }
    }
}

@Preview(name = "State - Pull To Refresh", showBackground = true, widthDp = 448, heightDp = 160)
@Composable
private fun PullToRefreshPreview() {
    CommerceXTheme {
        Column(modifier = Modifier.background(BackgroundColor)) {
            PullToRefreshIndicator(isRefreshing = false, progress = 0.35f)
            PullToRefreshIndicator(isRefreshing = false, progress = 0.9f)
            PullToRefreshIndicator(isRefreshing = true, progress = 1f)
        }
    }
}
