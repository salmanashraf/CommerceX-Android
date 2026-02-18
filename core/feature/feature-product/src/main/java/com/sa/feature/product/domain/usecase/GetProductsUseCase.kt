package com.sa.feature.product.domain.usecase

import androidx.paging.PagingData
import com.sa.feature.product.domain.model.Product
import com.sa.feature.product.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<PagingData<Product>> = repository.getProducts()
}
