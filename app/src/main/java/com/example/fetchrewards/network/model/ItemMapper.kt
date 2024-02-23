package com.example.fetchrewards.network.model

import com.example.fetchrewards.domain.model.Item
import com.example.fetchrewards.domain.util.DomainMapper
import javax.inject.Inject

class ItemMapper @Inject constructor(): DomainMapper<ItemDto, Item> {

    override fun mapToDomainModel(dto: ItemDto): Item {
        return Item(
            id = dto.id,
            listId = dto.listId,
            name = dto.name
        )
    }

    fun toDomainList(list: List<ItemDto>): List<Item> {
        return list.map { mapToDomainModel(it) }
    }
}