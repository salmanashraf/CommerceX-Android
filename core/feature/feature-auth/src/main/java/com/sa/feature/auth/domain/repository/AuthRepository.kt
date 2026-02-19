package com.sa.feature.auth.domain.repository

import com.sa.feature.auth.domain.model.AuthSession
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val session: StateFlow<AuthSession>

    suspend fun login(username: String, password: String): Result<Unit>
    suspend fun refreshProfile(): Result<Unit>
    suspend fun logout()
}
