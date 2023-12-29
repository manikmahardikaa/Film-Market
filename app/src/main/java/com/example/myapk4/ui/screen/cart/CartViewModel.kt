package com.example.myapk4.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapk4.data.ProductRepository
import com.example.myapk4.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val repository: ProductRepository): ViewModel() {
    private val _uiState:MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderProduct(){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderProduct()
                .collect{orderProduct ->
                    val totalRequiredCost =
                        orderProduct.sumOf { it.product.cost * it.count  }
                    _uiState.value = UiState.Success(CartState(orderProduct,totalRequiredCost))
            }
        }


            }
    fun updateOrderProduct(productId: Long, count: Int){
        viewModelScope.launch {
            repository.updateOrderProduct(productId, count)
                .collect{ isUpdated ->
                    if (isUpdated) {
                        getAddedOrderProduct()
                    }
                }
        }
    }
}