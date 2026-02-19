package com.sa.feature.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sa.feature.auth.domain.model.AuthSession
import com.sa.feature.auth.domain.model.AuthUser
import com.sa.feature.auth.domain.usecase.LoginUseCase
import com.sa.feature.auth.domain.usecase.LogoutUseCase
import com.sa.feature.auth.domain.usecase.ObserveAuthSessionUseCase
import com.sa.feature.auth.domain.usecase.RefreshProfileUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    observeAuthSessionUseCase: ObserveAuthSessionUseCase
) : ViewModel() {

    private val authSession = observeAuthSessionUseCase()

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<LoginEvent>()
    val events: SharedFlow<LoginEvent> = _events.asSharedFlow()

    init {
        viewModelScope.launch {
            authSession.collectLatest { session ->
                if (session.isAuthenticated) {
                    _events.emit(LoginEvent.LoginSuccess)
                }
            }
        }
    }

    fun onUsernameChanged(value: String) {
        _uiState.update { it.copy(username = value, errorMessage = null) }
    }

    fun onPasswordChanged(value: String) {
        _uiState.update { it.copy(password = value, errorMessage = null) }
    }

    fun login() {
        val state = _uiState.value
        if (state.username.isBlank() || state.password.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Username and password are required") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = loginUseCase(state.username, state.password)
            if (result.isFailure) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Invalid credentials. Try emilys / emilyspass"
                    )
                }
            } else {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

sealed interface LoginEvent {
    data object LoginSuccess : LoginEvent
}

class ProfileViewModel(
    observeAuthSessionUseCase: ObserveAuthSessionUseCase,
    private val refreshProfileUseCase: RefreshProfileUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val authSession = observeAuthSessionUseCase()

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        observeSession()
    }

    private fun observeSession() {
        viewModelScope.launch {
            authSession.collectLatest { session ->
                _uiState.update { current ->
                    current.copy(
                        isLoading = session.isLoading,
                        isGuest = session.isGuest,
                        user = session.user
                    )
                }

                if (session.isAuthenticated) {
                    refreshProfile()
                }
            }
        }
    }

    fun showLogoutDialog(show: Boolean) {
        _uiState.update { it.copy(showLogoutDialog = show) }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
            _uiState.update { it.copy(showLogoutDialog = false) }
        }
    }

    private fun refreshProfile() {
        viewModelScope.launch {
            val result = refreshProfileUseCase()
            if (result.isFailure) {
                _uiState.update { it.copy(errorMessage = "Unable to refresh profile") }
            } else {
                _uiState.update { it.copy(errorMessage = null) }
            }
        }
    }
}

data class ProfileUiState(
    val isLoading: Boolean = true,
    val isGuest: Boolean = true,
    val user: AuthUser? = null,
    val showLogoutDialog: Boolean = false,
    val errorMessage: String? = null
)

class AuthSessionViewModel(
    observeAuthSessionUseCase: ObserveAuthSessionUseCase
) : ViewModel() {
    private val authSession = observeAuthSessionUseCase()
    val session: StateFlow<AuthSession> = authSession
}
