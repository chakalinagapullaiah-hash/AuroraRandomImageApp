package com.naga.aurorarandomimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.naga.aurorarandomimage.ui.screen.ImageScreen
import com.naga.aurorarandomimage.ui.theme.AuroraRandomImageTheme
import com.naga.aurorarandomimage.ui.viewmodel.ImageViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            // Create ViewModel
            val viewModel = remember { ImageViewModel(AppModule.repository) }
            val state = viewModel.uiState

            // Load first image when app starts
            LaunchedEffect(Unit) {
                viewModel.loadImage()
            }

            AuroraRandomImageTheme {
                ImageScreen(
                    state = state,
                    onAnotherClick = { viewModel.loadImage() }
                )
            }
        }
    }
}
