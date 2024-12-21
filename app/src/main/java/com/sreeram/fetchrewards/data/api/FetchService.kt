package com.sreeram.fetchrewards.data.api

import com.sreeram.fetchrewards.data.model.Item
import retrofit2.http.GET

interface FetchService {
    @GET("hiring.json")
    suspend fun fetchItems(): List<Item>
}