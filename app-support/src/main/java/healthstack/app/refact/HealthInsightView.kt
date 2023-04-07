package healthstack.app.refact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.InsertChart
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import healthstack.kit.theme.AppTheme
import healthstack.kit.ui.BottomBarNavigation
import healthstack.kit.ui.BottomNavItem
import healthstack.kit.ui.InsightCard
import healthstack.kit.ui.InsightUnit
import java.time.LocalDateTime

class HealthInsightView(
    val title: String = "Health Insights",
    val changeNavigation: (AppStage) -> Unit,
) {

    @Composable
    fun Render() {
        var selectedHealth by remember {
            mutableStateOf<HealthInsightType?>(null)
        }

        Scaffold(
            drawerElevation = 0.dp,
            topBar = {
                if (selectedHealth == null)
                    TopAppBar(
                        title = {
                            Text(
                                text = title,
                                style = AppTheme.typography.headline3,
                                color = AppTheme.colors.onBackground,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                        },
                        backgroundColor = AppTheme.colors.background,
                        elevation = 0.dp
                    )
            },
            bottomBar = {
                if (selectedHealth == null)
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
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = AppTheme.colors.background
        ) {
            if (selectedHealth == null) {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(scrollState)
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(Modifier.padding(6.dp))
                    InsightCard("Exercise", LocalDateTime.now().minusHours(1L))
                    Spacer(Modifier.height(24.dp))
                    InsightCard(
                        "Step Count", LocalDateTime.now().minusHours(1L), listOf(InsightUnit("1325", "steps"))
                    ) { selectedHealth = STEP_COUNT }
                    Spacer(Modifier.height(24.dp))
                    InsightCard(
                        "Blood Pressure", LocalDateTime.now().minusHours(1L), listOf(InsightUnit("120", "mmHg"))
                    ) { selectedHealth = BLOOD_PRESSURE }
                    Spacer(Modifier.height(24.dp))
                    InsightCard(
                        "Heart Rate", LocalDateTime.now().minusHours(1L), listOf(InsightUnit("78", "bpm"))
                    ) { selectedHealth = HEART_RATE }
                    Spacer(Modifier.height(24.dp))
                    InsightCard(
                        "Respiratory Rate", LocalDateTime.now().minusHours(1L), listOf(InsightUnit("14.5", "bpm"))
                    ) { selectedHealth = RESPIRATORY_RATE }
                    Spacer(Modifier.height(24.dp))
                    InsightCard(
                        "Sleep", LocalDateTime.now().minusHours(1L),
                        listOf(
                            InsightUnit("7", "hours"),
                            InsightUnit("24", "minutes")
                        )
                    ) { selectedHealth = SLEEP }
                    Spacer(Modifier.height(24.dp))
                    InsightCard(
                        "Stress Level", LocalDateTime.now().minusHours(1L),
                        listOf(
                            InsightUnit(value = "High", color = AppTheme.colors.errorVariant)
                        )
                    ) { selectedHealth = STRESS_LEVEL }
                    Spacer(Modifier.height(65.dp))
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    selectedHealth?.let {
                        HealthDetailView(it, changeNavigation) {
                            selectedHealth = null
                        }.Render()
                    }
                }
            }
        }
    }
}
