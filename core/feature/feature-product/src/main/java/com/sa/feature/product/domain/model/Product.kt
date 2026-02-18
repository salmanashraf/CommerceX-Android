package com.sa.feature.product.domain.model

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val rating: Double,
    val thumbnailUrl: String
)
