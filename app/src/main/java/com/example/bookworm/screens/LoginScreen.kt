package com.example.bookworm.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
               label = {Text("Set Password")},
               colors = OutlinedTextFieldDefaults.colors(
                   focusedTextColor = Color.Black,
               ),
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(16.dp),
               visualTransformation = PasswordVisualTransformation()
           )

           Button(onClick = { /*TODO*/ }) {


           }
       }
    }
}