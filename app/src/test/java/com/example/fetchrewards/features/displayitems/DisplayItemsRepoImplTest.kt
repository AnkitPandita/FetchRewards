package com.example.fetchrewards.features.displayitems

import com.example.fetchrewards.FetchRewardsService
import com.example.fetchrewards.domain.model.Item
import com.example.fetchrewards.network.model.ItemDto
import com.example.fetchrewards.network.model.ItemMapper
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DisplayItemsRepoImplTest {

    @InjectMocks
    lateinit var sut: DisplayItemsRepoImpl

    @Mock
    lateinit var fetchRewardsService: FetchRewardsService

    @Mock
    lateinit var mapper: ItemMapper

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetItemList() = runTest {
        val itemDtoList = listOf(ItemDto(1, 1, "name"))
        val itemList = listOf(Item(1, 1, "name"))
        Mockito.`when`(fetchRewardsService.getItemList()).thenReturn(itemDtoList)
        Mockito.`when`(mapper.toDomainList(itemDtoList)).thenReturn(itemList)
        val response = sut.getItemList()
        assertEquals(true, response.data!!.size == 1)
        assertEquals("name", response.data!![0].name)
    }
}