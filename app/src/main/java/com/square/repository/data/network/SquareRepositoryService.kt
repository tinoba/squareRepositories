package com.square.repository.data.network

import com.square.repository.data.network.model.RepositoryItemResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SquareRepositoryService {
    @GET("orgs/square/repos")
    suspend fun getRepositoryList(
        @Query("page") pageNumber: Int,
        @Query("per_page") pageSize: Int
    ): List<RepositoryItemResponse?>?
}