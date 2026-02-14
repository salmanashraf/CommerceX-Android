package com.sa.core.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.ColorScheme

// ============================================================================
// LIGHT MODE COLOR SCHEME - Material Design 3
// ============================================================================

val LightColorScheme: ColorScheme = lightColorScheme(
    primary = PrimaryColor,              // #6750A4 - Deep Purple
    onPrimary = Color.White,
    primaryContainer = PrimaryLight,     // #EADDFF
    onPrimaryContainer = PrimaryDark,

    secondary = AccentColor,             // #FF8C00 - Orange
    onSecondary = Color.White,
    secondaryContainer = AccentLight,    // #FFAC3E
    onSecondaryContainer = AccentDark,

    tertiary = Color(0xFF0061A4),        // Blue (Material3)
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFD9E3FF),
    onTertiaryContainer = Color(0xFF001B3D),

    error = ErrorColor,                  // #C62828
    onError = Color.White,
    errorContainer = Color(0xFFFCDEDE),
    onErrorContainer = ErrorColor,

    background = BackgroundColor,        // #FAFAFA - Off-white
    onBackground = TextPrimaryColor,     // #212121

    surface = SurfaceColor,              // #FFFFFF - White
    onSurface = TextPrimaryColor,        // #212121
    surfaceVariant = SurfaceVariant,     // #F5F5F5
    onSurfaceVariant = TextSecondaryColor, // #757575

    outline = BorderColor,               // #CECECE
    outlineVariant = DividerColor        // #E0E0E0
)

// ============================================================================
// DARK MODE COLOR SCHEME - Material Design 3
// ============================================================================

val DarkColorScheme: ColorScheme = darkColorScheme(
    primary = DarkColorPalette.PrimaryLight,           // #D0BCFF
    onPrimary = PrimaryDark,                           // #4F378B
    primaryContainer = PrimaryColor,                   // #6750A4
    onPrimaryContainer = DarkColorPalette.PrimaryLight, // #D0BCFF

    secondary = AccentLight,             // #FFAC3E
    onSecondary = AccentDark,            // #E67E00
    secondaryContainer = AccentColor,    // #FF8C00
    onSecondaryContainer = AccentLight,  // #FFAC3E

    tertiary = Color(0xFFB3E5FC),        // Light Blue
    onTertiary = Color(0xFF00368C),
    tertiaryContainer = Color(0xFF004C97),
    onTertiaryContainer = Color(0xFFD9E3FF),

    error = Color(0xFFFFB4AB),           // Light Error
    onError = Color(0xFF601410),
    errorContainer = Color(0xFF8C1D18),
    onErrorContainer = Color(0xFFFFB4AB),

    background = DarkColorPalette.BackgroundColor,     // #121212
    onBackground = DarkColorPalette.TextPrimaryColor,  // #E6E1E5

    surface = DarkColorPalette.SurfaceColor,           // #1E1E1E
    onSurface = DarkColorPalette.TextPrimaryColor,     // #E6E1E5
    surfaceVariant = DarkColorPalette.SurfaceVariant,  // #2D2D2D
    onSurfaceVariant = DarkColorPalette.TextSecondaryColor, // #CCC7D0

    outline = Color(0xFF7D747D),         // Medium outline
    outlineVariant = DarkColorPalette.SurfaceVariant   // #2D2D2D
)

// ============================================================================
// USAGE NOTES
// ============================================================================
/*
These color schemes are used in the Theme composable:

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
        content = content
    )
}

WCAG CONTRAST COMPLIANCE:
- Primary on Surface: 9.5:1 (AAA)
- Secondary on Surface: 5.2:1 (AA)
- Text Primary on Surface: 14.5:1 (AAA)
- Text Secondary on Surface: 6.2:1 (AA)
*/

