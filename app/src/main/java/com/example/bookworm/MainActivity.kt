package com.example.bookworm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.bookworm.navigation.AppNavHost
import com.example.bookworm.screens.RegisterScreen
import com.example.bookworm.ui.theme.BookwormTheme
import com.example.bookworm.viewModels.InventoryViewModel
import com.google.firebase.Firebase
import com.google.firebase.appcheck.appCheck
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.initialize

class MainActivity : ComponentActivity() {
    private val inventoryViewModel: InventoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        Firebase.initialize(context = this)
        val db = FirebaseFirestore.getInstance()
        Firebase.appCheck.installAppCheckProviderFactory(
            SafetyNetAppCheckProviderFactory.getInstance()
        )
        super.onCreate(savedInstanceState)
        setContent {
            BookwormTheme {
                val navController = rememberNavController()
                AppNavHost(navController, inventoryViewModel)
            }
        }
    }
}
