package com.sa.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// ============================================================================
// MATERIAL DESIGN 3 SHAPES
// ============================================================================

val CommerceXShapes = Shapes(
    small = RoundedCornerShape(6.dp),      // Small components (chips, tags)
    medium = RoundedCornerShape(12.dp),    // Medium components (buttons, inputs)
    large = RoundedCornerShape(16.dp)      // Large components (cards, dialogs)
)

// ============================================================================
// CUSTOM SHAPE TOKENS
// ============================================================================

object CustomShapes {
    // 4dp - Minimal rounding
    val xs = RoundedCornerShape(4.dp)

    // 8dp - Small elements
    val sm = RoundedCornerShape(8.dp)

    // 12dp - STANDARD for cards (0.75rem per design.md)
    val md = RoundedCornerShape(12.dp)

    // 16dp - Large containers
    val lg = RoundedCornerShape(16.dp)

    // 20dp - Extra large
    val xl = RoundedCornerShape(20.dp)

    // 50dp - Pill shape for buttons and chips
    val full = RoundedCornerShape(50.dp)
}

// ============================================================================
// USAGE GUIDE
// ============================================================================
/*
4dp (xs)   - Minimal rounding, icon buttons
8dp (sm)   - Small badges, tags
12dp (md)  - STANDARD CARDS, product cards ⭐
16dp (lg)  - Dialogs, large containers
20dp (xl)  - Extra large containers
50dp (full)- Pill-shaped buttons, chips

EXAMPLE - Product Card:
Card(
    modifier = Modifier
        .clip(CustomShapes.md)  // 12dp border radius
        .background(Color.White)
)

EXAMPLE - Pill Button:
Button(
    modifier = Modifier
        .clip(CustomShapes.full)  // 50dp for pill shape
)
*/

