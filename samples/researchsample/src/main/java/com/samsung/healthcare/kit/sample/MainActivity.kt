package com.samsung.healthcare.kit.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.toArgb
import com.google.android.libraries.healthdata.data.IntervalDataTypes.SLEEP_SESSION
import com.google.android.libraries.healthdata.data.SampleDataTypes.HEART_RATE
import com.samsung.healthcare.kit.app.BaseApplication
import com.samsung.healthcare.kit.app.TaskDataType.Companion.TASK_DATA_TYPE
import com.samsung.healthcare.kit.task.OnboardingTask
import com.samsung.healthcare.kit.task.SignUpTask
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

    @Inject
    lateinit var signUpTask: SignUpTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                AppTheme(appColors) {
                    this.window.statusBarColor = AppTheme.colors.background.toArgb()
                    BaseApplication(
                        onboardingTask,
                        signUpTask,
                        listOf(
                            HEART_RATE,
                            SLEEP_SESSION,
                            TASK_DATA_TYPE
                        )
                    )
                }
            }
        }
    }
}
