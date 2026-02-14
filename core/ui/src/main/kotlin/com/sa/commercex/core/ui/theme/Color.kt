package com.sa.commercex.core.ui.theme

import androidx.compose.ui.graphics.Color

// ============================================================================
// PRIMARY COLORS
// ============================================================================

// Deep Purple (HSL 269 39% 49%)
val PrimaryColor = Color(0xFF6750A4)
val PrimaryLight = Color(0xFFEADDF8)      // Tint for backgrounds
val PrimaryDark = Color(0xFF4F378B)       // Shade for pressed states

// ============================================================================
// ACCENT/CTA COLORS
// ============================================================================

// Orange (HSL 25 95% 55%)
val AccentColor = Color(0xFFFF8C00)
val AccentLight = Color(0xFFFFB84D)       // For hover state
val AccentDark = Color(0xFFE67E00)        // For pressed state

// ============================================================================
// STATUS COLORS
// ============================================================================

// Sale badges - Red (HSL 0 84% 55%)
val SaleColor = Color(0xFFD32F2F)

// Success/stock indicators - Green (HSL 145 63% 42%)
val SuccessColor = Color(0xFF43A047)

// Error states
val ErrorColor = Color(0xFFC62828)

// Warning states
val WarningColor = Color(0xFFFFA726)

// ============================================================================
// NEUTRAL COLORS
// ============================================================================

// Background - Off-white (HSL 0 0% 98%)
val BackgroundColor = Color(0xFFFAFAFA)

// Cards - Pure white
val SurfaceColor = Color(0xFFFFFFFF)

// Light gray for variant surfaces
val SurfaceVariant = Color(0xFFF5F5F5)

// Text Primary - Dark gray
val TextPrimaryColor = Color(0xFF212121)

// Text Secondary - Medium gray
val TextSecondaryColor = Color(0xFF757575)

// Text Tertiary - Light gray
val TextTertiaryColor = Color(0xFFBDBDBD)

// Dividers and borders
val DividerColor = Color(0xFFE0E0E0)
val BorderColor = Color(0xFFCECECE)

// ============================================================================
// DARK MODE COLORS
// ============================================================================

object DarkColorPalette {
    val BackgroundColor = Color(0xFF121212)
    val SurfaceColor = Color(0xFF1E1E1E)
    val SurfaceVariant = Color(0xFF2D2D2D)
    val PrimaryLight = Color(0xFFD0BCFF)   // Adjusted for dark background
    val TextPrimaryColor = Color(0xFFE6E1E5)
    val TextSecondaryColor = Color(0xFFCCC7D0)
    val TextTertiaryColor = Color(0xFFB3B0B8)
}

// ============================================================================
// SHADOW COLORS
// ============================================================================

// Subtle shadows for Material Design 3 style
val ShadowColor = Color(0x1F000000)        // Black with 12% opacity
val ShadowColorDark = Color(0x24000000)    // Black with 14% opacity

