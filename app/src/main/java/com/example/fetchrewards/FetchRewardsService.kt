package com.example.fetchrewards

import com.example.fetchrewards.network.model.ItemDto
import retrofit2.http.GET

/**
 * Communicates with the backend to obtain data using the coroutines.
 */
interface FetchRewardsService {

    @GET("hiring.json")
    suspend fun getItemList(): List<ItemDto>
}