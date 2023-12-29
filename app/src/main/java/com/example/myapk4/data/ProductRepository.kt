package com.example.myapk4.data

import com.example.myapk4.model.OrderProduct
import com.example.myapk4.model.Product
import com.example.myapk4.model.ProductDataResource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepository {

    private var orderProducts = mutableListOf<OrderProduct>()
    private var products = mutableListOf<Product>()

    init {
        if(orderProducts.isEmpty()){
            ProductDataResource.dummyProduct.forEach{
                orderProducts.add(OrderProduct(it,0, ))
            }
        }
    }

    fun getAllProduct(searchQuery: String = ""): Flow<List<OrderProduct>> {
        return if (searchQuery.isBlank()) {
            flowOf(orderProducts)
        } else {
            flowOf(
                orderProducts.filter {
                    it.product.nameProduct.contains(searchQuery, ignoreCase = true)
                }
            )
        }
    }


    fun getOrderProductById(productId: Long): OrderProduct{
        return orderProducts.first(){
            it.product.id == productId
        }
    }


    fun updateOrderProduct (productId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderProducts.indexOfFirst { it.product.id == productId }
        val result = if (index >= 0) {
            val orderProduct = orderProducts[index]
            orderProducts[index] =
                orderProduct.copy(product = orderProduct.product, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderProduct(): Flow<List<OrderProduct>> {
        return getAllProduct()
            .map { orderProducts ->
                orderProducts.filter { orderProduct ->
                    orderProduct.count != 0
                }
            }
    }

//    fun search(searchQuery: String = ""): Flow<List<OrderProduct>> {
//        return flowOf(
//            orderProducts.filter {
//                it.product.nameProduct.contains(searchQuery, ignoreCase = true)
//            }
//        )
//    }


    companion object {
        @Volatile
        private var instance: ProductRepository? = null

        fun getInstance(): ProductRepository =
            instance ?: synchronized(this) {
                ProductRepository().apply {
                    instance = this
                }
            }
    }
}