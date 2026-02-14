package com.sa.commercex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.sa.core.ui.preview.HomeScreenMockup
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Display Home Screen Mockup
                    HomeScreenMockup()
                }
            }
        }
    }
}

