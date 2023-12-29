package com.example.myapk4.di

import com.example.myapk4.data.ProductRepository


object Injection {
    fun provideRepository(): ProductRepository {
        return ProductRepository.getInstance()
    }
}