package com.example.delivery_app.di

import com.example.delivery_app.data.api.ProductsApi
import com.example.delivery_app.data.model.Product
import com.example.delivery_app.data.model.ResponseModel
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.runBlocking
import org.mockito.Mockito

@Module
class TestApiModule {
    @Provides
    fun provideApi(): ProductsApi = runBlocking {
        val api = Mockito.mock(ProductsApi::class.java)
        val data = listOf(
            Product(
                title = "Телефон",
                description = "",
                price = 100,
                category = "phone",
                images = listOf("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ffunart.pro%2Fuploads%2Fposts%2F2021-04%2F1618111566_7-p-ochen-milie-kotiki-zhivotnie-krasivo-foto-7.jpg&f=1&nofb=1&ipt=730062aa28999a5d54f1a077a8e6f44cb3fa16f55585a1cde0cf54ac111862de&ipo=images")
            ),
            Product(
                title = "Айфон",
                description = "",
                price = 1000,
                category = "phone",
                images = listOf("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fconsequence.net%2Fwp-content%2Fuploads%2F2021%2F07%2Frick-astley.jpg%3Fresize%3D300&f=1&nofb=1&ipt=8dd051ebb2604c837b9266fab8fb1f6010848d7ef039509350d13c15ccfb791e&ipo=images")
            )
        )
        Mockito.`when`(api.getProducts()).thenReturn(ResponseModel(data))
        return@runBlocking api
    }
}