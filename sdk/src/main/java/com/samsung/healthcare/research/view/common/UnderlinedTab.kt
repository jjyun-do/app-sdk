package com.samsung.healthcare.research.view.common

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.view.LoginScreen

@Composable
fun UnderlinedTab(
    modifier: Modifier = Modifier,
    titles: List<String>,
    tabSelected: LoginScreen,
    onTabSelected: (LoginScreen) -> Unit,
    unselectedTextColor: Color = Color.Black,
    selectedTextColor: Color = Color.Black,
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = tabSelected.ordinal,
        backgroundColor = MaterialTheme.colors.background.copy(alpha = 0f),
        contentColor = Color.Black,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(tabPositions[tabSelected.ordinal]),
                height = 3.dp,
                color = Color.Black
            )
        }
    ) {
        titles.forEachIndexed { index, title ->
            val selected = index == tabSelected.ordinal

            var textColor = unselectedTextColor
            if (selected) {
                textColor = selectedTextColor
            }

            Tab(
                selected = selected,
                onClick = { onTabSelected(LoginScreen.values()[index]) },
                text = {
                    Text(
                        text = title,
                        color = textColor,
                    )
                }
            )
        }
    }
}

fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition
): Modifier = composed() {
    val indicatorWidth = 130.dp
    val currentTabWidth = currentTabPosition.width
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left + currentTabWidth / 2 - indicatorWidth / 2,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
    )

    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(indicatorWidth)
}

@Preview(showBackground = true)
@Composable
fun LoginTabsPreview() {
    var tabIndex by remember { mutableStateOf(LoginScreen.SignUp) }
    UnderlinedTab(
        modifier = Modifier,
        titles = listOf("Sign Up", "Sign In"),
        tabSelected = tabIndex,
        onTabSelected = { index -> tabIndex = index }
    )
}
