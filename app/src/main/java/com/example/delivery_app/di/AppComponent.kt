package com.example.delivery_app.di

import com.example.delivery_app.ui.menu.MenuFragment
import com.example.delivery_app.ui.menu.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, DispatcherModule::class])
interface AppComponent {
    fun inject(fragment: MenuFragment)
    fun viewModelFactory(): ViewModelFactory
}