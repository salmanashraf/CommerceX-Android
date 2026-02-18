package com.sa.feature.product.domain.model

data class ProductDetail(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val discountPercent: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnailUrl: String,
    val images: List<String>
)
