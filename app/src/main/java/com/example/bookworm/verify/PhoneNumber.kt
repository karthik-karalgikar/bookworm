package com.example.bookworm.verify

import android.app.Activity
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class PhoneNumber(private val activity: Activity) {

    val auth = FirebaseAuth.getInstance()
    private var storedVerificationId: String? = null
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null
    private var onSuccessCallback: (() -> Unit)? = null

    fun sendOtpToPhoneNumber(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // Set the activity for Recaptcha flow
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d("Phone", "Verification Completed: $credential")
            onSuccessCallback?.let { signInWithPhoneAuthCredential(credential, it) }
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Log.w("Phone", "Verification Failed", e)
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            Log.d("Phone", "Code has been sent to phone: $verificationId")
            storedVerificationId = verificationId
            resendToken = token
        }

        override fun onCodeAutoRetrievalTimeOut(verificationId: String) {
            Log.d("Phone", "Code auto-retrieval timed out: $verificationId")
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential, onSuccess: (() -> Unit)?) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    Log.d("Phone", "signInWithCredential: success")
                    onSuccess?.invoke()
                    val user = task.result?.user
                } else {
                    Log.w("Phone", "signInWithCredential: failure", task.exception)
                }
            }
    }

    fun verifyCode(code: String, onSuccess: () -> Unit) {
        onSuccessCallback = onSuccess
        val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, code)
        signInWithPhoneAuthCredential(credential, onSuccessCallback!!)
    }
}
