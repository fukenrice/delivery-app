package com.example.delivery_app.ui.menu

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.delivery_app.MainCoroutineScopeRule
import com.example.delivery_app.data.api.ProductsApi
import com.example.delivery_app.data.model.Product
import com.example.delivery_app.data.model.ResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito


class MenuViewModelTest {

    private lateinit var viewModel: MenuViewModel
    private lateinit var api: ProductsApi
    private val data = listOf(
        Product(
            title = "Телефон",
            description = "",
            price = 100,
            category = "phone",
            images = listOf()
        ),
        Product(
            title = "Айфон",
            description = "",
            price = 1000,
            category = "phone",
            images = listOf()
        )
    )
    private val response = ResponseModel(data)


    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        api = Mockito.mock(ProductsApi::class.java)
        viewModel = MenuViewModel(api, Dispatchers.Main)
    }

    @Test
    fun fetchDataSuccess() = runTest {
        Mockito.`when`(api.getProducts()).thenReturn(response)
        val expectedList = listOf(
            Product(
                title = "Телефон",
                description = "",
                price = 100,
                category = "phone",
                images = listOf()
            ),
            Product(
                title = "Айфон",
                description = "",
                price = 1000,
                category = "phone",
                images = listOf()
            )
        )
        viewModel.fetchData()
        assertEquals(expectedList, viewModel.productsList.value!!.data)
    }

    @Test
    fun fetchDataError() = runTest {
        Mockito.`when`(api.getProducts()).thenThrow(RuntimeException("exception"))
        viewModel.fetchData()
        val expectedMessage = "Ошибка получения данных, проверьте подключение к сети"
        assertEquals(expectedMessage, viewModel.productsList.value!!.message)
    }

    @Test
    fun isEmpty() {
        assertTrue(viewModel.isEmpty())
    }

    @Test
    fun isNotEmptyEmpty() = runTest {
        Mockito.`when`(api.getProducts()).thenReturn(response)
        viewModel.fetchData()
        assertFalse(viewModel.isEmpty())
    }
}