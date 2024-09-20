package com.square.repository.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.square.repository.data.network.SquareRepositoryClient
import com.square.repository.data.repository.paging.SquareRepositoryPagingSource
import com.square.repository.domain.model.RepositoryItem
import com.square.repository.domain.repository.SquareRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SquareRepositoryImpl @Inject constructor(private val client: SquareRepositoryClient) : SquareRepository {
    override suspend fun getRepositoryListPagination(pageSize: Int, prefetchDistance: Int): Flow<PagingData<RepositoryItem>> {
        return Pager(
            config = PagingConfig(pageSize = pageSize, prefetchDistance = prefetchDistance),
            pagingSourceFactory = {
                SquareRepositoryPagingSource(client, pageSize)
            }
        ).flow
    }
}
