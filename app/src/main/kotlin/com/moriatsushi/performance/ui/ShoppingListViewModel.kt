package com.moriatsushi.performance.ui

import androidx.lifecycle.ViewModel
import com.moriatsushi.performance.model.ShoppingItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

private val initialList = List(100) {
    ShoppingItem.create(
        name = "Item:$it",
        count = (1..10).random()
    )
}

class ShoppingListViewModel : ViewModel() {
    private val _list = MutableStateFlow(initialList)
    val list: StateFlow<List<ShoppingItem>> = _list

    fun increase(item: ShoppingItem) {
        _list.update { list ->
            list.map {
                if (it.id == item.id) {
                    it.increase()
                } else {
                    it
                }
            }
        }
    }

    fun decrease(item: ShoppingItem) {
        _list.update { list ->
            list.map {
                if (it.id == item.id) {
                    it.decrease()
                } else {
                    it
                }
            }
        }
    }
}
