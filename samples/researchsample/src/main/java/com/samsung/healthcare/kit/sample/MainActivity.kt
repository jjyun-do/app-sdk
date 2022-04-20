package com.samsung.healthcare.kit.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import com.samsung.healthcare.kit.task.OnboardingTask
import com.samsung.healthcare.kit.theme.AppColors
import com.samsung.healthcare.kit.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var onboardingTask: OnboardingTask

    @Inject
    lateinit var appColors: AppColors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                AppTheme(appColors) {
                    this.window.statusBarColor = AppTheme.colors.background.toArgb()
                    HealthApp(onboardingTask)
                }
            }
        }
    }
}

@Composable
fun HealthApp(
    onboardingTask: OnboardingTask,
) {
    onboardingTask.Render()
}
