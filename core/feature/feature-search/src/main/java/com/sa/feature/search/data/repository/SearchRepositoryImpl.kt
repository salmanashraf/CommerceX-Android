package com.sa.feature.search.data.repository

import com.sa.feature.search.data.remote.SearchApiService
import com.sa.feature.search.data.remote.toDomainModel
import com.sa.feature.search.domain.model.SearchProduct
import com.sa.feature.search.domain.repository.SearchRepository

private const val DEFAULT_LIMIT = 30
private const val ALL_CATEGORY = "All"

class SearchRepositoryImpl(
    private val apiService: SearchApiService
) : SearchRepository {

    override suspend fun getCategories(): List<String> {
        return listOf(ALL_CATEGORY) + apiService.getCategories()
    }

    override suspend fun searchProducts(query: String, category: String): List<SearchProduct> {
        val response = when {
            query.isBlank() && category == ALL_CATEGORY -> {
                apiService.getProducts(limit = DEFAULT_LIMIT, skip = 0)
            }
            query.isBlank() && category != ALL_CATEGORY -> {
                apiService.getProductsByCategory(category = category)
            }
            else -> {
                apiService.searchProducts(query = query)
            }
        }

        val mapped = response.products.map { it.toDomainModel() }
        return if (category == ALL_CATEGORY) mapped else mapped.filter { it.category == category }
    }
}
