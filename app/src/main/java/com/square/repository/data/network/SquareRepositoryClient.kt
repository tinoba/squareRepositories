package com.square.repository.data.network

import com.square.repository.domain.model.RepositoryItem

interface SquareRepositoryClient {
    suspend fun getSquareRepositories(pageNumber:Int, pageSize: Int): List<RepositoryItem>
}