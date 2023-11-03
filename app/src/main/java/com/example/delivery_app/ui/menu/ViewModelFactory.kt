package com.example.delivery_app.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

// Решение таким способом ижектить вьюмодель подсмотрено с https://habr.com/ru/companies/wrike/articles/569918/
class ViewModelFactory @Inject constructor(provider: Provider<MenuViewModel>) : ViewModelProvider.Factory {
    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        MenuViewModel::class.java to provider
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}