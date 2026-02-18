package com.sa.feature.product.domain.repository

import androidx.paging.PagingData
import com.sa.feature.product.domain.model.Product
import com.sa.feature.product.domain.model.ProductDetail
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<PagingData<Product>>
    suspend fun getProductDetail(productId: Int): ProductDetail
}
