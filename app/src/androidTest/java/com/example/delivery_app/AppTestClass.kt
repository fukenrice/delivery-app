package com.example.delivery_app

import com.example.delivery_app.di.AppComponent
import com.example.delivery_app.di.DaggerTestAppComponent


class AppTestClass : App() {
    override fun initializeComponent(): AppComponent {
        return DaggerTestAppComponent.create()
    }
}