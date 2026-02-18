package com.sa.feature.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sa.core.ui.component.PrimaryButton
import com.sa.core.ui.component.TextInput
import com.sa.core.ui.theme.BackgroundColor
import com.sa.core.ui.theme.BorderColor
import com.sa.core.ui.theme.CommerceXTheme
import com.sa.core.ui.theme.PrimaryColor
import com.sa.core.ui.theme.PrimaryLight
import com.sa.core.ui.theme.SaleColor
import com.sa.core.ui.theme.Spacing
import com.sa.core.ui.theme.SurfaceColor
import com.sa.core.ui.theme.TextSecondaryColor

/**
 * US-0.7: Screen Mockups - Profile & Auth
 */
@Preview(name = "Profile Screen", showBackground = true, widthDp = 448, heightDp = 900)
@Composable
fun ProfileScreenPreview() {
    CommerceXTheme {
        ProfileScreenMockup()
    }
}

@Preview(name = "Login Screen", showBackground = true, widthDp = 448, heightDp = 900)
@Composable
fun LoginScreenPreview() {
    CommerceXTheme {
        LoginScreenMockup()
    }
}

@Composable
fun ProfileScreenMockup(
    onBackClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Spacing.lg)
        ) {
            ProfileTopBar(onBackClick = onBackClick)

            Spacer(modifier = Modifier.height(Spacing.xl))

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(96.dp)
                            .clip(CircleShape)
                            .background(
                                brush = Brush.linearGradient(
                                    listOf(PrimaryColor, PrimaryLight)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "EJ",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(Spacing.md))

                    Text(
                        text = "Emma Johnson",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "emma.johnson@example.com",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondaryColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(Spacing.xl))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = SurfaceColor,
                shape = MaterialTheme.shapes.large
            ) {
                Column {
                    ProfileMenuItem(icon = Icons.Filled.Inventory2, label = "My Orders", badgeCount = 3)
                    HorizontalDivider(color = BorderColor)
                    ProfileMenuItem(icon = Icons.Filled.CreditCard, label = "Payment Methods")
                    HorizontalDivider(color = BorderColor)
                    ProfileMenuItem(icon = Icons.Filled.Notifications, label = "Notifications")
                    HorizontalDivider(color = BorderColor)
                    ProfileMenuItem(icon = Icons.Filled.Settings, label = "Settings")
                    HorizontalDivider(color = BorderColor)
                    ProfileMenuItem(icon = Icons.Filled.HelpOutline, label = "Help & Support")
                }
            }

            Spacer(modifier = Modifier.height(Spacing.xl))

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onLogoutClick),
                color = Color.Transparent,
                shape = MaterialTheme.shapes.medium
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, SaleColor, MaterialTheme.shapes.medium)
                        .padding(horizontal = Spacing.lg, vertical = Spacing.md),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Logout,
                        contentDescription = "Log out",
                        tint = SaleColor
                    )
                    Spacer(modifier = Modifier.size(Spacing.sm))
                    Text(
                        text = "Log Out",
                        color = SaleColor,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreenMockup(
    onSignInClick: () -> Unit = {}
) {
    var username by remember { mutableStateOf("emma.johnson") }
    var password by remember { mutableStateOf("password123") }
    var showPassword by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Spacing.lg),
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(Brush.linearGradient(listOf(PrimaryColor, PrimaryLight))),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "CX",
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(Spacing.md))

                    Text(
                        text = "Welcome Back",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Sign in to continue shopping",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondaryColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(Spacing.xl))

            TextInput(
                value = username,
                onValueChange = { username = it },
                label = "Username",
                placeholder = "Enter your username"
            )

            Spacer(modifier = Modifier.height(Spacing.md))

            PasswordInput(
                value = password,
                onValueChange = { password = it },
                isVisible = showPassword,
                onVisibilityToggle = { showPassword = !showPassword }
            )

            Spacer(modifier = Modifier.height(Spacing.sm))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(
                    text = "Forgot Password?",
                    color = PrimaryColor,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { }
                )
            }

            Spacer(modifier = Modifier.height(Spacing.lg))

            PrimaryButton(
                text = "Sign In",
                onClick = onSignInClick,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(Spacing.xl))

            Text(
                text = "Or continue with",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = TextSecondaryColor,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(Spacing.md))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Spacing.md, Alignment.CenterHorizontally)
            ) {
                SocialChip(label = "Google")
                SocialChip(label = "Apple")
            }
        }
    }
}

@Composable
private fun ProfileTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Spacing.sm),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
        Text(
            text = "Profile",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    label: String,
    badgeCount: Int? = null,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = Spacing.lg, vertical = Spacing.md),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = PrimaryColor)

        Spacer(modifier = Modifier.size(Spacing.md))

        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )

        if ((badgeCount ?: 0) > 0) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(PrimaryColor)
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = badgeCount.toString(),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.size(Spacing.sm))
        }

        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = "Open",
            tint = TextSecondaryColor
        )
    }
}

@Composable
private fun PasswordInput(
    value: String,
    onValueChange: (String) -> Unit,
    isVisible: Boolean,
    onVisibilityToggle: () -> Unit
) {
    Column {
        Text(
            text = "Password",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(Spacing.xs))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(SurfaceColor, MaterialTheme.shapes.medium)
                .border(1.dp, BorderColor, MaterialTheme.shapes.medium)
                .padding(horizontal = Spacing.lg),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation()
            )
            IconButton(onClick = onVisibilityToggle) {
                Icon(
                    imageVector = if (isVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                    contentDescription = "Toggle password visibility",
                    tint = TextSecondaryColor
                )
            }
        }
    }
}

@Composable
private fun SocialChip(label: String) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = SurfaceColor,
        modifier = Modifier.border(1.dp, BorderColor, MaterialTheme.shapes.medium)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = Spacing.lg, vertical = Spacing.sm),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.xs)
        ) {
            Icon(
                imageVector = if (label == "Google") Icons.Filled.Lock else Icons.Filled.Person,
                contentDescription = label,
                tint = TextSecondaryColor,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}
