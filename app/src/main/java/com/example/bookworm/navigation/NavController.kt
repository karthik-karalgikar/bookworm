package com.example.bookworm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.screens.Inventory
import com.example.bookworm.screens.LoginScreen
import com.example.bookworm.screens.SearchBooks
import com.example.bookworm.screens.RegisterScreen
import com.example.bookworm.viewModels.InventoryViewModel

@Composable
fun AppNavHost(navController: NavHostController, inventoryViewModel: InventoryViewModel) {
    NavHost(navController, startDestination = "login") {
        composable("register") { RegisterScreen(navController) }
        composable("login") { LoginScreen(navController)}
        composable("search") { SearchBooks(navController, inventoryViewModel) }
        composable("inventory") {
            val inventory = inventoryViewModel.inventory.value ?: emptyList()
            Inventory(inventory)
        }
    }
}
