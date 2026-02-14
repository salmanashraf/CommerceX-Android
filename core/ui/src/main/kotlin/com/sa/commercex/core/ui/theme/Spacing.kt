package com.sa.commercex.core.ui.theme

import androidx.compose.ui.unit.dp

// ============================================================================
// SPACING SCALE (4dp base unit)
// ============================================================================

object Spacing {
    // Extra small - 1x base unit
    val xs = 4.dp

    // Small - 2x base unit
    val sm = 8.dp

    // Medium - 3x base unit
    val md = 12.dp

    // Large - 4x base unit (standard padding)
    val lg = 16.dp

    // Extra large - 6x base unit
    val xl = 24.dp

    // Extra extra large - 8x base unit (screen padding)
    val xxl = 32.dp

    // Triple extra large - 12x base unit
    val xxxl = 48.dp
}

// ============================================================================
// SPACING USAGE GUIDE
// ============================================================================
/*
xs (4dp)   - Icon spacing, tight elements
sm (8dp)   - Small gaps, padding inside buttons
md (12dp)  - Component spacing, content padding
lg (16dp)  - Standard padding, default spacing
xl (24dp)  - Section spacing, large margins
xxl (32dp) - Screen padding, major sections
xxxl (48dp)- Top/bottom screen margins

EXAMPLES:
- Product card padding: lg (16dp)
- Horizontal spacing between cards: md (12dp)
- Section top/bottom: xl (24dp)
- Screen edge padding: xxl (32dp)
- Icon to text distance: sm (8dp)
*/

