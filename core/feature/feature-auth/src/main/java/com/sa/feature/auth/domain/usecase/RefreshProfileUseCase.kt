package com.sa.feature.auth.domain.usecase

import com.sa.feature.auth.domain.repository.AuthRepository

class RefreshProfileUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): Result<Unit> = repository.refreshProfile()
}
