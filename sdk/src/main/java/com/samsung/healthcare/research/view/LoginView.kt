package com.samsung.healthcare.research.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.view.common.RoundButton
import com.samsung.healthcare.research.view.common.RoundTextField
import com.samsung.healthcare.research.view.common.TopBar
import com.samsung.healthcare.research.view.common.UnderlinedTab

enum class LoginScreen {
    SignUp, SignIn
}

@Composable
fun LoginView(
    topBarTitle: String = "",
    topBarColor: Color = Color(0xB7000000),
    onClickBack: () -> Unit,
    onClickMoreVert: () -> Unit,
    tabSelectedTextColor: Color = Color.Black,
    tabUnselectedTextColor: Color = Color(0x970347F4),
) {
    Scaffold(
        topBar = {
            TopBar(
                title = topBarTitle,
                color = topBarColor,
                onClickBack = onClickBack,
                onClickAction = onClickMoreVert,
            )
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.wrapContentWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                var tabIndex by remember { mutableStateOf(LoginScreen.SignUp) }
                val titles = listOf(
                    "Sign Up",
                    "Sign In"
                )
                UnderlinedTab(
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 35.dp),
                    titles = titles,
                    tabSelected = tabIndex,
                    onTabSelected = { index -> tabIndex = index },
                    selectedTextColor = tabSelectedTextColor,
                    unselectedTextColor = tabUnselectedTextColor,
                )
                when (tabIndex) {
                    LoginScreen.SignUp -> {
                        SignUpScreen()
                    }
                    LoginScreen.SignIn -> {
                        SignInScreen()
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Divider(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    color = Color(0xFF76787A),
                    thickness = 1.dp
                )
                RoundButton(
                    modifier = Modifier
                        .size(
                            width = 200.dp,
                            height = 105.dp
                        )
                        .padding(vertical = 30.dp),
                    text = "Continue with Google",
                ) { }
            }
        }
    }
}

@Composable
fun SignUpScreen() {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val confirmPasswordState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        RoundTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = emailState.value,
            onValueChange = { newText -> emailState.value = newText },
            hint = "Email",
        )
        RoundTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = passwordState.value,
            onValueChange = { newText -> passwordState.value = newText },
            hint = "Password",
        )
        RoundTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = confirmPasswordState.value,
            onValueChange = { newText -> confirmPasswordState.value = newText },
            hint = "Confirm Password",
        )
    }
    RoundButton(
        modifier = Modifier
            .size(
                width = 200.dp,
                height = 65.dp
            )
            .padding(top = 20.dp),
        text = "Finish Enrollment",
        buttonColor = Color(0x975F8BFD),
        textColor = Color.White,
    ) { /* TODO */ }
}

@Composable
fun SignInScreen() {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        RoundTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = emailState.value,
            onValueChange = { newText -> emailState.value = newText },
            hint = "Email",
        )
        RoundTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = passwordState.value,
            onValueChange = { newText -> passwordState.value = newText },
            hint = "Password",
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 15.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            TextButton(
                modifier = Modifier.wrapContentSize(),
                onClick = {},
            ) {
                Text(
                    text = "Forgot password",
                    color = Color(0x970347F4),
                )
            }
        }
    }
    RoundButton(
        modifier = Modifier
            .size(
                width = 200.dp,
                height = 65.dp
            )
            .padding(top = 20.dp),
        text = "Sign In",
        buttonColor = Color(0x975F8BFD),
        textColor = Color.White,
    ) { /* TODO */ }
}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    LoginView(
        topBarTitle = "Register",
        onClickBack = {},
        onClickMoreVert = {},
    )
}
