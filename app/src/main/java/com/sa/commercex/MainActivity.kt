package com.sa.commercex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.sa.feature.cart.ui.CartScreenMockup
import com.sa.feature.auth.ui.LoginScreenMockup
import com.sa.feature.auth.ui.ProfileScreenMockup
import com.sa.feature.product.ui.HomeScreenMockup
import com.sa.feature.product.ui.ProductDetailMockup
import com.sa.feature.search.ui.SearchScreenDefaultMockup
import com.sa.core.ui.theme.CommerceXTheme

/**
 * Main Activity - CommerceX App Entry Point
 *
 * Displays the Home Screen mockup on app launch.
 * This demonstrates the complete home screen design from US-0.3.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CommerceXTheme {
                var currentScreen by remember { mutableStateOf(AppScreen.HOME) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimatedContent(
                        targetState = currentScreen,
                        transitionSpec = {
                            (slideInHorizontally(
                                initialOffsetX = { it / 3 },
                                animationSpec = tween(260)
                            ) + fadeIn(animationSpec = tween(260))).togetherWith(
                                slideOutHorizontally(
                                    targetOffsetX = { -it / 3 },
                                    animationSpec = tween(220)
                                ) + fadeOut(animationSpec = tween(220))
                            )
                        },
                        label = "main_screen_transition"
                    ) { screen ->
                        when (screen) {
                            AppScreen.LOGIN -> LoginScreenMockup(
                                onSignInClick = { currentScreen = AppScreen.PROFILE }
                            )
                            AppScreen.PROFILE -> ProfileScreenMockup(
                                onBackClick = { currentScreen = AppScreen.HOME },
                                onLogoutClick = { currentScreen = AppScreen.LOGIN }
                            )
                            AppScreen.SEARCH -> SearchScreenDefaultMockup(
                                onBackClick = { currentScreen = AppScreen.HOME }
                            )
                            AppScreen.CART -> CartScreenMockup(
                                onBackClick = { currentScreen = AppScreen.HOME }
                            )
                            AppScreen.PRODUCT_DETAIL -> ProductDetailMockup(
                                onBackClick = { currentScreen = AppScreen.HOME }
                            )
                            AppScreen.HOME -> HomeScreenMockup(
                                onProductClick = { currentScreen = AppScreen.PRODUCT_DETAIL },
                                onCartClick = { currentScreen = AppScreen.CART },
                                onSearchClick = { currentScreen = AppScreen.SEARCH },
                                onProfileClick = { currentScreen = AppScreen.PROFILE }
                            )
                        }
                    }
                }
            }
        }
    }
}

private enum class AppScreen {
    HOME,
    SEARCH,
    CART,
    PRODUCT_DETAIL,
    PROFILE,
    LOGIN
}
