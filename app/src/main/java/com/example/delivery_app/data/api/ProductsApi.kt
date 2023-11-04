package com.example.delivery_app.data.api

import com.example.delivery_app.data.model.ResponseModel
import retrofit2.http.GET

interface ProductsApi {
    @GET("products")
    suspend fun getProducts(): ResponseModel
}