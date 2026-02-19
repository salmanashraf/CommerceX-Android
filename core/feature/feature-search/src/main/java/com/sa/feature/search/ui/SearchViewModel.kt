package com.sa.feature.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sa.feature.search.domain.model.SearchProduct
import com.sa.feature.search.domain.usecase.SearchProductsUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val ALL_CATEGORY = "All"

class SearchViewModel(
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState(isLoading = true, selectedCategory = ALL_CATEGORY))
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val queryFlow = MutableStateFlow("")
    private val categoryFlow = MutableStateFlow(ALL_CATEGORY)

    init {
        loadCategories()
        observeSearchInputs()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            runCatching { searchProductsUseCase.getCategories() }
                .onSuccess { categories ->
                    _uiState.update { it.copy(categories = categories) }
                }
                .onFailure {
                    _uiState.update { it.copy(categories = listOf(ALL_CATEGORY)) }
                }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchInputs() {
        viewModelScope.launch {
            combine(
                queryFlow.debounce(300),
                categoryFlow
            ) { query, category -> query to category }
                .distinctUntilChanged()
                .collect { (query, category) ->
                    search(query = query, category = category)
                }
        }
    }

    fun onQueryChanged(value: String) {
        _uiState.update { it.copy(query = value) }
        queryFlow.value = value
        if (value.isBlank()) {
            onCategorySelected(ALL_CATEGORY)
        }
    }

    fun onCategorySelected(category: String) {
        _uiState.update { it.copy(selectedCategory = category) }
        categoryFlow.value = category
    }

    fun onSearchTriggered() {
        queryFlow.value = _uiState.value.query
    }

    private suspend fun search(query: String, category: String) {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        runCatching { searchProductsUseCase(query, category) }
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
    val categories: List<String> = listOf(ALL_CATEGORY),
    val selectedCategory: String = ALL_CATEGORY,
    val results: List<SearchProduct> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
