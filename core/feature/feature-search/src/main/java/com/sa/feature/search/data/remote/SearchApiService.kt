package com.sa.feature.search.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApiService {
    @GET("products/search")
    suspend fun searchProducts(
        @Query("q") query: String
    ): SearchProductsResponse

    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): SearchProductsResponse

    @GET("products/categories")
    suspend fun getCategories(): List<String>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String
    ): SearchProductsResponse
}
