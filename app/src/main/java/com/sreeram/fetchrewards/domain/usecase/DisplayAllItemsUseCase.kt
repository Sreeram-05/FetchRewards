package com.sreeram.fetchrewards.domain.usecase

import com.sreeram.fetchrewards.data.model.Item
import com.sreeram.fetchrewards.data.repository.FetchRepositoryImpl
import javax.inject.Inject

class DisplayAllItemsUseCase @Inject constructor(
    private val repository: FetchRepositoryImpl
) {
    suspend fun get(): List<Item> {
        return try {
            repository.fetchItems()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList() // Return empty list on error
        }
    }
}