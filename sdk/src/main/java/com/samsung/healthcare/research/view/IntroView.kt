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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.R
import com.samsung.healthcare.research.theme.AppTheme
import com.samsung.healthcare.research.view.common.BottomRoundButton

@Composable
fun IntroView(
    title: String = "SleepCare",
    drawableId: Int = R.drawable.sample_image4,
    logoDrawableId: Int = R.drawable.ic_sample_icon,
    summaryList: List<Pair<Int, String>> = listOf(),
    descriptionList: List<Pair<String, String>> = listOf(
        Pair(
            "Description",
            stringResource(id = R.string.lorem_ipsum)
        ),
        Pair("Description", stringResource(id = R.string.lorem_ipsum)),
        Pair("Description", stringResource(id = R.string.lorem_ipsum))
    ),
    onComplete: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    Scaffold(
        bottomBar = {
            BottomRoundButton(
                text = "Get Started",
            ) { onComplete() }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
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
                            painter = painterResource(id = drawableId),
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
                                painter = painterResource(logoDrawableId),
                                contentDescription = "",
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = title,
                                style = AppTheme.typography.appTitle,
                                color = AppTheme.colors.textPrimary,
                            )
                        }
                    }
                }
            }

            if (summaryList.isNotEmpty()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    summaryList.forEach() { (icon, title) ->
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

            descriptionList.forEach() { (title, description) ->
                Column(modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)) {
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

@Preview(showBackground = true)
@Composable
fun IntroViewPreview() {
    IntroView(
        summaryList = listOf(
            Pair(R.drawable.ic_watch, "Wear your watch"),
            Pair(R.drawable.ic_clock, "10 min a day"),
            Pair(R.drawable.ic_alarm, "2 surveys a week")
        )
    )
}
