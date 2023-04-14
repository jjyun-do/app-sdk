package healthstack.app.refact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.InsertChart
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import healthstack.app.pref.AppStage
import healthstack.app.pref.AppStage.Education
import healthstack.app.pref.AppStage.Home
import healthstack.app.pref.AppStage.Insights
import healthstack.kit.annotation.PreviewGenerated
import healthstack.kit.theme.AppTheme
import healthstack.kit.ui.BottomBarNavigation
import healthstack.kit.ui.BottomNavItem
import healthstack.kit.ui.TopBar
import healthstack.kit.ui.WeeklyCard
import java.time.LocalDate

class ExerciseView(
    val title: String = "Exercise",
    val changeNavigation: (AppStage) -> Unit,
    val onClickBack: () -> Unit,
) {
    @Composable
    fun Render() {
        val scrollState = rememberScrollState()

        Scaffold(
            topBar = {
                TopBar(title = title) {
                    onClickBack()
                    changeNavigation(Insights)
                }
            },
            bottomBar = {
                BottomBarNavigation(
                    listOf(
                        BottomNavItem("Home", Icons.Default.Home) {
                            changeNavigation(Home)
                        },
                        BottomNavItem("Insights", Icons.Default.InsertChart) {
                            changeNavigation(Insights)
                        },
                        BottomNavItem("Education", Icons.Default.MenuBook) {
                            changeNavigation(Education)
                        }
                    ),
                    1
                )
            },
            backgroundColor = AppTheme.colors.background
        ) {
            Column(
                Modifier.fillMaxWidth()
                    .fillMaxWidth()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(16.dp))
                WeeklyCard(LocalDate.now())
                Spacer(Modifier.height(16.dp))
                Text(
                    style = AppTheme.typography.title2,
                    color = AppTheme.colors.onBackground,
                    text = "This week",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    style = AppTheme.typography.headline3,
                    color = AppTheme.colors.onBackground,
                    text = "03:26:10",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    Modifier.fillMaxWidth().height(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        style = AppTheme.typography.body3,
                        color = AppTheme.colors.onBackground,
                        text = "- cal",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.wrapContentWidth().padding(end = 4.dp)
                    )
                    Divider(
                        color = AppTheme.colors.onBackground.copy(0.12F),
                        modifier = Modifier.fillMaxHeight().width(1.dp)
                    )
                    Text(
                        style = AppTheme.typography.body3,
                        color = AppTheme.colors.onBackground,
                        text = "- sessions",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.wrapContentWidth().padding(start = 4.dp)
                    )
                }

                Spacer(Modifier.height(42.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        style = AppTheme.typography.title2,
                        color = AppTheme.colors.onBackground,
                        text = "Today",
                        modifier = Modifier.wrapContentWidth()
                    )
                    Text(
                        style = AppTheme.typography.body2,
                        color = AppTheme.colors.onBackground,
                        text = "00:00:00",
                        modifier = Modifier.wrapContentWidth()
                    )
                }
                Spacer(Modifier.height(24.dp))
                ExerciseCard(
                    "Walking (auto)",
                    listOf("00:00:00", "28 cal", "0.49 mi", "11:08 PM")
                )
                Spacer(Modifier.height(18.dp))
                ExerciseCard(
                    "Circuit training",
                    listOf("00:00:00", "- cal", "00:00 PM")
                )
            }
        }
    }
}

@PreviewGenerated
@Preview(showBackground = true)
@Composable
fun ExercisePreview() =
    ExerciseView(
        changeNavigation = { }
    ) { }.Render()
