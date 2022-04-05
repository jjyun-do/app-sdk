package com.samsung.healthcare.research.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.R
import com.samsung.healthcare.research.theme.AppTheme
import com.samsung.healthcare.research.view.common.RoundButton
import com.samsung.healthcare.research.view.common.RoundTextField
import com.samsung.healthcare.research.view.common.TopBar

@Composable
fun SignupView(
    // TODO Get App information from context
    title: String = "SleepCare",
    logoDrawableId: Int? = null,
    onComplete: () -> Unit = {}
) {

    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val passwordConfirmState = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBar(
                title = "Register"
            ) {}
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(vertical = 20.dp)
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 20.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        AppTheme.colors.border,
                    ),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.background),
                    elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
                    onClick = { /* TODO */ onComplete() }
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
                            contentDescription = "",
                            contentScale = ContentScale.Fit
                        )
                        Spacer(
                            modifier = Modifier.width(10.dp)
                        )
                        Text(
                            text = "Continue with Google",
                            color = AppTheme.colors.textPrimary,
                        )
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (logoDrawableId != null) {
                    Image(
                        painter = painterResource(id = logoDrawableId),
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
                Text(
                    text = title,
                    style = AppTheme.typography.appTitle,
                    color = AppTheme.colors.textPrimary
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Thanks for joining the study! Now please create an account to keep track of your data and keep it safe.",
                modifier = Modifier.fillMaxWidth(),
                style = AppTheme.typography.body1,
                color = AppTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.height(40.dp))

            RoundTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = emailState.value,
                onValueChange = { it -> emailState.value = it },
                hint = "Email Address",
            )

            Spacer(modifier = Modifier.height(10.dp))

            RoundTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = passwordState.value,
                onValueChange = { it -> passwordState.value = it },
                hint = "Password",
                isPassword = true,
            )

            Spacer(modifier = Modifier.height(10.dp))

            RoundTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = passwordConfirmState.value,
                onValueChange = { it -> passwordConfirmState.value = it },
                hint = "Confirm Password",
                isPassword = true,
            )

            Spacer(modifier = Modifier.height(20.dp))

            RoundButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                text = "Sign Up",
                textColor = AppTheme.colors.textPrimary,
                onClick = { /* TODO */onComplete() }
            )
        }
    }
}
