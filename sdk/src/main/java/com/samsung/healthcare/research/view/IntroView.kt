package com.samsung.healthcare.research.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.intro.Intro
import com.samsung.healthcare.research.theme.AppTheme
import com.samsung.healthcare.research.view.common.BottomBar

@Composable
fun IntroView(
    intro: Intro,
    onComplete: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    Scaffold(
        bottomBar = {
            BottomBar(
                text = "Get Started",
            ) { onComplete() }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.TopCenter)
                                .height(200.dp),
                            painter = painterResource(id = intro.drawableId),
                            contentDescription = "",
                            contentScale = ContentScale.FillWidth
                        )
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Image(
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(100.dp)
                                    .shadow(
                                        elevation = 50.dp,
                                        shape = CircleShape,
                                        clip = false
                                    ),
                                painter = painterResource(intro.logoDrawableId),
                                contentDescription = "",
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = intro.title,
                                style = AppTheme.typography.appTitle,
                                color = Color(0xff130C00),
                            )
                        }
                    }
                }
            }

            if (intro.summaryList.isNotEmpty()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    intro.summaryList.forEach() { (icon, title) ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxHeight(),
                        ) {
                            Image(
                                modifier = Modifier.wrapContentSize(),
                                painter = painterResource(icon),
                                contentDescription = "",
                                contentScale = ContentScale.Fit
                            )

                            Text(
                                modifier = Modifier.width(80.dp),
                                text = title,
                                style = AppTheme.typography.body1,
                                color = AppTheme.colors.textPrimary,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            intro.descriptionList.forEach() { (title, description) ->
                Column(
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = title,
                        style = AppTheme.typography.subHeader2,
                        color = AppTheme.colors.textPrimary
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = description,
                        style = AppTheme.typography.body1,
                        color = AppTheme.colors.textPrimary
                    )
                }
            }
        }
    }
}
