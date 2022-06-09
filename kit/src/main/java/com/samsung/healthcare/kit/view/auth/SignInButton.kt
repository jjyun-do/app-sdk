package com.samsung.healthcare.kit.view.auth

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser
import com.samsung.healthcare.kit.R
import com.samsung.healthcare.kit.auth.AuthContractFactory
import com.samsung.healthcare.kit.auth.SignInProvider
import com.samsung.healthcare.kit.common.CallbackCollection
import com.samsung.healthcare.kit.theme.AppTheme

@Composable
fun GoogleSignInButton(
    callbackCollection: CallbackCollection,
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(vertical = 20.dp)
    ) {
        var user: FirebaseUser? by rememberSaveable { mutableStateOf(null) }
        val context = LocalContext.current

        val authResultLauncher =
            rememberLauncherForActivityResult(
                contract = AuthContractFactory.createAuthContract(
                    SignInProvider.Google
                )
            ) { task ->
                val account = task.getResult(ApiException::class.java)
                if (null == account) {
                    showMessage(context, context.getString(R.string.failed_to_signin))
                    return@rememberLauncherForActivityResult
                }

                user = account
                showMessage(context, "hello ${user?.displayName}")
            }

        user?.let {
            callbackCollection.next()
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 20.dp),
            border = BorderStroke(width = 1.dp, AppTheme.colors.border),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.background),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            onClick = {
                authResultLauncher.launch(1)
            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp),
                    painter = painterResource(R.drawable.ic_google__g__logo),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Continue with Google",
                    color = AppTheme.colors.textPrimary,
                )
            }
        }
    }
}

private fun showMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG)
        .show()
}

@Preview(showBackground = true)
@Composable
fun GoogleSignInButtonPreview() {
    GoogleSignInButton(
        CallbackCollection()
    )
}
