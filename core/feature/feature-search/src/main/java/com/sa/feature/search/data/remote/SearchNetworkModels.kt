package com.sa.feature.search.data.remote

import com.sa.feature.search.domain.model.SearchProduct

data class SearchProductsResponse(
    val products: List<NetworkSearchProduct>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

data class NetworkSearchProduct(
    val id: Int,
    val title: String,
    val price: Double,
    val rating: Double,
    val thumbnail: String,
    val category: String,
    val discountPercentage: Double,
    val stock: Int
)

fun NetworkSearchProduct.toDomainModel(): SearchProduct = SearchProduct(
    id = id,
    title = title,
    price = price,
    rating = rating,
    thumbnailUrl = thumbnail,
    category = category,
    discountPercent = discountPercentage,
    reviewCount = stock
)
