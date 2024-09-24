package com.square.repository.data.network.mapper

import com.square.repository.data.network.model.RepositoryItemResponse
import com.square.repository.domain.model.RepositoryItem

interface RepositoryItemApiMapper {
    fun toModel(response: RepositoryItemResponse?): RepositoryItem
}