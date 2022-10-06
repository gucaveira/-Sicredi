package com.example.sicredi

import android.app.Application
import com.example.sicredi.di.coroutinesDispatchersModule
import com.example.sicredi.di.networkModule
import com.example.sicredi.di.repositoryModule
import com.example.sicredi.di.useCaseModule
import com.example.sicredi.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class SicrediApp : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@SicrediApp)
            modules(
                listOf(
                    networkModule,
                    viewModelModule,
                    repositoryModule,
                    useCaseModule,
                    coroutinesDispatchersModule
                )
            )
        }
    }
}