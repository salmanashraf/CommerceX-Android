package com.sa.commercex.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// ============================================================================
// TYPOGRAPHY SCALE - MATERIAL DESIGN 3
// ============================================================================
// Font: Inter (400, 500, 600, 700, 800)
// Mobile-first design for screens up to 448px

val CommerceXTypography = Typography(
    // ========================================================================
    // HEADLINES - Used for page titles and major sections
    // ========================================================================

    headlineLarge = TextStyle(
        fontWeight = FontWeight.Bold,          // 700
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),

    headlineMedium = TextStyle(
        fontWeight = FontWeight.Bold,          // 700
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),

    headlineSmall = TextStyle(
        fontWeight = FontWeight.SemiBold,      // 600
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),

    // ========================================================================
    // TITLES - Used for cards, sections, and secondary headings
    // ========================================================================

    titleLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,      // 600
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    titleMedium = TextStyle(
        fontWeight = FontWeight.Medium,        // 500
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),

    titleSmall = TextStyle(
        fontWeight = FontWeight.Medium,        // 500
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),

    // ========================================================================
    // BODY TEXT - Used for main content and descriptions
    // ========================================================================

    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,        // 400
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,        // 400
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),

    bodySmall = TextStyle(
        fontWeight = FontWeight.Normal,        // 400
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),

    // ========================================================================
    // LABELS - Used for buttons, badges, and UI labels
    // ========================================================================

    labelLarge = TextStyle(
        fontWeight = FontWeight.Medium,        // 500
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),

    labelMedium = TextStyle(
        fontWeight = FontWeight.Medium,        // 500
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

    labelSmall = TextStyle(
        fontWeight = FontWeight.Medium,        // 500
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

// ============================================================================
// TYPOGRAPHY USAGE GUIDE
// ============================================================================
/*
HEADLINES:
- headlineLarge (32sp, Bold)     → App title, hero sections
- headlineMedium (28sp, Bold)    → Section headers
- headlineSmall (24sp, SemiBold) → Screen titles

TITLES:
- titleLarge (20sp, SemiBold)    → Card titles, product names
- titleMedium (16sp, Medium)     → Subtitles, secondary headers
- titleSmall (14sp, Medium)      → Tab labels, form labels

BODY:
- bodyLarge (16sp, Normal)       → Product descriptions, primary text
- bodyMedium (14sp, Normal)      → Standard text, category names
- bodySmall (12sp, Normal)       → Helper text, captions

LABELS:
- labelLarge (14sp, Medium)      → Button text ("Add to Cart")
- labelMedium (12sp, Medium)     → Badges ("Sale", "In Stock")
- labelSmall (11sp, Medium)      → Rating text ("★ 4.5 (2.8K)")

EXAMPLES:

// Product title
Text(
    "Product Name",
    style = MaterialTheme.typography.titleLarge,
    color = TextPrimaryColor
)

// Button text
Text(
    "Add to Cart",
    style = MaterialTheme.typography.labelLarge,
    color = Color.White
)

// Product description
Text(
    "Full product description here...",
    style = MaterialTheme.typography.bodyLarge,
    color = TextPrimaryColor
)

// Sale badge
Text(
    "50% OFF",
    style = MaterialTheme.typography.labelMedium,
    color = Color.White
)
*/

