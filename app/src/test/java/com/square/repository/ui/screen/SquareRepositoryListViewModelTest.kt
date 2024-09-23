package com.square.repository.ui.screen

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import com.google.common.truth.Truth.assertThat
import com.square.MainCoroutineRule
import com.square.repository.data.FakeServiceImpl
import com.square.repository.data.network.SquareRepositoryClient
import com.square.repository.data.network.SquareRepositoryClientImpl
import com.square.repository.data.network.mapper.ApiMapper
import com.square.repository.data.network.mapper.ApiMapperImpl
import com.square.repository.data.repository.SquareRepositoryImpl
import com.square.repository.data.repository.paging.SquareRepositoryPagingSource
import com.square.repository.domain.repository.SquareRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SquareRepositoryListViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SquareRepositoryListViewModel

    private lateinit var client: SquareRepositoryClient
    private lateinit var repository: SquareRepository
    private lateinit var apiService: FakeServiceImpl
    private lateinit var apiMapper: ApiMapper

    @Before
    fun setUp() {
        apiService = FakeServiceImpl()
        apiMapper = ApiMapperImpl()
        client = SquareRepositoryClientImpl(apiService, apiMapper)
        repository = SquareRepositoryImpl(client)
        viewModel = SquareRepositoryListViewModel(repository)
    }

    @Test
    fun testRepositoryListIsNotEmpty() = runBlocking {
        assertTrue(
            client.getSquareRepositories(1, 1)
                .isNotEmpty()
        )
    }

    @Test
    fun testListSizeIsTwoWhenPageSizeIsTwo() = runBlocking {
        assertTrue(
            client.getSquareRepositories(1, 2).size == 2
        )
    }

    @Test
    fun testDomainListIsEmptyWhenResponseIsNull() = runBlocking {
        apiService.shouldReturnNull = true
        assertNotNull(client.getSquareRepositories(1, 1))
    }

    @Test
    fun loadReturnsPageWhenOnSuccessfulLoadOfItemKeyedData() = runBlocking {
        val pagingSource = SquareRepositoryPagingSource(
            client, 1
        )

        val pager = TestPager(PagingConfig(1), pagingSource)

        val result = pager.refresh() as PagingSource.LoadResult.Page

        val list = client.getSquareRepositories(1, 1)

        assertThat(result.data)
            .containsExactlyElementsIn(list)
            .inOrder()
    }
}