package com.square.repository.data

import com.square.repository.data.network.SquareRepositoryService
import com.square.repository.data.network.model.RepositoryItemResponse
import javax.inject.Inject

class FakeServiceImpl @Inject constructor(

) : SquareRepositoryService {

    var shouldReturnNull = false

    private val repositoryList = listOf(
        RepositoryItemResponse(
            id = 1,
            name = "Alpha",
            fullName = "AlphaFullName",
            htmlUrl = "https://github.com/Alpha",
            description = "Alpha repository description",
            url = "https://api.github.com/repos/Alpha"
        ),
        RepositoryItemResponse(
            id = 2,
            name = "Beta",
            fullName = "BetaFullName",
            htmlUrl = "https://github.com/Beta",
            description = "Beta repository description",
            url = "https://api.github.com/repos/Beta"
        ),
        RepositoryItemResponse(
            id = 3,
            name = "Gamma",
            fullName = "GammaFullName",
            htmlUrl = "https://github.com/Gamma",
            description = "Gamma repository description",
            url = "https://api.github.com/repos/Gamma"
        )
    )

    override suspend fun getRepositoryList(pageNumber: Int, pageSize: Int): List<RepositoryItemResponse?>? {
        val response = if (shouldReturnNull) null else repositoryList

        return response?.take(pageSize)
    }
}