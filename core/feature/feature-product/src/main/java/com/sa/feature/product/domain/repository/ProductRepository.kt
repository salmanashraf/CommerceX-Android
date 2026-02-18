package com.sa.feature.product.domain.repository

import androidx.paging.PagingData
import com.sa.feature.product.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<PagingData<Product>>
}
