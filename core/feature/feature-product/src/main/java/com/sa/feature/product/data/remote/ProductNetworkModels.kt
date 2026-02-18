package com.sa.feature.product.data.remote

import com.sa.feature.product.domain.model.Product
import com.sa.feature.product.domain.model.ProductDetail

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

data class NetworkProductDetail(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
)

fun NetworkProduct.toDomainModel(): Product = Product(
    id = id,
    title = title,
    price = price,
    rating = rating,
    thumbnailUrl = thumbnail
)

fun NetworkProductDetail.toDomainModel(): ProductDetail = ProductDetail(
    id = id,
    title = title,
    description = description,
    price = price,
    discountPercent = discountPercentage,
    rating = rating,
    stock = stock,
    brand = brand,
    category = category,
    thumbnailUrl = thumbnail,
    images = images
)
