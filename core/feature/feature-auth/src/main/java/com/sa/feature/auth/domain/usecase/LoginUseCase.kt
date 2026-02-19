package com.sa.feature.auth.domain.usecase

import com.sa.feature.auth.domain.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<Unit> {
        return repository.login(username.trim(), password)
    }
}
