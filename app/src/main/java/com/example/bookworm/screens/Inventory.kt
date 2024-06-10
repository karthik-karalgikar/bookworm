package com.example.bookworm.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookworm.ItemsItem
import com.example.bookworm.model.BookItem

@Composable
fun Inventory(inventory: List<ItemsItem>) {
    Log.d("Inventory class", "Inventory: inside function")
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        Log.d("Inventory class", "Inventory: lazy column")
        itemsIndexed(items = inventory) { index, item ->
            Log.d("Inventory class", "Inventory: index")
            BookItem(
                item = item,
                onItemSelected = {}
            )
            Log.d("Inventory class", "Inventory: bye bye")
        }
    }
}
