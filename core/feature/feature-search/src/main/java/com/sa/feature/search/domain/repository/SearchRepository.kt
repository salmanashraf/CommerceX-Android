package com.sa.feature.search.domain.repository

import com.sa.feature.search.domain.model.SearchProduct

interface SearchRepository {
    suspend fun getCategories(): List<String>
    suspend fun searchProducts(query: String, category: String): List<SearchProduct>
}
