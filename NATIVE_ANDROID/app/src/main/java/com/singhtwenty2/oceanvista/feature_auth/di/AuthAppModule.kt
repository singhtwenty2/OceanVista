package com.singhtwenty2.oceanvista.feature_auth.di

import android.content.Context
import android.content.SharedPreferences
import com.singhtwenty2.oceanvista.feature_auth.data.remote.AuthService
import com.singhtwenty2.oceanvista.feature_auth.data.remote.repository.AuthRepositoryImpl
import com.singhtwenty2.oceanvista.feature_auth.domain.repository.AuthRepository
import com.singhtwenty2.oceanvista.feature_auth.presentation.login_sreen.LoginViewModel
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.RegisterViewModel
import com.singhtwenty2.oceanvista.feature_auth.util.ConnectionUrl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val authAppModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(ConnectionUrl.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(AuthService::class.java)
    }

    single<SharedPreferences> {
        get<Context>().getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    single<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }

    single {
        RegisterViewModel(get())
    }

    single {
        LoginViewModel(get())
    }
}