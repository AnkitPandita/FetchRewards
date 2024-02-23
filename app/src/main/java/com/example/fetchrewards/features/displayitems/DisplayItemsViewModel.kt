package com.example.fetchrewards.features.displayitems

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewards.domain.model.Item
import com.example.fetchrewards.domain.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisplayItemsViewModel @Inject constructor(private val displayItemsRepoImpl: DisplayItemsRepoImpl) :
    ViewModel() {

    private val _itemList = MutableStateFlow<Response<List<Item>>>(Response.Loading())
    val itemList = _itemList.asStateFlow()

    init {
        getList()
    }

    private fun getList() {
        viewModelScope.launch(Dispatchers.IO) {
            _itemList.emit(displayItemsRepoImpl.getItemList())
        }
    }
}