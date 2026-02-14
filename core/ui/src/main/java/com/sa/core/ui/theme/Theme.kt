package com.sa.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.sa.core.ui.theme.CommerceXTypography
import com.sa.core.ui.theme.DarkColorScheme
import com.sa.core.ui.theme.LightColorScheme

// ============================================================================
// COMMERCEX THEME COMPOSABLE
// ============================================================================

@Composable
fun CommerceXTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (useDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = CommerceXTypography,
        shapes = CommerceXShapes,
        content = {
            Surface(
                color = MaterialTheme.colorScheme.background,
                content = content
            )
        }
    )
}

// ============================================================================
// USAGE IN ACTIVITIES/SCREENS
// ============================================================================

/*
@Composable
fun MyScreen() {
    CommerceXTheme {
        // Your app content here
        // All composables will use the theme colors, typography, shapes
    }
}

// In MainActivity
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
        CommerceXTheme {
            NavHost(...)
        }
    }
}
*/

// ============================================================================
// ACCESSING THEME VALUES IN COMPOSABLES
// ============================================================================

/*
// Colors
val primaryColor = MaterialTheme.colorScheme.primary
val backgroundColor = MaterialTheme.colorScheme.background
val textColor = MaterialTheme.colorScheme.onBackground

// Typography
val titleStyle = MaterialTheme.typography.titleLarge
val bodyStyle = MaterialTheme.typography.bodyLarge

// Shapes
val cardShape = MaterialTheme.shapes.large

// Custom tokens
val spacing = Spacing.lg
val elevation = CommerceXElevation.level2
val gradient = CommerceXGradients.heroGradient

// Example component
@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.md)
            .shadow(
                elevation = CommerceXElevation.level1,
                shape = MaterialTheme.shapes.medium
            ),
        backgroundColor = MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(Spacing.lg)
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(Spacing.md))

            Text(
                text = "\\$${product.price}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
*/

