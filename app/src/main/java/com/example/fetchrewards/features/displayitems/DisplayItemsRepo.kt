package com.example.fetchrewards.features.displayitems

import com.example.fetchrewards.domain.model.Item
import com.example.fetchrewards.domain.util.Response

interface DisplayItemsRepo {

    suspend fun getItemList(): Response<List<Item>>
}