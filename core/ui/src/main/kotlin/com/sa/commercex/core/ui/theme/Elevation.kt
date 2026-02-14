package com.sa.commercex.core.ui.theme

import androidx.compose.ui.unit.dp

// ============================================================================
// ELEVATION/SHADOWS - MATERIAL DESIGN 3 SCALE
// ============================================================================

object CommerceXElevation {
    // Level 0 - No elevation (flat)
    val level0 = 0.dp

    // Level 1 - Subtle (default cards)
    val level1 = 1.dp

    // Level 2 - Slight (hovered cards, scrolled items)
    val level2 = 3.dp

    // Level 3 - Medium (floating buttons, FAB)
    val level3 = 6.dp

    // Level 4 - Higher (dialogs, modals)
    val level4 = 8.dp

    // Level 5 - Maximum (top app bar, sticky headers)
    val level5 = 12.dp
}

// ============================================================================
// SHADOW USAGE GUIDE
// ============================================================================
/*
Level 0 (0dp)   - Flat backgrounds, no depth
Level 1 (1dp)   - Product cards at rest
Level 2 (3dp)   - Cards on scroll/hover
Level 3 (6dp)   - Floating action buttons
Level 4 (8dp)   - Dialogs, bottom sheets
Level 5 (12dp)  - Top app bar, sticky headers

EXAMPLE - Product Card:
Card(
    modifier = Modifier
        .shadow(
            elevation = CommerceXElevation.level1,
            shape = RoundedCornerShape(12.dp)
        )
)

EXAMPLE - Floating Button:
FloatingActionButton(
    modifier = Modifier
        .shadow(
            elevation = CommerceXElevation.level3,
            shape = CircleShape
        )
)
*/

