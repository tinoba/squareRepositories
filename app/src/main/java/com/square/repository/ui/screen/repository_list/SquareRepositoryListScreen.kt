package com.square.repository.ui.screen.repository_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.square.repository.domain.model.RepositoryItem
import com.square.repository.ui.screen.SquareRepositoryListViewModel
import com.square.repository.utils.ObserveAsEvents
import kotlinx.coroutines.flow.flowOf

@Composable
fun SquareRepositoryListScreen(
    viewModel: SquareRepositoryListViewModel = hiltViewModel()
) {
    val repositoryPagingItems: LazyPagingItems<RepositoryItem> = viewModel.repositoryState.collectAsLazyPagingItems()
    val uriHandler = LocalUriHandler.current

    ObserveAsEvents(viewModel.openUrlRequest) {
        uriHandler.openUri(it)
    }

    SquareRepositoryListContent(repositoryPagingItems) { viewModel.openUrl(it) }
}

@Composable
@Preview
fun SquareRepositoryListScreenPreview() {
    SquareRepositoryListContent(
        flowOf(
            PagingData.from(
                listOf(
                    RepositoryItem(1, "Tino Balint", "https://github.com/tino-balint/SquareRepositories", "Some description"),
                    RepositoryItem(1, "Tino Balint", "https://github.com/tino-balint/SquareRepositories", "Some description"),
                    RepositoryItem(1, "Tino Balint", "https://github.com/tino-balint/SquareRepositories", "Some description"),
                    RepositoryItem(1, "Tino Balint", "https://github.com/tino-balint/SquareRepositories", "Some description")
                ), sourceLoadStates =
                LoadStates(
                    refresh = LoadState.NotLoading(true),
                    append = LoadState.NotLoading(true),
                    prepend = LoadState.NotLoading(true),
                )
            )
        ).collectAsLazyPagingItems(),
        {}
    )
}