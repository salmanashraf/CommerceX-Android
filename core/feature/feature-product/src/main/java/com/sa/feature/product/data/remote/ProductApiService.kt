package com.sa.feature.product.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): ProductsResponse
}
