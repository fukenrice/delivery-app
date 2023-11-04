package com.example.delivery_app.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class DispatcherModule {
    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO
}