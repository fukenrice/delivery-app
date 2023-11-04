package com.example.delivery_app.ui.menu

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.delivery_app.R
import com.example.delivery_app.data.api.ProductsApi
import com.example.delivery_app.data.model.Product
import com.example.delivery_app.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MenuViewModel"

class MenuViewModel @Inject constructor(
    private val api: ProductsApi, private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    val productsList = MutableLiveData<Resource<List<Product>>>(Resource.loading(listOf()))

    val banners = MutableLiveData(listOf(R.drawable.banner1, R.drawable.banner2))

    val categories = MutableLiveData(
        mutableMapOf(
            "Пицца" to true,
            "Комбо" to false,
            "Дессерты" to false,
            "Напитки" to false
        )
    )

    var selectedCategory = "Пицца"

    fun fetchData() {
        viewModelScope.launch(dispatcher) {
            try {
                productsList.postValue(Resource.loading(listOf()))
                val products = api.getProducts()
                Log.d(TAG, "fetchData: " + products)
                productsList.postValue(Resource.success(products.products))
            } catch (e: Throwable) {
                productsList.postValue(
                    Resource.error(
                        "Ошибка получения данных, проверьте подключение к сети",
                        listOf()
                    )
                )
                Log.d(TAG, "getRegions: " + e.message)
            }
        }

    }

    fun isEmpty() = productsList.value?.data?.isEmpty() ?: true
}