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
                // Filter out items where the name is null or blank, then group by listId
                repository.fetchItems()
                    .filter { !it.name.isNullOrBlank() } // Filter out null or blank names
                    .groupBy { it.listId }[listId]
                    ?.sortedBy { it.name } // Sort by name within each listId
                    ?.let { listId to it }
            }
            .toMap()
    }
}