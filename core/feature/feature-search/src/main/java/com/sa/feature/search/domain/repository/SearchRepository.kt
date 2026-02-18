package com.sa.feature.search.domain.repository

import com.sa.feature.search.domain.model.SearchProduct

interface SearchRepository {
    suspend fun searchProducts(query: String): List<SearchProduct>
}
