//////package com.naga.aurorarandomimage.ui.screen
//////
//////import android.graphics.BitmapFactory
//////import androidx.compose.animation.core.Animatable
//////import androidx.compose.foundation.background
//////import androidx.compose.foundation.layout.*
//////import androidx.compose.material3.Button
//////import androidx.compose.material3.CircularProgressIndicator
//////import androidx.compose.material3.Text
//////import androidx.compose.runtime.*
//////import androidx.compose.ui.Alignment
//////import androidx.compose.ui.Modifier
//////import androidx.compose.ui.graphics.Color
//////import androidx.compose.ui.graphics.toArgb
//////import androidx.compose.ui.unit.dp
//////import androidx.palette.graphics.Palette
//////import coil.compose.AsyncImage
//////import com.naga.aurorarandomimage.ui.viewmodel.UiState
//////import java.net.URL
//////
//////@Composable
//////fun ImageScreen(
//////    state: UiState,
//////    onAnotherClick: () -> Unit
//////) {
//////    val bgColor = remember { Animatable(Color.DarkGray) }
//////
//////    LaunchedEffect(state) {
//////        if (state is UiState.Success) {
//////            val bitmap = loadPictureBitmap(state.url)
//////            bitmap?.let {
//////                val palette = Palette.from(it).generate()
//////                val dominantColor = palette.getDominantColor(Color.DarkGray.toArgb())
//////                bgColor.animateTo(Color(dominantColor))
//////            }
//////        }
//////    }
//////
//////    Box(
//////        modifier = Modifier.run {
//////            fillMaxSize()
//////                .background(bgColor.value)
//////        },
//////        contentAlignment = Alignment.Center
//////    ) {
//////        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//////
//////            when (state) {
//////                UiState.Loading -> CircularProgressIndicator()
//////
//////                is UiState.Error -> Text(state.msg)
//////
//////                is UiState.Success -> {
//////                    AsyncImage(
//////                        model = state.url,
//////                        contentDescription = "Random image",
//////                        modifier = Modifier.size(300.dp)
//////                    )
//////                }
//////            }
//////
//////            Spacer(modifier = Modifier.height(20.dp))
//////
//////            Button(onClick = onAnotherClick) {
//////                Text("Another")
//////            }
//////        }
//////    }
//////}
//////
//////suspend fun loadPictureBitmap(url: String) = with(kotlinx.coroutines.Dispatchers.IO) {
//////    try {
//////        val connection = URL(url).openStream()
//////        BitmapFactory.decodeStream(connection)
//////    } catch (e: Exception) {
//////        null
//////    }
//////}
////
////
//////package com.naga.aurorarandomimage.ui.screen
//////
//////import android.graphics.BitmapFactory
//////
//////import androidx.compose.ui.graphics.Color
//////import androidx.compose.ui.graphics.VectorConverter   // CORRECT import
//////
////////import androidx.compose.animation.Animatable
////////import androidx.compose.animation.VectorConverter
//////import androidx.compose.animation.core.Animatable
//////import androidx.compose.foundation.background
//////import androidx.compose.foundation.layout.*
//////import androidx.compose.material3.Button
//////import androidx.compose.material3.CircularProgressIndicator
//////import androidx.compose.material3.Text
//////import androidx.compose.runtime.*
//////import androidx.compose.ui.Alignment
//////import androidx.compose.ui.Modifier
////////import androidx.compose.ui.graphics.Color
//////import androidx.compose.ui.graphics.toArgb
//////import androidx.compose.ui.unit.dp
//////import androidx.palette.graphics.Palette
//////import coil.compose.AsyncImage
//////import com.naga.aurorarandomimage.ui.viewmodel.UiState
//////import kotlinx.coroutines.Dispatchers
//////import kotlinx.coroutines.withContext
//////import java.net.URL
////
//////package com.naga.aurorarandomimage.ui.screen
//////
//////import android.graphics.BitmapFactory
//////import androidx.compose.ui.graphics.VectorConverter
//////
//////import androidx.compose.animation.core.Animatable
//////import androidx.compose.animation.core.tween
//////import androidx.compose.foundation.background
//////import androidx.compose.foundation.layout.*
//////import androidx.compose.material3.Button
//////import androidx.compose.material3.CircularProgressIndicator
//////import androidx.compose.material3.Text
//////import androidx.compose.runtime.*
//////import androidx.compose.ui.Alignment
//////import androidx.compose.ui.Modifier
//////import androidx.compose.ui.graphics.Color
////////import androidx.compose.ui.graphics.VectorConverter
//////import androidx.compose.ui.graphics.toArgb
//////import androidx.compose.ui.unit.dp
//////import androidx.palette.graphics.Palette
//////import coil.compose.AsyncImage
//////import com.naga.aurorarandomimage.ui.viewmodel.UiState
//////import kotlinx.coroutines.Dispatchers
//////import kotlinx.coroutines.withContext
//////import java.net.URL
//////
//////
//////@Composable
//////fun ImageScreen(
//////    state: UiState,
//////    onAnotherClick: () -> Unit
//////) {
//////    // FIXED: Explicitly typed Animatable<Color>
//////   // val bgColor = remember { Animatable(Color.Blue, Color.VectorConverter) }
//////
//////    //val bgColor = remember { Animatable(Color.DarkGray, Color.VectorConverter) }
//////
//////    val bgColor = remember { Animatable(Color.DarkGray, Color.VectorConverter) }
//////
//////
//////    // When image changes → update background color
//////    LaunchedEffect(state) {
//////        if (state is UiState.Success) {
//////            val bitmap = loadPictureBitmap(state.url)
//////            bitmap?.let {
//////                val palette = Palette.from(it).generate()
//////                val dominantColor = palette.getDominantColor(Color.DarkGray.toArgb())
//////                bgColor.animateTo(Color(dominantColor))
//////            }
//////        }
//////    }
//////
//////    Box(
//////        modifier = Modifier
//////            .fillMaxSize()
//////            .background(bgColor.value),
//////        contentAlignment = Alignment.Center
//////    ) {
//////        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//////
//////            when (state) {
//////                UiState.Loading -> CircularProgressIndicator()
//////
//////                is UiState.Error -> Text(state.msg)
//////
//////                is UiState.Success -> {
//////                    AsyncImage(
//////                        model = state.url,
//////                        contentDescription = "Random Image",
//////                        modifier = Modifier.size(300.dp)
//////                    )
//////                }
//////            }
//////
//////            Spacer(modifier = Modifier.height(20.dp))
//////
//////            Button(onClick = onAnotherClick) {
//////                Text("Another")
//////            }
//////        }
//////    }
//////}
////
////
////
/////**
//// * Loads a Bitmap from a URL so Palette can extract dominant colors.
//// */
//////suspend fun loadPictureBitmap(url: String) = withContext(Dispatchers.IO) {
//////    try {
//////        val connection = URL(url).openStream()
//////        BitmapFactory.decodeStream(connection)
//////    } catch (e: Exception) {
//////        null
//////    }
//////}
////
/////*
//////-------------------------
////package com.naga.aurorarandomimage.ui.screen
////
////import android.graphics.BitmapFactory
////import androidx.compose.animation.Animatable
////
//////import androidx.compose.animation.core.Animatable
//////import androidx.compose.animation.core.VectorConverter
//////import androidx.compose.animation.core.AnimationVector4D
////
////import androidx.compose.animation.core.Animatable
////import androidx.compose.animation.core.AnimationVector4D
////import androidx.compose.animation.core.VectorConverter
////import androidx.compose.runtime.remember
////
//////import androidx.compose.animation.core.Animatable
////import androidx.compose.animation.core.tween
////import androidx.compose.foundation.background
////import androidx.compose.foundation.layout.*
////import androidx.compose.material3.Button
////import androidx.compose.material3.CircularProgressIndicator
////import androidx.compose.material3.Text
////import androidx.compose.runtime.*
////import androidx.compose.ui.Alignment
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.graphics.Color
//////import androidx.compose.animation.core.VectorConverter
////import androidx.compose.ui.graphics.toArgb
////import androidx.compose.ui.unit.dp
////import androidx.palette.graphics.Palette
////import coil.compose.AsyncImage
////import com.naga.aurorarandomimage.ui.viewmodel.UiState
////import kotlinx.coroutines.Dispatchers
////import kotlinx.coroutines.withContext
////import java.net.URL
//////-------------------------------
////*/
////
////package com.naga.aurorarandomimage.ui.screen
////
////import android.graphics.BitmapFactory
////import androidx.compose.animation.core.Animatable
////import androidx.compose.animation.core.AnimationVector4D
////import androidx.compose.animation.core.VectorConverter
////import androidx.compose.animation.core.tween
////import androidx.compose.foundation.background
////import androidx.compose.foundation.layout.*
////import androidx.compose.material3.Button
////import androidx.compose.material3.CircularProgressIndicator
////import androidx.compose.material3.Text
////import androidx.compose.runtime.*
////import androidx.compose.ui.Alignment
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.graphics.Color
////import androidx.compose.ui.graphics.toArgb
////import androidx.compose.ui.unit.dp
////import androidx.palette.graphics.Palette
////import coil.compose.AsyncImage
////import com.naga.aurorarandomimage.ui.viewmodel.UiState
////import kotlinx.coroutines.Dispatchers
////import kotlinx.coroutines.withContext
////import java.net.URL
////
////
////
////@Composable
////fun ImageScreen(
////    state: UiState,
////    onAnotherClick: () -> Unit
////) {
////    //val bgColor = remember { Animatable(Color.DarkGray, Color.VectorConverter) }
////
////   // val bgColor = remember { Animatable<Color, AnimationVector4D>(Color.DarkGray, Color.VectorConverter) }
////
////    val bgColor = remember { Animatable<Color,
////            AnimationVector4D>(Color.DarkGray, Color.VectorConverter) }
////
////    LaunchedEffect(state) {
////        if (state is UiState.Success) {
////            val bitmap = loadPictureBitmap(state.url)
////            bitmap?.let {
////                val palette = Palette.from(it).generate()
////                val dominantColor = palette.getDominantColor(Color.DarkGray.toArgb())
////
////                bgColor.animateTo(
////                    targetValue = Color(dominantColor),
////                    animationSpec = tween(800)
////                )
////            }
////        }
////    }
////
////    Box(
////        modifier = Modifier
////            .fillMaxSize()
////            .background(bgColor.value),
////        contentAlignment = Alignment.Center
////    ) {
////        Column(horizontalAlignment = Alignment.CenterHorizontally) {
////
////            when (state) {
////                UiState.Loading -> CircularProgressIndicator()
////                is UiState.Error -> Text(state.msg)
////                is UiState.Success -> {
////                    AsyncImage(
////                        model = state.url,
////                        contentDescription = "Random Image",
////                        modifier = Modifier.size(300.dp)
////                    )
////                }
////            }
////
////            Spacer(modifier = Modifier.height(20.dp))
////
////            Button(onClick = onAnotherClick) {
////                Text("Another")
////            }
////        }
////    }
////}
////
////suspend fun loadPictureBitmap(url: String) = withContext(Dispatchers.IO) {
////    try {
////        val stream = URL(url).openStream()
////        BitmapFactory.decodeStream(stream)
////    } catch (e: Exception) {
////        null
////    }
////}
//package com.naga.aurorarandomimage.ui.screen
//
//import android.graphics.BitmapFactory
//import androidx.compose.animation.core.Animatable
//import androidx.compose.animation.core.AnimationVector4D
//import androidx.compose.animation.core.VectorConverter
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Button
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.toArgb
//import androidx.compose.ui.unit.dp
//import androidx.palette.graphics.Palette
//import coil.compose.AsyncImage
//import com.naga.aurorarandomimage.ui.viewmodel.UiState
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import java.net.URL
//
//@Composable
//fun ImageScreen(
//    state: UiState,
//    onAnotherClick: () -> Unit
//) {
//    // Animatable<Color> with the correct VectorConverter
////    val bgColor = remember {
////        Animatable<Color, AnimationVector4D>(
////            Color.DarkGray,
////            Color.VectorConverter
////        )
////    }
//
//    val bgColor = remember {
////        Animatable<Color, AnimationVector4D>(
////            Color.DarkGray,
////            Color.VectorConverter)
//
//        Animatable<Color, AnimationVector4D>(Color.DarkGray, Color.VectorConverter)
//
//    }
//
//    // Update background color when image loads
//    LaunchedEffect(state) {
//        if (state is UiState.Success) {
//            val bitmap = loadPictureBitmap(state.url)
//            bitmap?.let {
//                val palette = Palette.from(it).generate()
//                val dominantColor = palette.getDominantColor(Color.DarkGray.toArgb())
//
//                bgColor.animateTo(
//                    targetValue = Color(dominantColor),
//                    animationSpec = tween(800)
//                )
//            }
//        }
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(bgColor.value),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//
//            when (state) {
//                UiState.Loading -> CircularProgressIndicator()
//
//                is UiState.Error -> Text(state.msg)
//
//                is UiState.Success -> {
//                    AsyncImage(
//                        model = state.url,
//                        contentDescription = "Random Image",
//                        modifier = Modifier.size(300.dp)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            Button(onClick = onAnotherClick) {
//                Text(text = "Another")
//            }
//        }
//    }
//}
//
///**
// * Load a Bitmap from URL so Palette can extract colors
// */
//suspend fun loadPictureBitmap(url: String) = withContext(Dispatchers.IO) {
//    try {
//        val stream = URL(url).openStream()
//        BitmapFactory.decodeStream(stream)
//    } catch (e: Exception) {
//        null
//    }
//}

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
