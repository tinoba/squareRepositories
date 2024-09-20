package com.square.repository.data.network

import com.square.repository.data.network.mapper.ApiMapper
import com.square.repository.domain.model.RepositoryItem
import javax.inject.Inject

class SquareRepositoryClientImpl @Inject constructor(
    private val api: SquareRepositoryService,
    private val apiMapper: ApiMapper
) : SquareRepositoryClient {
    override suspend fun getSquareRepositories(pageNumber: Int, pageSize: Int): List<RepositoryItem> {
        return api.getRepositoryList(pageNumber, pageSize)
            ?.map(apiMapper::toModel) ?: emptyList()
    }
}