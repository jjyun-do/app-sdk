package com.samsung.healthcare.kit.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.libraries.healthdata.data.DataType
import com.samsung.healthcare.kit.app.AppStage.Main
import com.samsung.healthcare.kit.app.AppStage.Onboarding
import com.samsung.healthcare.kit.app.AppStage.SignUp
import com.samsung.healthcare.kit.app.setting.SettingPreference
import com.samsung.healthcare.kit.task.OnboardingTask
import com.samsung.healthcare.kit.task.SignUpTask
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// TODO replace with Activity
@Composable
fun BaseApplication(
    onboardingTask: OnboardingTask,
    singUpTask: SignUpTask,
    statusList: List<DataType>
) {
    val settingPreference = SettingPreference(LocalContext.current)
    Main(
        settingPreference,
        onboardingTask,
        singUpTask,
        statusList
    )
}

@Composable
private fun Main(
    settingPreference: SettingPreference,
    onboardingTask: OnboardingTask,
    singUpTask: SignUpTask,
    statusList: List<DataType>
) {
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    val appStage = runBlocking {
        settingPreference.appStage.first()
    }

    NavHost(navController = navController, startDestination = appStage.name) {
        composable(Main.name) { Home(statusList) }
        composable(Onboarding.name) {
            onboardingTask.callback = {
                scope.launch {
                    settingPreference.setAppStage(SignUp)
                }
                navController.navigate(SignUp.name) {
                    this.launchSingleTop = true
                }
            }
            onboardingTask.Render()
        }
        composable(SignUp.name) {
            singUpTask.callback = {
                scope.launch {
                    settingPreference.setAppStage(Main)
                }
                navController.navigate(Main.name) {
                    this.launchSingleTop = true
                }
            }
            singUpTask.Render()
        }
    }
}
