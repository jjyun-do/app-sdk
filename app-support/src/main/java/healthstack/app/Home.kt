package healthstack.app

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import healthstack.app.pref.AppStage
import healthstack.app.pref.AppStage.Profile
import healthstack.app.pref.AppStage.Settings
import healthstack.app.pref.AppStage.StudyInformation
import healthstack.app.status.StatusDataType
import healthstack.app.viewmodel.TaskViewModel
import healthstack.app.viewmodel.TaskViewModel.TasksState
import healthstack.kit.task.base.Task
import healthstack.kit.theme.AppTheme
import healthstack.kit.ui.DropdownMenuItemData
import healthstack.kit.ui.TopBarWithDropDown
import healthstack.kit.ui.WeeklyCard
import java.time.LocalDate

@Composable
fun Home(
    dataTypeStatus: List<StatusDataType>,
    viewModel: TaskViewModel,
    changeNavigation: (AppStage) -> Unit,
) {
    val scrollState = rememberScrollState()
    var selectedTask by remember {
        mutableStateOf<Task?>(null)
    }

    if (selectedTask == null) {
        DailyTaskView(
            LocalDate.now(),
            dataTypeStatus,
            viewModel,
            scrollState,
            changeNavigation
        ) { selectedTask = it }
    } else {
        selectedTask?.let { task ->
            task.callback = {
                viewModel.done(task)
                selectedTask = null
            }
            task.canceled = {
                selectedTask = null
            }
            task.Render()
        }
    }
}

@Composable
private fun DailyTaskView(
    date: LocalDate,
    dataTypeStatus: List<StatusDataType>,
    viewModel: healthstack.app.viewmodel.TaskViewModel,
    scrollState: ScrollState,
    changeNavigation: (AppStage) -> Unit,
    onStartTask: (Task) -> Unit,
) {
    val firebaseAuth = FirebaseAuth.getInstance()

    Scaffold(
        backgroundColor = AppTheme.colors.background,
        topBar = {
            TopBarWithDropDown(
                "Keep it going, ${firebaseAuth.currentUser?.displayName}!",
                AppTheme.typography.headline3,
                AppTheme.colors.onSurface,
                listOf<DropdownMenuItemData>(
                    DropdownMenuItemData("Profile", Icons.Default.Person) { changeNavigation(Profile) },
                    DropdownMenuItemData("Settings", Icons.Default.Settings) { changeNavigation(Settings) },
                    DropdownMenuItemData(
                        "Study Information",
                        Icons.Default.Info
                    ) { changeNavigation(StudyInformation) },
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(10.dp))
            WeeklyCard(date)
            Spacer(Modifier.height(32.dp))

            StatusCards(dataTypeStatus, viewModel)
            Spacer(Modifier.height(40.dp))

            Tasks(
                "Upcoming Tasks",
                viewModel.activeTasks.collectAsState().value,
                { viewModel.syncTasks() }
            ) {
                onStartTask(it)
            }
            Spacer(Modifier.height(32.dp))
            Tasks(
                "Today",
                viewModel.todayTasks.collectAsState().value
            )
            Spacer(Modifier.height(32.dp))
            Tasks(
                "Completed Tasks",
                viewModel.completedTasks.collectAsState().value
            )
        }
    }
}

@Composable
fun Tasks(
    title: String,
    state: TasksState,
    onReload: () -> Unit = { },
    onStartTask: (Task) -> Unit = { },
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 4.dp)
    ) {
        Row(
            horizontalArrangement = SpaceBetween,
            verticalAlignment = CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                title,
                style = AppTheme.typography.headline3,
                color = AppTheme.colors.onSurface
            )
            IconButton(onClick = {
                onReload()
            }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    tint = AppTheme.colors.primary,
                    contentDescription = null,
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        state.tasks.forEach {
            it.CardView {
                onStartTask(it)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
