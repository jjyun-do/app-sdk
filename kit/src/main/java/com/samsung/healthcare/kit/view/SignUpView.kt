package com.samsung.healthcare.kit.view

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.kit.R
import com.samsung.healthcare.kit.common.CallbackCollection
import com.samsung.healthcare.kit.model.SignUpModel
import com.samsung.healthcare.kit.step.sub.SubStepHolder
import com.samsung.healthcare.kit.theme.AppTheme
import com.samsung.healthcare.kit.view.common.RoundButton
import com.samsung.healthcare.kit.view.common.RoundTextField
import com.samsung.healthcare.kit.view.common.TopBar

class SignUpView() : View<SignUpModel>() {
    @Composable
    override fun Render(model: SignUpModel, callbackCollection: CallbackCollection, holder: SubStepHolder?) {

        val emailState = remember { mutableStateOf("") }
        val passwordState = remember { mutableStateOf("") }
        val passwordConfirmState = remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                TopBar(title = "Register") {
                    callbackCollection.prev()
                }
            },
            bottomBar = {
                Column(
                    modifier = Modifier.wrapContentSize().padding(vertical = 20.dp)
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 20.dp),
                        border = BorderStroke(width = 1.dp, AppTheme.colors.border),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.background),
                        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
                        onClick = { /* TODO */ callbackCollection.next() }
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
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().height(60.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    model.drawableId?.let {
                        Image(
                            painter = painterResource(it),
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                    Text(
                        text = model.title,
                        style = AppTheme.typography.appTitle,
                        color = AppTheme.colors.textPrimary
                    )
                }
                Spacer(modifier = Modifier.height(25.dp))

                model.description?.let {
                    Text(
                        text = it,
                        modifier = Modifier.fillMaxWidth(),
                        style = AppTheme.typography.body1,
                        color = AppTheme.colors.textPrimary
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                RoundTextField(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    value = emailState.value,
                    onValueChange = { emailState.value = it },
                    placeholder = "Email Address",
                )

                Spacer(modifier = Modifier.height(10.dp))

                RoundTextField(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    value = passwordState.value,
                    onValueChange = { passwordState.value = it },
                    placeholder = "Password",
                    shouldMask = true,
                )

                Spacer(modifier = Modifier.height(10.dp))

                RoundTextField(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    value = passwordConfirmState.value,
                    onValueChange = { passwordConfirmState.value = it },
                    placeholder = "Confirm Password",
                    shouldMask = true,
                )

                Spacer(modifier = Modifier.height(20.dp))

                RoundButton(
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    text = "Sign Up",
                    textColor = AppTheme.colors.textPrimary,
                    onClick = { /* TODO */ callbackCollection.next() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpViewPreview() =
    SignUpView().Render(
        SignUpModel(
            "id",
            "SleepCare",
            "Thanks for joining the study! Now please create an account to keep track of your data and keep it safe.",
        ),
        CallbackCollection(),
        null
    )
