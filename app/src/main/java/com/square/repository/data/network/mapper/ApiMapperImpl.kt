package com.square.repository.data.network.mapper

import com.square.repository.data.network.model.RepositoryItemResponse
import com.square.repository.domain.model.RepositoryItem

class ApiMapperImpl : ApiMapper {
    override fun toModel(response: RepositoryItemResponse?): RepositoryItem {
        return response?.let {
            RepositoryItem(
                it.id ?: 0,
                it.name.orEmpty(),
                it.fullName.orEmpty(),
                it.htmlUrl.orEmpty(),
                it.description.orEmpty(),
                it.url.orEmpty()
            )
        } ?: RepositoryItem.EMPTY
    }
}