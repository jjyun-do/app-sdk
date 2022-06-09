package com.samsung.healthcare.kit.auth

import androidx.activity.result.contract.ActivityResultContract
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser

object AuthContractFactory {
    fun createAuthContract(provider: SignInProvider): ActivityResultContract<Int, Task<FirebaseUser>> =
        when (provider) {
            SignInProvider.Google -> FirebaseGoogleAuthContract()
        }
}
