package com.sa.feature.cart.di

import androidx.room.Room
import com.sa.feature.cart.data.local.CartDatabase
import com.sa.feature.cart.data.repository.CartRepositoryImpl
import com.sa.feature.cart.domain.repository.CartRepository
import com.sa.feature.cart.ui.CartViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

private const val CART_DB_NAME = "commercex_cart.db"

val cartModule: Module = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            CartDatabase::class.java,
            CART_DB_NAME
        ).build()
    }

    single { get<CartDatabase>().cartDao() }
    single<CartRepository> { CartRepositoryImpl(get()) }
    viewModel { CartViewModel(get()) }
}
