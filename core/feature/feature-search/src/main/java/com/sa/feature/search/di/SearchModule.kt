package com.sa.feature.search.di

import com.sa.feature.search.data.remote.SearchApiService
import com.sa.feature.search.data.repository.SearchRepositoryImpl
import com.sa.feature.search.domain.repository.SearchRepository
import com.sa.feature.search.domain.usecase.SearchProductsUseCase
import com.sa.feature.search.ui.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val searchModule: Module = module {
    single<SearchApiService> { get<Retrofit>().create(SearchApiService::class.java) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
    factory { SearchProductsUseCase(get()) }
    viewModel { SearchViewModel(get()) }
}
