package com.example.myapk4.ui.screen.cart

import com.example.myapk4.model.OrderProduct

data class CartState(
    val orderProduct: List<OrderProduct>,
    val totalRequiredCost: Int
)