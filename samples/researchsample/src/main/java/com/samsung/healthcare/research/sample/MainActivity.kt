package com.samsung.healthcare.research.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import com.samsung.healthcare.research.step.ConsentTextStep
import com.samsung.healthcare.research.step.EligibilityStep
import com.samsung.healthcare.research.step.Step
import com.samsung.healthcare.research.task.OrderedTask
import com.samsung.healthcare.research.theme.AppColors
import com.samsung.healthcare.research.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var eligibilityStep: EligibilityStep

    @Inject
    lateinit var consentStep: ConsentTextStep

    @Inject
    lateinit var appColors: AppColors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                AppTheme(appColors) {
                    this.window.statusBarColor = AppTheme.colors.background.toArgb()
                    HealthApp(listOf(eligibilityStep, consentStep))
                }
            }
        }
    }
}

@Composable
fun HealthApp(steps: List<Step<*>>) {
    OrderedTask(
        id = "id",
        steps = steps,
    )
}
