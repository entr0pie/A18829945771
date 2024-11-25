package br.edu.up.a18829945771.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.up.a18829945771.data.Item

class SelectedItemViewModel : ViewModel() {
    private val _selected = MutableLiveData<Item?>()
    val selected: LiveData<Item?> = _selected

    fun select(item: Item) {
        _selected.value = item
    }
}