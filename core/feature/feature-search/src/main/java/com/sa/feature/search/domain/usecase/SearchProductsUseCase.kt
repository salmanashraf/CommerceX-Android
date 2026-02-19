package com.sa.feature.search.domain.usecase

import com.sa.feature.search.domain.model.SearchProduct
import com.sa.feature.search.domain.repository.SearchRepository

class SearchProductsUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(query: String, category: String): List<SearchProduct> {
        return repository.searchProducts(query, category)
    }

    suspend fun getCategories(): List<String> = repository.getCategories()
}
