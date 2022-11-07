package healthstack.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.toArgb
import com.google.android.libraries.healthdata.HealthDataService
import dagger.hilt.android.AndroidEntryPoint
import healthstack.app.BaseApplication
import healthstack.app.status.HeartRateStatus
import healthstack.app.status.SleepSessionStatus
import healthstack.app.status.TaskStatus
import healthstack.app.sync.SyncManager
import healthstack.app.task.db.TaskRoomDatabase
import healthstack.backend.integration.BackendFacadeHolder
import healthstack.backend.integration.adapter.HealthStackBackendAdapter
import healthstack.healthdata.link.HealthDataLinkHolder
import healthstack.healthdata.link.healthplatform.HealthPlatformAdapter
import healthstack.kit.task.onboarding.OnboardingTask
import healthstack.kit.task.signup.SignUpTask
import healthstack.kit.theme.AppColors
import healthstack.kit.theme.AppTheme
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appColors: AppColors

    @Inject
    lateinit var onboardingTask: OnboardingTask

    @Inject
    lateinit var signUpTask: SignUpTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val healthDataRequired = listOf("HeartRate", "Steps", "SleepSession")
        val healthDataToDisplay = listOf(HeartRateStatus, SleepSessionStatus, TaskStatus)
        val healthDataSyncSpecs = listOf(
            SyncManager.HealthDataSyncSpec("HeartRate", 15, TimeUnit.MINUTES),
            SyncManager.HealthDataSyncSpec("Steps", 1, TimeUnit.DAYS),
            SyncManager.HealthDataSyncSpec("SleepSession", 1, TimeUnit.DAYS)
        )

        HealthDataLinkHolder.initialize(
            HealthPlatformAdapter(HealthDataService.getClient(this), healthDataRequired)
        )

        BackendFacadeHolder.initialize(
            HealthStackBackendAdapter.initialize(
                this.getString(R.string.research_platform_endpoint),
                this.getString(R.string.research_project_id)
            ).let { HealthStackBackendAdapter.getInstance() }
        )

        TaskRoomDatabase.initialize(this)

        setContent {
            Surface {
                AppTheme(appColors) {
                    this.window.statusBarColor = AppTheme.colors.primary.toArgb()
                    BaseApplication(
                        onboardingTask,
                        signUpTask,
                        healthDataToDisplay,
                        healthDataSyncSpecs
                    )
                }
            }
        }
    }
}
