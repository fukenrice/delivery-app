package com.example.delivery_app.di

import com.example.delivery_app.ui.menu.MenuFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [])
interface AppComponent {
    fun inject(fragment: MenuFragment)
}