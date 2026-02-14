package com.sa.commercex.core.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// ============================================================================
// GRADIENT SPECIFICATIONS - Per design.md
// ============================================================================

object CommerceXGradients {
    // ========================================================================
    // PRIMARY GRADIENT - Purple (soft)
    // Angle: 135° (top-left to bottom-right)
    // ========================================================================
    val primaryGradient = Brush.linearGradient(
        colors = listOf(
            PrimaryColor,       // #6750A4 (Deep Purple)
            PrimaryLight        // #EADDFF (Light Purple)
        ),
        angle = 135f
    )

    // ========================================================================
    // HERO GRADIENT - Purple to Orange
    // Used for hero banner per design.md: "purple → orange for hero banner"
    // Angle: 135° (top-left to bottom-right)
    // ========================================================================
    val heroGradient = Brush.linearGradient(
        colors = listOf(
            PrimaryColor,       // #6750A4 (Deep Purple)
            AccentColor         // #FF8C00 (Orange)
        ),
        angle = 135f
    )

    // ========================================================================
    // BUTTON GRADIENT - Dark Purple to Medium Purple
    // Per design.md: "purple → lighter purple (135deg) for buttons"
    // Angle: 90° (top to bottom for buttons)
    // ========================================================================
    val buttonGradient = Brush.linearGradient(
        colors = listOf(
            PrimaryColor,       // #6750A4
            PrimaryDark         // #4F378B
        ),
        angle = 90f
    )

    // ========================================================================
    // ACCENT GRADIENT - Orange (warm)
    // For secondary actions and highlights
    // ========================================================================
    val accentGradient = Brush.linearGradient(
        colors = listOf(
            AccentColor,        // #FF8C00 (Orange)
            AccentLight         // #FFAC3E (Light Orange)
        ),
        angle = 135f
    )

    // ========================================================================
    // SUCCESS GRADIENT - Green
    // For success states and confirmations
    // ========================================================================
    val successGradient = Brush.linearGradient(
        colors = listOf(
            SuccessColor,       // #43A047 (Green)
            Color(0xFF388E3C)   // Darker Green
        ),
        angle = 135f
    )

    // ========================================================================
    // WARNING GRADIENT - Orange to Accent
    // For warning and alert states
    // ========================================================================
    val warningGradient = Brush.linearGradient(
        colors = listOf(
            WarningColor,       // #FFA726 (Orange)
            AccentColor         // #FF8C00 (Darker Orange)
        ),
        angle = 135f
    )
}

// ============================================================================
// GRADIENT USAGE GUIDE
// ============================================================================
/*
PRIMARY GRADIENT:
- Button backgrounds (primary color)
- Key UI elements requiring emphasis
Example:
Button(
    modifier = Modifier.background(CommerceXGradients.primaryGradient)
)

HERO GRADIENT:
- Hero banner on home screen (per design.md)
- Large background sections
Example:
Box(
    modifier = Modifier.background(CommerceXGradients.heroGradient)
) {
    Text("Summer Collection / Up to 50% OFF")
}

BUTTON GRADIENT:
- Primary action buttons
- "Add to Cart" button (per design.md)
Example:
Button(
    modifier = Modifier
        .background(CommerceXGradients.buttonGradient)
        .clip(RoundedCornerShape(12.dp))
)

ACCENT GRADIENT:
- Secondary actions and CTAs
- Highlight elements
Example:
Button(
    modifier = Modifier.background(CommerceXGradients.accentGradient)
)

SUCCESS GRADIENT:
- Success confirmations
- "In Stock" indicators
Example:
Box(
    modifier = Modifier.background(CommerceXGradients.successGradient)
)

WARNING GRADIENT:
- Warning states
- Alert banners
Example:
Box(
    modifier = Modifier.background(CommerceXGradients.warningGradient)
)
*/

