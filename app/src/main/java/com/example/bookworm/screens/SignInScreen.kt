package com.example.bookworm.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookworm.verify.OtpTextFieldScreen
import com.example.bookworm.verify.PhoneNumber
import com.example.bookworm.verify.verifyOtp
import com.example.bookworm.viewModels.BookContent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(){

    val context = LocalContext.current as Activity
    val phoneNumber = PhoneNumber(context)
    var phoneNo by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var bookcontent = BookContent()

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        Column(modifier = Modifier
            .padding(25.dp)
            .fillMaxSize()
            .background(Color.LightGray)
        ){

            OutlinedTextField(value = username,
                onValueChange = { username = it},
                label = {Text("Enter Username")},
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
//            Text(
//                text = "Enter Username",
//                style = TextStyle(
//                    fontSize = 34.sp,
//                    lineHeight = 38.sp,
//                    fontFamily = FontFamily.SansSerif,
//                    fontWeight = FontWeight(400),
//                    color = Color.Black,
//                    textAlign = TextAlign.Center
//                )
//            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = phoneNo,
                onValueChange = { phoneNo = it},
                label = {Text("Enter Mobile Number")},
                colors = TextFieldDefaults.outlinedTextFieldColors(Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Button(
                onClick = {
                    phoneNumber.sendOtpToPhoneNumber(phoneNo)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
//                enabled = phoneNo.length == 10
            ) {
                Text(
                    text = "Receive OTP",
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
            Button(
                onClick = {
                    bookcontent.getBookContent()
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

            Spacer(modifier = Modifier.height(20.dp))

//            Text(
//                text = "Enter OTP",
//                style = TextStyle(
//                    fontSize = 34.sp,
//                    lineHeight = 38.sp,
//                    fontFamily = FontFamily.SansSerif,
//                    fontWeight = FontWeight(400),
//                    color = Color.Black,
//                    textAlign = TextAlign.Center
//                )
//            )

            Spacer(modifier = Modifier.height(2.dp))

            OutlinedTextField(
                value = otp,
                onValueChange = { otp = it },
                label = { Text("Enter OTP") },
                colors = TextFieldDefaults.outlinedTextFieldColors(Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Button(
                onClick = {
                    phoneNumber.verifyCode(otp)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Text(
                    text = "Verify OTP",
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

//            OtpTextFieldScreen(onVerifyClick = { otp ->
//                    verifyOtp(otp = otp)
//            })


        }

    }
}