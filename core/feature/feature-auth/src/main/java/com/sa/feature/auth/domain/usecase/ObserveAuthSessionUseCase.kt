package com.sa.feature.auth.domain.usecase

import com.sa.feature.auth.domain.model.AuthSession
import com.sa.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.StateFlow

class ObserveAuthSessionUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(): StateFlow<AuthSession> = repository.session
}
