package com.sa.feature.auth.data.remote

import com.sa.feature.auth.domain.model.AuthUser

data class LoginRequest(
    val username: String,
    val password: String,
    val expiresInMins: Int = 60
)

data class LoginResponse(
    val accessToken: String,
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val username: String,
    val image: String?
)

data class NetworkUser(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val username: String,
    val image: String?
)

fun LoginResponse.toDomainUser(): AuthUser = AuthUser(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    username = username,
    imageUrl = image.orEmpty()
)

fun NetworkUser.toDomainUser(): AuthUser = AuthUser(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    username = username,
    imageUrl = image.orEmpty()
)
