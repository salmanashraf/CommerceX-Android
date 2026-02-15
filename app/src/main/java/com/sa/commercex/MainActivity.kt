package com.sa.commercex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.sa.feature.cart.ui.CartScreenMockup
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
                var showProductDetail by remember { mutableStateOf(false) }
                var showCart by remember { mutableStateOf(false) }
                var showSearch by remember { mutableStateOf(false) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when {
                        showSearch -> {
                            SearchScreenDefaultMockup()
                        }
                        showCart -> {
                            CartScreenMockup(
                                onBackClick = { showCart = false }
                            )
                        }
                        showProductDetail -> {
                            ProductDetailMockup(
                                onBackClick = { showProductDetail = false }
                            )
                        }
                        else -> {
                            HomeScreenMockup(
                                onProductClick = { showProductDetail = true },
                                onCartClick = { showCart = true },
                                onSearchClick = { showSearch = true }
                            )
                        }
                    }
                }
            }
        }
    }
}
