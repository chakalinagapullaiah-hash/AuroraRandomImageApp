package com.naga.aurorarandomimage.ui.screen

import android.graphics.BitmapFactory
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import com.naga.aurorarandomimage.ui.viewmodel.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

@Composable
fun ImageScreen(
    state: UiState,
    onAnotherClick: () -> Unit
) {
    // Target background color (updated when image changes)
    var targetBgColor by remember { mutableStateOf(Color.DarkGray) }

    // Animated background color (no VectorConverter needed)
    val animatedBgColor by animateColorAsState(
        targetValue = targetBgColor,
        animationSpec = tween(800),
        label = "bg"
    )

    // React when image loads
    LaunchedEffect(state) {
        if (state is UiState.Success) {
            val bitmap = loadPictureBitmap(state.url)
            bitmap?.let {
                val palette = Palette.from(it).generate()
                val dominant = palette.getDominantColor(Color.DarkGray.toArgb())
                targetBgColor = Color(dominant) // Set target color
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(animatedBgColor),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            when (state) {
                UiState.Loading -> CircularProgressIndicator()

                is UiState.Error -> Text(state.msg)

                is UiState.Success -> {
                    AsyncImage(
                        model = state.url,
                        contentDescription = "Random Image",
                        modifier = Modifier.size(300.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = onAnotherClick) {
                Text("Show Random Image")
            }
        }
    }
}

suspend fun loadPictureBitmap(url: String) = withContext(Dispatchers.IO) {
    try {
        val stream = URL(url).openStream()
        BitmapFactory.decodeStream(stream)
    } catch (e: Exception) {
        null
    }
}
