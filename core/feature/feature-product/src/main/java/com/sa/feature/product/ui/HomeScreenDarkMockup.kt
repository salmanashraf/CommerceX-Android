package com.sa.feature.product.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sa.core.ui.theme.CommerceXTheme

/**
 * Home Screen Mockup - Dark Mode
 *
 * US-0.3: Screen Mockups - Home & Product List
 */
@Preview(
    name = "Home Screen - Dark Mode",
    showBackground = true,
    widthDp = 448,
    heightDp = 900,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun HomeScreenDarkPreview() {
    CommerceXTheme {
        HomeScreenMockup()
    }
}

/**
 * Home Screen States Preview
 */
@Preview(
    name = "Home Screen - No Cart Items",
    showBackground = true,
    widthDp = 448,
    heightDp = 900
)
@Composable
fun HomeScreenEmptyCartPreview() {
    CommerceXTheme {
        HomeScreenMockup()
    }
}

/**
 * Home Screen - Category Selected
 */
@Preview(
    name = "Home Screen - Category Selected",
    showBackground = true,
    widthDp = 448,
    heightDp = 900
)
@Composable
fun HomeScreenCategoryPreview() {
    CommerceXTheme {
        HomeScreenMockup()
    }
}

