package com.sreeram.fetchrewards.domain.usecase

import com.sreeram.fetchrewards.data.model.Item
import com.sreeram.fetchrewards.data.repository.FetchRepositoryImpl
import javax.inject.Inject

class DisplayFilteredListUseCase @Inject constructor(
    private val repository: FetchRepositoryImpl
) {
    suspend operator fun invoke(): Map<Int?, List<Item>> {
        // Fetch the items from the repository
        return listOf(1, 2, 3, 4)
            .mapNotNull { listId ->
                repository.fetchItems()
                    .filter { !it.name.isNullOrBlank() }
                    .groupBy { it.listId }[listId]
                    ?.sortedBy { it.name }
                    ?.let { listId to it }
            }
            .toMap()
    }
}