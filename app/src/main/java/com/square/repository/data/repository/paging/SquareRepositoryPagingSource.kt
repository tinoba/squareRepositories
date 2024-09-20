package com.square.repository.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.square.repository.data.network.SquareRepositoryClient
import com.square.repository.domain.model.RepositoryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SquareRepositoryPagingSource @Inject constructor(
    private val remoteDataSource: SquareRepositoryClient,
    private val pageSize: Int,
) : PagingSource<Int, RepositoryItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryItem> {
        return try {
            val currentPage = params.key ?: 1
            val repositories = withContext(Dispatchers.IO) {
                remoteDataSource.getSquareRepositories(
                    pageNumber = currentPage,
                    pageSize = pageSize
                )
            }

            LoadResult.Page(
                data = repositories,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (repositories.isEmpty()) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RepositoryItem>): Int? {
        return state.anchorPosition
    }
}
