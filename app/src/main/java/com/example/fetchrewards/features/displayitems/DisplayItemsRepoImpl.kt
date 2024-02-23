package com.example.fetchrewards.features.displayitems

import com.example.fetchrewards.FetchRewardsService
import com.example.fetchrewards.domain.model.Item
import com.example.fetchrewards.domain.util.Response
import com.example.fetchrewards.network.model.ItemMapper
import javax.inject.Inject

class DisplayItemsRepoImpl @Inject constructor(
    private val fetchRewardsService: FetchRewardsService,
    private val mapper: ItemMapper
) : DisplayItemsRepo {

    override suspend fun getItemList(): Response<List<Item>> {
        val result = kotlin.runCatching { fetchRewardsService.getItemList() }
        result.onSuccess {
            return Response.Success(
                mapper.toDomainList(
                    it.filter { itemDto ->
                        !itemDto.name.isNullOrEmpty()
                    }.sortedBy { item ->
                        item.name
                    }.sortedBy { item ->
                        item.listId
                    })
            )
        }
        result.onFailure {
            return Response.Error(it.message.toString())
        }
        return Response.Error("Not responding")
    }
}