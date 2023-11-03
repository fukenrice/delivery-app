package com.example.delivery_app.data.model

data class Product(
    val title: String,
    val description: String,
    val price: Int,
    val images: List<String>,
    val category: String
)
