package com.sa.feature.product.domain.usecase

import com.sa.feature.product.domain.model.ProductDetail
import com.sa.feature.product.domain.repository.ProductRepository

class GetProductDetailUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productId: Int): ProductDetail {
        return repository.getProductDetail(productId)
    }
}
