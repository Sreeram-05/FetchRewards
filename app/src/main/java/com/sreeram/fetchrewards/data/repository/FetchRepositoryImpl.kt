package com.sreeram.fetchrewards.data.repository

import com.sreeram.fetchrewards.data.api.FetchService
import com.sreeram.fetchrewards.data.model.Item
import com.sreeram.fetchrewards.domain.repository.FetchRepository
import javax.inject.Inject


class FetchRepositoryImpl @Inject constructor(
    private val fetchService: FetchService
) : FetchRepository {
    override suspend fun fetchItems(): List<Item> {
        return fetchService.fetchItems()
    }
}