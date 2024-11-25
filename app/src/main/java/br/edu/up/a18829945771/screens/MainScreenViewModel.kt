package br.edu.up.a18829945771.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.edu.up.a18829945771.data.FirebaseItemsDataSourceImpl
import br.edu.up.a18829945771.data.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(app: Application) : AndroidViewModel(app) {
    private val dataSource = FirebaseItemsDataSourceImpl()
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items

    init {
        fetchAvailable()
    }

    fun fetchAvailable() {
        viewModelScope.launch {
            dataSource.list().collect { _items.value = it }
        }
    }

    fun create(item: Item) {
        viewModelScope.launch {
            dataSource.create(item)
        }
    }

    fun update(item: Item) {
        viewModelScope.launch {
            dataSource.update(item)
        }
    }

    fun delete(item: Item) {
        viewModelScope.launch {
            dataSource.delete(item)
        }
    }
}