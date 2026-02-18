package com.sa.feature.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sa.feature.search.domain.model.SearchProduct
import com.sa.feature.search.domain.usecase.SearchProductsUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState(isLoading = true))
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val queryFlow = MutableStateFlow("")

    init {
        observeQuery()
    }

    @OptIn(FlowPreview::class)
    private fun observeQuery() {
        viewModelScope.launch {
            queryFlow
                .debounce(300)
                .distinctUntilChanged()
                .collect { query ->
                    search(query)
                }
        }
    }

    fun onQueryChanged(value: String) {
        _uiState.update { it.copy(query = value) }
        queryFlow.value = value
    }

    fun onSearchTriggered() {
        queryFlow.value = _uiState.value.query
    }

    fun clearSearch() {
        onQueryChanged("")
    }

    private suspend fun search(query: String) {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        runCatching { searchProductsUseCase(query) }
            .onSuccess { products ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        results = products,
                        errorMessage = null
                    )
                }
            }
            .onFailure {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        results = emptyList(),
                        errorMessage = "Unable to load search results."
                    )
                }
            }
    }
}

data class SearchUiState(
    val query: String = "",
    val results: List<SearchProduct> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
