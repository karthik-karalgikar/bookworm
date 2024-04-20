package com.example.bookworm.verify

import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class PhoneNumber {

    val auth = FirebaseAuth.getInstance()


    fun sendOtpToPhoneNumber(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()

        val randomOtp = generateRandomOTP()
        // Here you can use the generated randomOtp for further processing or verification
        // For now, let's just print it to the log
        Log.d("Generated OTP", randomOtp)

        PhoneAuthProvider.verifyPhoneNumber(options)

    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d("Phone", "Verification Completed")
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Log.d("Phone", "Verification Failed")
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            Log.d("Phone", "Code has been sent to phone")
        }
    }

}