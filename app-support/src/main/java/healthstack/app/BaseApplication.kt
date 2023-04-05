package healthstack.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import healthstack.app.pref.AppStage
import healthstack.app.pref.AppStage.Home
import healthstack.app.pref.AppStage.Onboarding
import healthstack.app.pref.AppStage.SignUp
import healthstack.app.pref.SettingPreference
import healthstack.app.status.StatusDataType
import healthstack.app.sync.SyncManager
import healthstack.app.task.repository.TaskRepository
import healthstack.app.task.repository.TaskRepositoryImpl
import healthstack.kit.info.MyProfileView
import healthstack.kit.info.SettingsView
import healthstack.kit.info.StudyInfoView
import healthstack.kit.task.onboarding.OnboardingTask
import healthstack.kit.task.signup.SignUpTask
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun BaseApplication(
    onboardingTask: OnboardingTask,
    singUpTask: SignUpTask,
    statusList: List<StatusDataType>,
    healthDataSyncSpecs: List<SyncManager.HealthDataSyncSpec>,
) {
    val settingPreference = SettingPreference(LocalContext.current)
    Main(
        settingPreference,
        onboardingTask,
        singUpTask,
        statusList,
        healthDataSyncSpecs
    )
}

@Composable
private fun Main(
    settingPreference: SettingPreference,
    onboardingTask: OnboardingTask,
    singUpTask: SignUpTask,
    statusList: List<StatusDataType>,
    healthDataSyncSpecs: List<SyncManager.HealthDataSyncSpec>,
) {
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    val initialAppStage = runBlocking {
        settingPreference.appStage.first()
    }

    val roomDB: TaskRepository = TaskRepositoryImpl()
    val viewModel = healthstack.app.viewmodel.TaskViewModel(roomDB, settingPreference)

    val changeNavigation = { stage: AppStage ->
        scope.launch {
            settingPreference.setAppStage(stage)
        }
        navController.navigate(stage.name) {
            this.launchSingleTop = true
            popUpTo(0)
        }
    }

    NavHost(navController = navController, startDestination = initialAppStage.name) {
        composable(Home.name) {
            Home(statusList, viewModel, changeNavigation)
        }
        composable(AppStage.Profile.name) {
            MyProfileView(home = { changeNavigation(Home) }).Render()
        }
        composable(AppStage.StudyInformation.name) {
            StudyInfoView(home = { changeNavigation(Home) }).Render()
        }
        composable(AppStage.Settings.name) {
            SettingsView(home = { changeNavigation(Home) }, initialize = { changeNavigation(Onboarding) }).Render()
        }
        composable(Onboarding.name) {
            onboardingTask.callback = { changeNavigation(SignUp) }
            onboardingTask.Render()
        }
        composable(SignUp.name) {
            SyncManager.initialize(LocalContext.current, healthDataSyncSpecs)
            singUpTask.callback = {
                scope.launch {
                    SyncManager.getInstance().startBackgroundSync()
                }
                changeNavigation(Home)
            }
            singUpTask.Render()
        }
    }
}
