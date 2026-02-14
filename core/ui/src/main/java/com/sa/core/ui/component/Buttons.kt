package com.sa.core.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sa.core.ui.theme.CommerceXElevation
import com.sa.core.ui.theme.CommerceXGradients
import com.sa.core.ui.theme.CustomShapes
import com.sa.core.ui.theme.PrimaryColor
import com.sa.core.ui.theme.Spacing
import com.sa.core.ui.theme.TextSecondaryColor

/**
 * Primary Button with Gradient Background
 *
 * Features:
 * - Purple gradient background
 * - White text
 * - Rounded corners (12dp)
 * - Elevated appearance
 */
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(56.dp)
            .background(
                brush = CommerceXGradients.buttonGradient,
                shape = CustomShapes.md
            ),
        enabled = enabled,
        shape = CustomShapes.md,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = CommerceXElevation.level2,
            pressedElevation = CommerceXElevation.level1,
            disabledElevation = 0.dp
        ),
        contentPadding = PaddingValues(horizontal = Spacing.xl, vertical = Spacing.md)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = if (enabled) Color.White else Color.White.copy(alpha = 0.5f)
        )
    }
}

/**
 * Secondary Outlined Button
 *
 * Features:
 * - Outlined with primary color border
 * - Primary color text
 * - Rounded corners (12dp)
 * - Transparent background
 */
@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        enabled = enabled,
        shape = CustomShapes.md,
        border = BorderStroke(
            width = 2.dp,
            color = if (enabled) PrimaryColor else PrimaryColor.copy(alpha = 0.3f)
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = PrimaryColor,
            disabledContentColor = PrimaryColor.copy(alpha = 0.3f)
        ),
        contentPadding = PaddingValues(horizontal = Spacing.xl, vertical = Spacing.md)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold
        )
    }
}

/**
 * Text Button
 *
 * Features:
 * - No background or border
 * - Primary color text
 * - Minimal padding
 */
@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = PrimaryColor,
            disabledContentColor = TextSecondaryColor
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Medium
        )
    }
}

/**
 * Accent CTA Button with Orange Gradient
 *
 * Features:
 * - Orange/accent gradient background
 * - White text
 * - Used for primary CTAs like "Add to Cart"
 */
@Composable
fun AccentButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(56.dp)
            .background(
                brush = CommerceXGradients.accentGradient,
                shape = CustomShapes.md
            ),
        enabled = enabled,
        shape = CustomShapes.md,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = CommerceXElevation.level2,
            pressedElevation = CommerceXElevation.level1
        ),
        contentPadding = PaddingValues(horizontal = Spacing.xl, vertical = Spacing.md)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = if (enabled) Color.White else Color.White.copy(alpha = 0.5f)
        )
    }
}

