package com.sa.feature.auth.domain.model

data class AuthUser(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val username: String,
    val imageUrl: String
) {
    val initials: String
        get() = buildString {
            firstName.firstOrNull()?.let(::append)
            lastName.firstOrNull()?.let(::append)
        }.ifBlank { "U" }
}
