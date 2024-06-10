package com.example.bookworm.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookworm.ItemsItem
import com.example.bookworm.model.BookList
import com.example.bookworm.viewModels.BookContent
import com.example.bookworm.viewModels.InventoryViewModel

@Composable
fun SearchBooks(navController: NavController, inventoryViewModel: InventoryViewModel) {

    var books by remember { mutableStateOf(listOf<ItemsItem>()) }
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var inventory by remember { mutableStateOf<List<ItemsItem>>(emptyList()) }
    var selectedBooks by remember { mutableStateOf<List<ItemsItem>>(emptyList()) }

    LaunchedEffect(Unit) {
        BookContent().getBookContent(title, author) { fetchedBooks ->
            books = fetchedBooks
        }
    }

    fun addToInventory(selectedBooks: List<ItemsItem>) {
        val currentInventory = inventoryViewModel.inventory.value ?: emptyList()
        Log.d("Inventory add", "Inventory: $currentInventory")
        val updatedInventory = currentInventory + selectedBooks
        Log.d("Inventory add", "Inventory: $updatedInventory")
        inventoryViewModel.updateInventory(updatedInventory)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )
        TextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Author") }
        )
        Button(
            onClick = {
//                bookcontent.getBookContent()
                BookContent().getBookContent(title, author) { fetchedBooks ->
                    books = fetchedBooks
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
//                enabled = phoneNo.length == 10
        ) {
            Text(
                text = "Search books",
                style = TextStyle(
                    fontSize = 34.sp,
                    lineHeight = 38.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight(400),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            )
        }
        BookList(
            items = books,
            onItemSelected = { selectedItem ->
                val updatedBooks = books.map {
                    if (it.id == selectedItem.id) selectedItem else it
                }
                books = updatedBooks
                val selectedItems = updatedBooks.filter { it.isSelected }
                selectedBooks = selectedItems
                Log.d("Selected books", "Selected: $selectedItems")
            }
        )
    }
    Button(
        onClick = {
            addToInventory(selectedBooks)
            navController.navigate("inventory")
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black
        )
    ) {
        Text(
            text = "Go to Inventory",
            style = TextStyle(
                fontSize = 34.sp,
                lineHeight = 38.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight(400),
                color = Color.White,
                textAlign = TextAlign.End
            )
        )
    }

}



