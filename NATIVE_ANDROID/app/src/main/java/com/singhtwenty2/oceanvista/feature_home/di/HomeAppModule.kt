package com.singhtwenty2.oceanvista.feature_home.di

import android.content.Context
import android.content.SharedPreferences
import com.singhtwenty2.oceanvista.core.util.HttpBaseUrl
import com.singhtwenty2.oceanvista.feature_home.data.remote.BeachApiService
import com.singhtwenty2.oceanvista.feature_home.data.remote.LocationApiService
import com.singhtwenty2.oceanvista.feature_home.data.repository.BeachRepositoryImpl
import com.singhtwenty2.oceanvista.feature_home.data.repository.LocationRepositoryImpl
import com.singhtwenty2.oceanvista.feature_home.domain.repository.BeachRepository
import com.singhtwenty2.oceanvista.feature_home.domain.repository.LocationRepository
import com.singhtwenty2.oceanvista.feature_home.domain.worker.LocationWorker
import com.singhtwenty2.oceanvista.feature_home.presentation.home_screen.HomeViewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val homeAppModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(HttpBaseUrl.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(LocationApiService::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl(HttpBaseUrl.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(BeachApiService::class.java)
    }

    single<SharedPreferences> {
        get<Context>().getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    single<LocationRepository> {
        LocationRepositoryImpl(get(), get())
    }

    single {
        LocationWorker(get(), get(), get())
    }

    single<BeachRepository> {
        BeachRepositoryImpl(get(), get())
    }

    single {
        HomeViewModel(get())
    }
}