package com.example.bookworm.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bookworm.ItemsItem

@Composable
fun BookItem(item: ItemsItem, onItemSelected: (ItemsItem) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Title: ${item.volumeInfo.title}", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Authors: ${item.volumeInfo.authors?.joinToString(", ") ?: "Unknown"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Categories: ${item.volumeInfo.categories?.joinToString(", ") ?: "Unknown"}")
        }
        Column {
            Checkbox(
                checked = item.isSelected,
                onCheckedChange = { isChecked ->
                    // Update selection status and invoke callback
                    item.isSelected = isChecked
                    onItemSelected(item)
                                  },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Blue,
                    uncheckedColor = Color.White
                )
            )
        }
    }
}

@Composable
fun BookList(items: List<ItemsItem>, onItemSelected: (ItemsItem) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items) { item ->
            BookItem(item = item, onItemSelected = onItemSelected)
        }
    }
}

