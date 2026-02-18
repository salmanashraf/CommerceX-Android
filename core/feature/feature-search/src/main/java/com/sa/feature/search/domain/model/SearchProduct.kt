package com.sa.feature.search.domain.model

data class SearchProduct(
    val id: Int,
    val title: String,
    val price: Double,
    val rating: Double,
    val thumbnailUrl: String,
    val discountPercent: Double,
    val reviewCount: Int
)
