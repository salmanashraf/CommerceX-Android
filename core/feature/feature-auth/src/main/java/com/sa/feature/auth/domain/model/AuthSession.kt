package com.sa.feature.auth.domain.model

data class AuthSession(
    val isLoading: Boolean = false,
    val token: String? = null,
    val user: AuthUser? = null
) {
    val isAuthenticated: Boolean
        get() = !token.isNullOrBlank() && user != null

    val isGuest: Boolean
        get() = !isAuthenticated
}
