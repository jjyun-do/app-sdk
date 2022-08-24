package com.samsung.healthcare.kit.view.auth

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.kit.R.drawable
import com.samsung.healthcare.kit.theme.AppTheme

@Composable
fun GoogleSignInButton(
    onClick: () -> Unit,
) {
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
            border = BorderStroke(width = 1.dp, AppTheme.colors.border),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.background),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            onClick = onClick
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp),
                    painter = painterResource(drawable.ic_google__g__logo),
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

@Preview(showBackground = true)
@Composable
fun GoogleSignInButtonPreview() {
    GoogleSignInButton { }
}
