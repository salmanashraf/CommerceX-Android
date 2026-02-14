package com.sa.commercex.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sa.core.ui.component.Badge
import com.sa.core.ui.theme.*

/**
 * Glassmorphic App Bar Component
 *
 * Features:
 * - White/80% opacity background
 * - Backdrop blur effect
 * - Bottom border
 * - Search and cart icons with badge
 * - Gradient text for app name
 */
@Composable
fun GlassmorphicAppBar(
    title: String,
    cartItemCount: Int = 0,
    onSearchClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        color = Color.White.copy(alpha = 0.8f),
        shadowElevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = 20.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
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
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.background(
                        brush = CommerceXGradients.primaryGradient
                    )
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
                                modifier = Modifier.align(Alignment.TopEnd)
                            )
                        }
                    }
                }
            }

            // Bottom border
            Divider(
                modifier = Modifier.align(Alignment.BottomCenter),
                thickness = 1.dp,
                color = DividerColor
            )
        }
    }
}

/**
 * Bottom Navigation Bar Component
 *
 * Features:
 * - Fixed bottom navigation
 * - Home, Search, Cart (with badge), Profile icons
 * - Active state uses primary purple color
 * - Material 3 NavigationBar style
 */
@Composable
fun BottomNavigationBar(
    selectedItem: BottomNavItem,
    cartItemCount: Int = 0,
    onItemSelected: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = SurfaceColor,
        tonalElevation = CommerceXElevation.level2
    ) {
        BottomNavItem.values().forEach { item ->
            NavigationBarItem(
                icon = {
                    Box {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label
                        )
                        if (item == BottomNavItem.CART && cartItemCount > 0) {
                            Badge(
                                count = cartItemCount,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .offset(x = 8.dp, y = (-4).dp)
                            )
                        }
                    }
                },
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                selected = selectedItem == item,
                onClick = { onItemSelected(item) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryColor,
                    selectedTextColor = PrimaryColor,
                    indicatorColor = PrimaryLight,
                    unselectedIconColor = TextSecondaryColor,
                    unselectedTextColor = TextSecondaryColor
                )
            )
        }
    }
}

/**
 * Bottom Navigation Items Enum
 */
enum class BottomNavItem(val label: String, val icon: ImageVector) {
    HOME("Home", Icons.Filled.Home),
    SEARCH("Search", Icons.Filled.Search),
    CART("Cart", Icons.Filled.ShoppingCart),
    PROFILE("Profile", Icons.Filled.Person)
}

/**
 * Simple Top App Bar with Back Button
 *
 * Features:
 * - Back arrow navigation
 * - Title
 * - Optional action buttons
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = actions,
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = SurfaceColor,
            titleContentColor = TextPrimaryColor,
            navigationIconContentColor = TextPrimaryColor
        )
    )
}

