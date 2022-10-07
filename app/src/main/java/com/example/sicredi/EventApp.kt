package com.example.sicredi

import android.app.Application
import com.example.sicredi.di.coroutinesDispatchersModule
import com.example.sicredi.di.imageLoaderModule
import com.example.sicredi.di.networkModule
import com.example.sicredi.di.repositoryModule
import com.example.sicredi.di.useCaseModule
import com.example.sicredi.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class EventApp : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@EventApp)
            modules(
                listOf(
                    networkModule,
                    viewModelModule,
                    repositoryModule,
                    useCaseModule,
                    coroutinesDispatchersModule,
                    imageLoaderModule
                )
            )
        }
    }
}