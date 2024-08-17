package com.example.bookworm.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun LoginScreen(navController: NavController){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current as Activity
    val db = FirebaseFirestore.getInstance()

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
       Column(modifier = Modifier
           .fillMaxSize()
           .padding(25.dp)
           .background(Color.LightGray)
       ) {
           OutlinedTextField(
               value = username,
               onValueChange = { username = it},
               label = { Text("Enter Username") },
               colors = OutlinedTextFieldDefaults.colors(
                   focusedTextColor = Color.Black,
               ),
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(16.dp)
           )

           Spacer(modifier = Modifier.height(20.dp))

           OutlinedTextField(
               value = password,
               onValueChange = { password = it},
               label = {Text("Enter Password")},
               colors = OutlinedTextFieldDefaults.colors(
                   focusedTextColor = Color.Black,
               ),
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(16.dp),
               visualTransformation = PasswordVisualTransformation()
           )

           Button(
               onClick = {
                   checkLoginCredentials(db, username, password){ success, message ->
                       if(success){
                           navController.navigate("search")
                       } else {
                           Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                       }
                   }
               },
               colors = ButtonDefaults.buttonColors(
                   containerColor = Color.Black
               ),
               modifier = Modifier
                   .align(Alignment.CenterHorizontally)
                ) { Text(
                    text = "Login",
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

           Spacer(modifier = Modifier.height(20.dp))

           Button(
               onClick = {
                   navController.navigate("register")
               },
               modifier = Modifier
                   .align(Alignment.CenterHorizontally),
               colors = ButtonDefaults.buttonColors(
                   containerColor = Color.Black
               )
           ) {
               Text(
                   text = "New here? Register first!",
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
       }
    }
}

fun checkLoginCredentials(
    db: FirebaseFirestore,
    username: String,
    password: String,
    callback: (success: Boolean, message: String) -> Unit
) {
    db.collection("users")
        .whereEqualTo("username", username)
        .whereEqualTo("password", password)
        .get()
        .addOnSuccessListener { documents ->
            if (!documents.isEmpty) {
                // Username and password are correct
                callback(true, "Login successful")
            } else {
                // Incorrect username or password
                callback(false, "Please check your username or password")
            }
        }
        .addOnFailureListener { e ->
            println("Error fetching document: $e")
            callback(false, "Error checking credentials")
        }
}