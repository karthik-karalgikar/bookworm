package com.example.bookworm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.screens.SearchBooks
import com.example.bookworm.screens.SignInScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "search") {
        composable("sign_in") { SignInScreen(navController) }
        composable("search") { SearchBooks() }
    }
}
