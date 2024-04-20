package com.example.bookworm.verify

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OtpTextFieldScreen(onVerifyClick:(String)->Unit){

    val codeTxtFieldTxt = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Box (contentAlignment = Alignment.Center){
        val modifier = Modifier
            .size(0.dp)
            .alpha(0f)

        val focusRequester = remember {
            FocusRequester()
        }


        TextField(
            value = codeTxtFieldTxt.value,
            onValueChange = {
                if (it.length <= 6) {
                    codeTxtFieldTxt.value = it
                    if(it.length == 6){
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        onVerifyClick(it)
                    }
                } else {
                    keyboardController?.hide()
                }
            },
            maxLines = 1,
            modifier = Modifier
                .alpha(0f)
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            )
        )

        OtpTextField(
            codeText = codeTxtFieldTxt,
            onRequestFocus = {focusRequester.requestFocus()}
        )

        Spacer(Modifier.height(20.dp))


    }

}

@Composable
fun OtpTextField(
    codeText : MutableState<String>,
    onRequestFocus:() -> Unit
){
    Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier
        .fillMaxSize()
        .clickable() {
            onRequestFocus()
        }){
        OtpTextFieldBox(
            text = if (codeText.value.isNotEmpty()) codeText.value[0].toString() else "",
            onRequestFocus = onRequestFocus
        )
        OtpTextFieldBox(
            text = if (codeText.value.isNotEmpty() && codeText.value.length >= 2) codeText.value[1].toString() else "",
            onRequestFocus = onRequestFocus
        )
        OtpTextFieldBox(
            text = if (codeText.value.isNotEmpty() && codeText.value.length >= 3) codeText.value[2].toString() else "",
            onRequestFocus = onRequestFocus
        )
        OtpTextFieldBox(
            text = if (codeText.value.isNotEmpty() && codeText.value.length >= 4) codeText.value[3].toString() else "",
            onRequestFocus = onRequestFocus
        )
        OtpTextFieldBox(
            text = if (codeText.value.isNotEmpty() && codeText.value.length >= 5) codeText.value[4].toString() else "",
            onRequestFocus = onRequestFocus
        )
        OtpTextFieldBox(
            text = if (codeText.value.isNotEmpty() && codeText.value.length >= 5) codeText.value[4].toString() else "",
            onRequestFocus = onRequestFocus
        )
        OtpTextFieldBox(
            text = if (codeText.value.isNotEmpty() && codeText.value.length >= 6) codeText.value[5].toString() else "",
            onRequestFocus = onRequestFocus
        )
    }
}

@Composable
private fun OtpTextFieldBox(text: String, onRequestFocus: () -> Unit){

    Box(
        modifier = Modifier
            .width(50.dp)
            .height(TextFieldDefaults.MinHeight)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color.Black, shape = RoundedCornerShape(12.dp))
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text (
            text = text,
            fontSize = 19.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}

fun generateRandomOTP(): String {
    val otp = StringBuilder()
    repeat(6) {
        otp.append(Random.nextInt(0, 10)) // Random digit between 0 and 9
    }
    return otp.toString()
}

fun verifyOtp(otp: String): Boolean {
    // Generate the expected OTP
    val expectedOtp = generateRandomOTP()
    // Compare the entered OTP with the expected OTP
    return otp == expectedOtp
}