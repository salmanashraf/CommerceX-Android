package com.sa.feature.product.data.remote

import com.sa.feature.product.domain.model.Product

data class ProductsResponse(
    val products: List<NetworkProduct>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

data class NetworkProduct(
    val id: Int,
    val title: String,
    val price: Double,
    val rating: Double,
    val thumbnail: String
)

fun NetworkProduct.toDomainModel(): Product = Product(
    id = id,
    title = title,
    price = price,
    rating = rating,
    thumbnailUrl = thumbnail
)
