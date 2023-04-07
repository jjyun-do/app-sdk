package healthstack.app.refact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.InsertChart
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import healthstack.app.pref.AppStage
import healthstack.app.pref.AppStage.Education
import healthstack.app.pref.AppStage.Home
import healthstack.app.pref.AppStage.Insights
import healthstack.app.refact.HealthInsightType.BLOOD_PRESSURE
import healthstack.app.refact.HealthInsightType.HEART_RATE
import healthstack.app.refact.HealthInsightType.RESPIRATORY_RATE
import healthstack.app.refact.HealthInsightType.SLEEP
import healthstack.app.refact.HealthInsightType.STEP_COUNT
import healthstack.app.refact.HealthInsightType.STRESS_LEVEL
import healthstack.kit.annotation.PreviewGenerated
import healthstack.kit.theme.AppTheme
import healthstack.kit.ui.BottomBarNavigation
import healthstack.kit.ui.BottomNavItem
import healthstack.kit.ui.HorizontalTab
import healthstack.kit.ui.TabContent
import healthstack.kit.ui.TopBar

class HealthDetailView(
    val type: HealthInsightType,
    val changeNavigation: (AppStage) -> Unit,
    val onClickBack: () -> Unit,
) {
    @Composable
    fun Render() {
        val scrollState = rememberScrollState()

        Scaffold(
            topBar = {
                TopBar(title = type.title) {
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
                HorizontalTab(
                    listOf(
                        TabContent("Today", type.today),
                        TabContent("Week", type.week),
                        TabContent("All", type.all)
                    )
                )
            }
        }
    }
}

@PreviewGenerated
@Preview(showBackground = true)
@Composable
fun HealthDetailPreview() =
    HealthDetailView(
        STEP_COUNT, { }
    ) { }.Render()

@PreviewGenerated
@Preview(showBackground = true)
@Composable
fun HealthDetailPreview2() =
    HealthDetailView(
        BLOOD_PRESSURE, { }
    ) { }.Render()

@PreviewGenerated
@Preview(showBackground = true)
@Composable
fun HealthDetailPreview3() =
    HealthDetailView(
        HEART_RATE, { }
    ) { }.Render()

@PreviewGenerated
@Preview(showBackground = true)
@Composable
fun HealthDetailPreview4() =
    HealthDetailView(
        RESPIRATORY_RATE, { }
    ) { }.Render()

@PreviewGenerated
@Preview(showBackground = true)
@Composable
fun HealthDetailPreview5() =
    HealthDetailView(
        SLEEP, { }
    ) { }.Render()

@PreviewGenerated
@Preview(showBackground = true)
@Composable
fun HealthDetailPreview6() =
    HealthDetailView(
        STRESS_LEVEL, { }
    ) { }.Render()
