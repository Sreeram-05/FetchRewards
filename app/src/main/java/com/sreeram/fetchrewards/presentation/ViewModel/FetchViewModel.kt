package com.sreeram.fetchrewards.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sreeram.fetchrewards.data.model.Item
import com.sreeram.fetchrewards.domain.usecase.DisplayAllItemsUseCase
import com.sreeram.fetchrewards.domain.usecase.DisplayFilteredListUseCase
import com.sreeram.fetchrewards.domain.usecase.SortTheResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FetchViewModel @Inject constructor(
    private val displayAllItemsUseCase: DisplayAllItemsUseCase,
    private val sortTheResultsUseCase: SortTheResultsUseCase,
    private val displayFilteredListUseCase: DisplayFilteredListUseCase
) : ViewModel() {

    private val _displayItemsByListID = MutableStateFlow<Map<Int?, List<Item>>>(emptyMap())
    val displayItemsByListID: StateFlow<Map<Int?, List<Item>>> = _displayItemsByListID

    private val _sortTheResults = MutableStateFlow<Map<Int?, List<Item>>>(emptyMap())
    val sortTheResults: StateFlow<Map<Int?, List<Item>>> = _sortTheResults

    private val _filterTheResults = MutableStateFlow<Map<Int?, List<Item>>>(emptyMap())
    val filterTheResults: StateFlow<Map<Int?, List<Item>>> = _filterTheResults

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            _loading.value = true
            try {
                _displayItemsByListID.value = displayAllItemsUseCase.invoke()
                _sortTheResults.value = sortTheResultsUseCase.invoke()
                _filterTheResults.value = displayFilteredListUseCase.invoke()
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _loading.value = false
            }
        }
    }
}
