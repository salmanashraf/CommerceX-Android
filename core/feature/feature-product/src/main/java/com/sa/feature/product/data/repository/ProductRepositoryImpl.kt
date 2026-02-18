package com.sa.feature.product.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sa.feature.product.data.remote.ProductApiService
import com.sa.feature.product.data.remote.ProductPagingSource
import com.sa.feature.product.data.remote.toDomainModel
import com.sa.feature.product.domain.model.Product
import com.sa.feature.product.domain.model.ProductDetail
import com.sa.feature.product.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

private const val PAGE_SIZE = 30

class ProductRepositoryImpl(
    private val apiService: ProductApiService
) : ProductRepository {

    override fun getProducts(): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ProductPagingSource(apiService) }
        ).flow
    }

    override suspend fun getProductDetail(productId: Int): ProductDetail {
        return apiService.getProductDetail(productId).toDomainModel()
    }
}
