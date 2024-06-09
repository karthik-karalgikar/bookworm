package com.example.bookworm.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bookworm.ItemsItem

@Composable
fun BookItem(volumeInfo: VolumeInfo) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Title: ${volumeInfo.title}", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Authors: ${volumeInfo.authors?.joinToString(", ") ?: "Unknown"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Categories: ${volumeInfo.categories?.joinToString(", ") ?: "Unknown"}")
        }
    }
}

@Composable
fun BookList(items: List<ItemsItem>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items) { item ->
            BookItem(volumeInfo = item.volumeInfo)
        }
    }
}

