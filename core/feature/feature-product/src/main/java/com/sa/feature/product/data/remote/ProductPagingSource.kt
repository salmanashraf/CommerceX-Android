package com.sa.feature.product.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sa.feature.product.domain.model.Product

private const val NETWORK_PAGE_SIZE = 30

class ProductPagingSource(
    private val apiService: ProductApiService
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val skip = params.key ?: 0
            val response = apiService.getProducts(limit = NETWORK_PAGE_SIZE, skip = skip)
            val items = response.products.map { it.toDomainModel() }

            LoadResult.Page(
                data = items,
                prevKey = if (skip == 0) null else (skip - NETWORK_PAGE_SIZE).coerceAtLeast(0),
                nextKey = if (skip + items.size >= response.total) null else skip + items.size
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(NETWORK_PAGE_SIZE) ?: page.nextKey?.minus(NETWORK_PAGE_SIZE)
    }
}
