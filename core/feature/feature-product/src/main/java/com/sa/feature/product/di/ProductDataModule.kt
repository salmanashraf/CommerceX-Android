package com.sa.feature.product.di

import com.sa.feature.product.data.remote.ProductApiService
import com.sa.feature.product.data.repository.ProductRepositoryImpl
import com.sa.feature.product.domain.repository.ProductRepository
import com.sa.feature.product.domain.usecase.GetProductDetailUseCase
import com.sa.feature.product.domain.usecase.GetProductsUseCase
import com.sa.feature.product.ui.ProductDetailViewModel
import com.sa.feature.product.ui.ProductListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val productModule: Module = module {
    single<ProductApiService> { get<Retrofit>().create(ProductApiService::class.java) }
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    factory { GetProductsUseCase(get()) }
    factory { GetProductDetailUseCase(get()) }
    viewModel { ProductListViewModel(get()) }
    viewModel { (productId: Int) -> ProductDetailViewModel(productId, get()) }
}
