package com.square.repository.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.square.repository.domain.model.RepositoryItem
import com.square.repository.domain.repository.SquareRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SquareRepositoryListViewModel @Inject constructor(
    private val repository: SquareRepository
) : ViewModel() {

    private val _repositoryState: MutableStateFlow<PagingData<RepositoryItem>> = MutableStateFlow(value = PagingData.empty())
    val repositoryState: MutableStateFlow<PagingData<RepositoryItem>> get() = _repositoryState

    init {
        viewModelScope.launch {
            getRepositories()
        }
    }

    private suspend fun getRepositories() {
        repository.getRepositoryListPagination(PAGE_SIZE, PREFETCH_DISTANCE)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _repositoryState.value = it
            }
    }

    companion object {
        private const val PAGE_SIZE = 30
        private const val PREFETCH_DISTANCE = 5
    }
}