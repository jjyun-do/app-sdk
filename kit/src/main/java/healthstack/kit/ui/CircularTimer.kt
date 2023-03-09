package healthstack.kit.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import healthstack.kit.theme.AppTheme
import kotlinx.coroutines.delay

@Composable
fun CircularTimer(
    modifier: Modifier = Modifier.size(280.dp),
    color: Color = AppTheme.colors.primary,
    strokeWidth: Dp = 25.dp,
    fontSize: TextUnit = 48.sp,
    timeSeconds: Int = 10,
) {
    val leftTimeSeconds = remember { mutableStateOf(timeSeconds) }
    LaunchedEffect(timeSeconds > 0) {
        while (leftTimeSeconds.value > 0) {
            delay(1000)
            --leftTimeSeconds.value
        }
    }

    val progress = remember { Animatable(1f) }
    LaunchedEffect(Unit) {
        progress.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = timeSeconds * 1000,
                easing = LinearEasing
            )
        )
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize().align(Alignment.Center),
            color = AppTheme.colors.secondaryVariant,
            progress = 1f,
            strokeWidth = strokeWidth,
        )

        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize().align(Alignment.Center),
            color = color,
            progress = progress.value,
            strokeWidth = strokeWidth,
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = formatTime(leftTimeSeconds.value),
                style = AppTheme.typography.title1,
                color = AppTheme.colors.textPrimaryAccent,
                textAlign = TextAlign.Center,
                fontSize = fontSize,
            )
        }
    }
}

fun formatTime(timeSeconds: Int): String {
    return String.format("%02d", (timeSeconds / 60) % 60) + ":" + String.format("%02d", timeSeconds % 60)
}
