package com.square.repository.domain.repository

import androidx.paging.PagingData
import com.square.repository.domain.model.RepositoryItem
import kotlinx.coroutines.flow.Flow

interface SquareRepository {
    suspend fun getRepositoryListPagination(pageSize: Int, prefetchDistance: Int): Flow<PagingData<RepositoryItem>>
}