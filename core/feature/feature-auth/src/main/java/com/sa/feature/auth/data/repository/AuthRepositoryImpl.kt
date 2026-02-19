package com.sa.feature.auth.data.repository

import com.sa.feature.auth.data.local.AuthLocalDataSource
import com.sa.feature.auth.data.remote.AuthApiService
import com.sa.feature.auth.data.remote.LoginRequest
import com.sa.feature.auth.data.remote.toDomainUser
import com.sa.feature.auth.domain.model.AuthSession
import com.sa.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val apiService: AuthApiService,
    private val localDataSource: AuthLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AuthRepository {

    private val scope = CoroutineScope(SupervisorJob() + ioDispatcher)

    private val _session = MutableStateFlow(AuthSession(isLoading = true))
    override val session: StateFlow<AuthSession> = _session.asStateFlow()

    init {
        scope.launch { restoreSession() }
    }

    override suspend fun login(username: String, password: String): Result<Unit> = withContext(ioDispatcher) {
        runCatching {
            val response = apiService.login(
                request = LoginRequest(
                    username = username,
                    password = password
                )
            )
            val user = response.toDomainUser()
            localDataSource.saveAuth(token = response.accessToken, user = user)
            _session.value = AuthSession(
                isLoading = false,
                token = response.accessToken,
                user = user
            )
        }
    }

    override suspend fun refreshProfile(): Result<Unit> = withContext(ioDispatcher) {
        val current = _session.value
        if (current.token.isNullOrBlank() || current.user == null) {
            return@withContext Result.success(Unit)
        }

        runCatching {
            val user = apiService.getUser(current.user.id).toDomainUser()
            localDataSource.saveAuth(current.token, user)
            _session.value = current.copy(user = user, isLoading = false)
        }
    }

    override suspend fun logout() = withContext(ioDispatcher) {
        localDataSource.clear()
        _session.value = AuthSession(isLoading = false)
    }

    private suspend fun restoreSession() {
        val token = localDataSource.getToken()
        val cachedUser = localDataSource.getCachedUser()

        if (token.isNullOrBlank() || cachedUser == null) {
            _session.value = AuthSession(isLoading = false)
            return
        }

        _session.value = AuthSession(
            isLoading = false,
            token = token,
            user = cachedUser
        )

        refreshProfile()
    }
}
