package com.sreeram.fetchrewards.domain.repository

import com.sreeram.fetchrewards.data.model.Item

interface FetchRepository {
    suspend fun fetchItems(): List<Item>
}