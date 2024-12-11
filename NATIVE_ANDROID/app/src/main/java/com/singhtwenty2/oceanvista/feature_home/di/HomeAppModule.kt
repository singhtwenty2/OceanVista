package com.singhtwenty2.oceanvista.feature_home.di

import android.content.Context
import android.content.SharedPreferences
import com.singhtwenty2.oceanvista.core.util.HttpBaseUrl
import com.singhtwenty2.oceanvista.feature_home.data.remote.BeachApiService
import com.singhtwenty2.oceanvista.feature_home.data.remote.LocationApiService
import com.singhtwenty2.oceanvista.feature_home.data.remote.MapApiService
import com.singhtwenty2.oceanvista.feature_home.data.remote.ProfileApiService
import com.singhtwenty2.oceanvista.feature_home.data.repository.BeachRepositoryImpl
import com.singhtwenty2.oceanvista.feature_home.data.repository.LocationRepositoryImpl
import com.singhtwenty2.oceanvista.feature_home.data.repository.MapRepositoryImpl
import com.singhtwenty2.oceanvista.feature_home.data.repository.ProfileRepositoryImpl
import com.singhtwenty2.oceanvista.feature_home.domain.repository.BeachRepository
import com.singhtwenty2.oceanvista.feature_home.domain.repository.LocationRepository
import com.singhtwenty2.oceanvista.feature_home.domain.repository.MapRepository
import com.singhtwenty2.oceanvista.feature_home.domain.repository.ProfileRepository
import com.singhtwenty2.oceanvista.feature_home.domain.worker.LocationWorker
import com.singhtwenty2.oceanvista.feature_home.presentation.beach_detail_screen.BeachDetailViewModel
import com.singhtwenty2.oceanvista.feature_home.presentation.home_screen.HomeViewModel
import com.singhtwenty2.oceanvista.feature_home.presentation.map_screen.MapViewModel
import com.singhtwenty2.oceanvista.feature_home.presentation.profile_screen.ProfileViewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val homeAppModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(HttpBaseUrl.APP_ENGINE_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(LocationApiService::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl(HttpBaseUrl.APP_ENGINE_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(BeachApiService::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl(HttpBaseUrl.APP_ENGINE_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ProfileApiService::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl(HttpBaseUrl.MAP_ENGINE_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MapApiService::class.java)
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

    single<ProfileRepository> {
        ProfileRepositoryImpl(get(), get())
    }

    single {
        ProfileViewModel(get())
    }

    single<MapRepository> {
        MapRepositoryImpl(get())
    }

    single {
        MapViewModel(get())
    }

    factory { (beachId: Long) ->
        BeachDetailViewModel(beachRepository = get(), beachId = beachId)
    }

    single {
        HomeViewModel(get())
    }
}