package com.example.delivery_app

import android.app.Application
import com.example.delivery_app.di.AppComponent
import com.example.delivery_app.di.DaggerAppComponent

open class App : Application() {
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.create()
    }
}