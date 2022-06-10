package com.samsung.healthcare.kit.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.samsung.healthcare.kit.R

class FirebaseGoogleAuthContract : ActivityResultContract<Int, Task<FirebaseUser>>() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private fun getClient(context: Context): GoogleSignInClient =
        GoogleSignIn.getClient(
            context,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.google_client_id))
                .requestEmail()
                .build()
        )

    private fun firebaseAuthWithGoogle(idToken: String): FirebaseUser? {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        auth.signInWithCredential(credential)
            .addOnCompleteListener { authResult ->
                if (authResult.isSuccessful) {
                    Log.i("Auth", "Authentication success. ${auth.currentUser?.email}")
                    // TODO: Update User Data (calls platform API - POST /users/{uId})
                }
            }

        return auth.currentUser
    }

    override fun createIntent(context: Context, input: Int): Intent =
        getClient(context).signInIntent.putExtra("input", input)

    override fun parseResult(resultCode: Int, intent: Intent?): Task<FirebaseUser> =
        when (resultCode) {
            Activity.RESULT_OK -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
                try {
                    val account = task.getResult(ApiException::class.java)
                    Tasks.forResult(firebaseAuthWithGoogle(account.idToken!!))
                } catch (e: ApiException) {
                    Log.e("Auth", "Authentication failed (${e.message})")
                    Tasks.forResult(null)
                }
            }
            else -> {
                Log.e("Auth", "Request respond with invalid result code $resultCode")
                Tasks.forResult(null)
            }
        }
}
