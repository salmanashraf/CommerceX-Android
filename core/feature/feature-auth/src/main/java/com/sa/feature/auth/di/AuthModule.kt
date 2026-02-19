package com.sa.feature.auth.di

import com.sa.feature.auth.data.local.AuthLocalDataSource
import com.sa.feature.auth.data.local.TokenCipher
import com.sa.feature.auth.data.remote.AuthApiService
import com.sa.feature.auth.data.repository.AuthRepositoryImpl
import com.sa.feature.auth.domain.repository.AuthRepository
import com.sa.feature.auth.domain.usecase.LoginUseCase
import com.sa.feature.auth.domain.usecase.LogoutUseCase
import com.sa.feature.auth.domain.usecase.ObserveAuthSessionUseCase
import com.sa.feature.auth.domain.usecase.RefreshProfileUseCase
import com.sa.feature.auth.ui.AuthSessionViewModel
import com.sa.feature.auth.ui.LoginViewModel
import com.sa.feature.auth.ui.ProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val authModule: Module = module {
    single<AuthApiService> { get<Retrofit>().create(AuthApiService::class.java) }
    single { TokenCipher() }
    single { AuthLocalDataSource(androidContext(), get()) }

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    factory { LoginUseCase(get()) }
    factory { LogoutUseCase(get()) }
    factory { ObserveAuthSessionUseCase(get()) }
    factory { RefreshProfileUseCase(get()) }

    viewModel { LoginViewModel(get(), get()) }
    viewModel { ProfileViewModel(get(), get(), get()) }
    viewModel { AuthSessionViewModel(get()) }
}
