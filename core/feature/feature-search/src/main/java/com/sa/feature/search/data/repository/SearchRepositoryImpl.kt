package com.sa.feature.search.data.repository

import com.sa.feature.search.data.remote.SearchApiService
import com.sa.feature.search.data.remote.toDomainModel
import com.sa.feature.search.domain.model.SearchProduct
import com.sa.feature.search.domain.repository.SearchRepository

private const val DEFAULT_LIMIT = 30

class SearchRepositoryImpl(
    private val apiService: SearchApiService
) : SearchRepository {

    override suspend fun searchProducts(query: String): List<SearchProduct> {
        val response = if (query.isBlank()) {
            apiService.getProducts(limit = DEFAULT_LIMIT, skip = 0)
        } else {
            apiService.searchProducts(query = query)
        }
        return response.products.map { it.toDomainModel() }
    }
}
