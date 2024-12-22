package com.sreeram.fetchrewards.domain.usecase

import com.sreeram.fetchrewards.data.model.Item
import com.sreeram.fetchrewards.data.repository.FetchRepositoryImpl
import javax.inject.Inject

class SortTheResultsUseCase @Inject constructor(
    private val repository: FetchRepositoryImpl
) {
    suspend operator fun invoke(): Map<Int?, List<Item>> {
        return repository.fetchItems()
            .sortedWith(
                compareBy(
                    { it.listId },
                    { it.name ?: "" },
                    { it.id }
                )
            )
            .groupBy { it.listId }
    }

}