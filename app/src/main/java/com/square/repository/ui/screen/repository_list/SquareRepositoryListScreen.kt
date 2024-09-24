package com.square.repository.ui.screen.repository_list

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.square.repository.R
import com.square.repository.domain.model.RepositoryItem
import com.square.repository.ui.screen.SquareRepositoryListViewModel
import com.square.repository.ui.screen.repository_list.composable.SquareRepositoryCard
import com.square.repository.utils.ErrorMessage
import com.square.repository.utils.LoadingNextPageItem
import com.square.repository.utils.ObserveAsEvents
import com.square.repository.utils.PageLoader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun SquareRepositoryListScreen(
    viewModel: SquareRepositoryListViewModel = hiltViewModel()
) {
    val repositoryPagingItems: LazyPagingItems<RepositoryItem> = viewModel.repositoryState.collectAsLazyPagingItems()
    val uriHandler = LocalUriHandler.current

    ObserveAsEvents(viewModel.openUrlRequest) {
        uriHandler.openUri(it)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.repositories)) })
        },
        content = { innerPadding ->
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    end = 20.dp,
                    top = 15.dp + innerPadding.calculateTopPadding(),
                    bottom = 15.dp + innerPadding.calculateBottomPadding(),
                )
            ) {
                items(repositoryPagingItems.itemCount) { index ->
                    repositoryPagingItems[index]?.let { SquareRepositoryCard(squareRepositoryItem = it, { viewModel.openUrl(it) }) }
                }

                repositoryPagingItems.apply {

                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item {
                                PageLoader(modifier = Modifier.fillParentMaxSize())
                            }
                        }

                        loadState.refresh is LoadState.Error -> {
                            val error = repositoryPagingItems.loadState.refresh as LoadState.Error
                            item {
                                ErrorMessage(
                                    modifier = Modifier.fillParentMaxSize(),
                                    message = error.error.localizedMessage ?: stringResource(R.string.something_went_wrong),
                                    onRetryClick = { retry() }
                                )
                            }
                        }

                        loadState.append is LoadState.Loading -> {
                            item { LoadingNextPageItem(modifier = Modifier) }
                        }

                        loadState.append is LoadState.Error -> {
                            val error = repositoryPagingItems.loadState.append as LoadState.Error
                            item {
                                ErrorMessage(
                                    modifier = Modifier,
                                    message = error.error.localizedMessage ?: stringResource(R.string.something_went_wrong),
                                    onRetryClick = { retry() }
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
