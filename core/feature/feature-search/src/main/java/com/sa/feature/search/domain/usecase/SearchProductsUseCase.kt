package com.sa.feature.search.domain.usecase

import com.sa.feature.search.domain.model.SearchProduct
import com.sa.feature.search.domain.repository.SearchRepository

class SearchProductsUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(query: String): List<SearchProduct> {
        return repository.searchProducts(query)
    }
}
