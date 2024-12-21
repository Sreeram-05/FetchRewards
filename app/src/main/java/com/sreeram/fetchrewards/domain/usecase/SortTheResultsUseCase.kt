package com.sreeram.fetchrewards.domain.usecase

import com.sreeram.fetchrewards.data.model.Item
import com.sreeram.fetchrewards.data.repository.FetchRepositoryImpl
import javax.inject.Inject

class SortTheResultsUseCase @Inject constructor(
    private val repository: FetchRepositoryImpl
) {
    suspend operator fun invoke(): Map<Int?, List<Item>> {
        // Fetch, group by listId, and sort items by listId, then by name, then by id
        return repository.fetchItems()
            .sortedWith(
                compareBy(
                    { it.listId },    // Sort by listId first
                    { it.name ?: "" }, // Sort by name, treat null as empty string
                    { it.id }          // Sort by id
                )
            )
            .groupBy { it.listId } // Group by listId after sorting
    }

}