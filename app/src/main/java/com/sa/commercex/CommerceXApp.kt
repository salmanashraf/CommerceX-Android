package com.sa.commercex

import android.app.Application
import com.sa.core.network.di.networkModule
import com.sa.feature.cart.di.cartModule
import com.sa.feature.product.di.productModule
import com.sa.feature.search.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CommerceXApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CommerceXApp)
            modules(networkModule, cartModule, productModule, searchModule)
        }
    }
}
