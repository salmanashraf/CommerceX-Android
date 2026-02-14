package com.sa.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.sa.core.ui.theme.BorderColor
import com.sa.core.ui.theme.CustomShapes
import com.sa.core.ui.theme.ErrorColor
import com.sa.core.ui.theme.Spacing
import com.sa.core.ui.theme.SurfaceColor
import com.sa.core.ui.theme.SurfaceVariant
import com.sa.core.ui.theme.TextPrimaryColor
import com.sa.core.ui.theme.TextSecondaryColor

/**
 * Search Bar Component
 *
 * Features:
 * - Rounded secondary-colored background
 * - Search icon on the left
 * - Auto-focus capability
 * - Clear button when text is present
 */
@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search products...",
    autoFocus: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = SurfaceVariant,
                shape = CustomShapes.full
            )
            .padding(horizontal = Spacing.lg, vertical = Spacing.sm),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.sm)
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search",
            tint = TextSecondaryColor,
            modifier = Modifier.size(20.dp)
        )

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = TextPrimaryColor
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearch(value) }
            ),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextSecondaryColor
                    )
                }
                innerTextField()
            }
        )

        if (value.isNotEmpty()) {
            IconButton(
                onClick = { onValueChange("") },
                modifier = Modifier.size(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Clear",
                    tint = TextSecondaryColor
                )
            }
        }
    }
}

/**
 * Text Input Field
 *
 * Features:
 * - Standard text input with label
 * - Optional error state
 * - Rounded corners
 * - Border styling
 */
@Composable
fun TextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    error: String? = null,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = TextPrimaryColor,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(Spacing.xs))

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(
                    color = SurfaceColor,
                    shape = CustomShapes.md
                )
                .border(
                    width = 1.dp,
                    color = if (error != null) ErrorColor else BorderColor,
                    shape = CustomShapes.md
                )
                .padding(horizontal = Spacing.lg, vertical = Spacing.md),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = TextPrimaryColor
            ),
            singleLine = true,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty() && placeholder.isNotEmpty()) {
                        Text(
                            text = placeholder,
                            style = MaterialTheme.typography.bodyLarge,
                            color = TextSecondaryColor
                        )
                    }
                    innerTextField()
                }
            }
        )

        if (error != null) {
            Spacer(modifier = Modifier.height(Spacing.xs))
            Text(
                text = error,
                style = MaterialTheme.typography.labelSmall,
                color = ErrorColor
            )
        }
    }
}

/**
 * Quantity Selector Component
 *
 * Features:
 * - Minus/Plus buttons
 * - Centered quantity display
 * - Bordered row layout
 * - Min/max constraints
 */
@Composable
fun QuantitySelector(
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    minQuantity: Int = 1,
    maxQuantity: Int = 99
) {
    Row(
        modifier = modifier
            .height(48.dp)
            .border(
                width = 1.dp,
                color = BorderColor,
                shape = CustomShapes.md
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Minus button
        IconButton(
            onClick = {
                if (quantity > minQuantity) {
                    onQuantityChange(quantity - 1)
                }
            },
            enabled = quantity > minQuantity,
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = Spacing.xs)
        ) {
            Icon(
                imageVector = Icons.Filled.Remove,
                contentDescription = "Decrease quantity",
                tint = if (quantity > minQuantity) TextPrimaryColor else TextSecondaryColor
            )
        }

        // Quantity display
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(color = SurfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = quantity.toString(),
                style = MaterialTheme.typography.titleMedium,
                color = TextPrimaryColor,
                fontWeight = FontWeight.SemiBold
            )
        }

        // Plus button
        IconButton(
            onClick = {
                if (quantity < maxQuantity) {
                    onQuantityChange(quantity + 1)
                }
            },
            enabled = quantity < maxQuantity,
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = Spacing.xs)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Increase quantity",
                tint = if (quantity < maxQuantity) TextPrimaryColor else TextSecondaryColor
            )
        }
    }
}

