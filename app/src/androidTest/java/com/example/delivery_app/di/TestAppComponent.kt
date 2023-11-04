package com.example.delivery_app.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestApiModule::class, DispatcherModule::class])
interface TestAppComponent : AppComponent