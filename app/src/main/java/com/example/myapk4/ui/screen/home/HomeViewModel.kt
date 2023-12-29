package com.example.myapk4.ui.screen.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapk4.data.ProductRepository
import com.example.myapk4.model.OrderProduct
import com.example.myapk4.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import androidx.compose.runtime.State
import com.example.myapk4.model.Product
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ProductRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderProduct>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderProduct>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query


    fun getAllProduct() {
        viewModelScope.launch {
            repository.getAllProduct()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderProduct ->
                    _uiState.value = UiState.Success(orderProduct)
                }
        }
    }


    fun search(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAllProduct(_query.value)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderProduct ->
                    _uiState.value = UiState.Success(orderProduct)
                }
        }
    }


}