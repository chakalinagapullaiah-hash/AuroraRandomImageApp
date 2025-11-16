package com.naga.aurorarandomimage.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naga.aurorarandomimage.data.repository.ImageRepository
import kotlinx.coroutines.launch

class ImageViewModel(
    private val repository: ImageRepository
) : ViewModel() {

    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    fun loadImage() {
        viewModelScope.launch {
            uiState = UiState.Loading
            try {
                val url = repository.fetchRandomImage()
                uiState = UiState.Success(url)
            }
//            catch (e: Exception) {
//                uiState = UiState.Error("Failed to load image")
//            }

            catch (e: Exception) {
                e.printStackTrace()
                uiState = UiState.Error("Failed to load image: ${e.message}")
            }
        }
    }
}

sealed class UiState {
    object Loading : UiState()
    data class Success(val url: String) : UiState()
    data class Error(val msg: String) : UiState()
}
