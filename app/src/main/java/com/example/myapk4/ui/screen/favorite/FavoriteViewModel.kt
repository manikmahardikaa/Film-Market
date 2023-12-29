package com.example.myapk4.ui.screen.favorite

import androidx.lifecycle.ViewModel
import com.example.myapk4.data.ProductRepository
import com.example.myapk4.model.OrderProduct
import com.example.myapk4.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//class FavoriteViewModel(private val repository: ProductRepository): ViewModel() {
//    private val _uiState: MutableStateFlow<UiState<List<OrderProduct>>> = MutableStateFlow(UiState.Loading)
//    val uiState: StateFlow<UiState<List<OrderProduct>>>
//        get() = _uiState
//
//    fun getAllFavorite
//}