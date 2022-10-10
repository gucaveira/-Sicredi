package com.example.sicredi.di

import com.example.core.data.repository.EventsRemoteDataSource
import com.example.core.data.repository.EventsRepository
import com.example.core.usecase.CheckInEventUseCase
import com.example.core.usecase.CheckInEventUseCaseImpl
import com.example.core.usecase.GetEventUseCaseImpl
import com.example.core.usecase.GetEventsUseCase
import com.example.core.usecase.base.AppCoroutinesDispatchers
import com.example.core.usecase.base.CoroutinesDispatchers
import com.example.sicredi.BuildConfig.BASE_URL
import com.example.sicredi.framework.EventsRepositoryImpl
import com.example.sicredi.framework.imageLoader.GlideImageLoader
import com.example.sicredi.framework.imageLoader.ImageLoader
import com.example.sicredi.framework.network.Api
import com.example.sicredi.presentation.eventslist.EventsViewModel
import com.example.sicredi.remote.RetrofitDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<Api> { get<Retrofit>().create(Api::class.java) }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory<EventsRemoteDataSource> { RetrofitDataSource(get()) }
}

val viewModelModule = module {
    viewModel { EventsViewModel(get(), get()) }
}

val imageLoaderModule = module {
    factory<ImageLoader> { GlideImageLoader() }
}

val useCaseModule = module {
    factory<GetEventsUseCase> { GetEventUseCaseImpl(get(), get()) }
    factory<CheckInEventUseCase> { CheckInEventUseCaseImpl(get(), get()) }
}

val coroutinesDispatchersModule = module {
    factory<CoroutinesDispatchers> { AppCoroutinesDispatchers() }
}

val repositoryModule = module {
    factory<EventsRepository> { EventsRepositoryImpl(get()) }
}