package com.example.bookworm.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookworm.ItemsItem

class InventoryViewModel : ViewModel() {
    // Inventory LiveData
    private val _inventory = MutableLiveData<List<ItemsItem>>()
    val inventory: LiveData<List<ItemsItem>> = _inventory

    // Function to update inventory
    fun updateInventory(newInventory: List<ItemsItem>) {
        _inventory.value = newInventory
        Log.d("Inventory", "updateInventory: $newInventory")
    }
}
