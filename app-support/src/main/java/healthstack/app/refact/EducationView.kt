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
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import healthstack.app.R
import healthstack.app.pref.AppStage
import healthstack.app.pref.AppStage.Education
import healthstack.app.pref.AppStage.Home
import healthstack.app.pref.AppStage.Insights
import healthstack.kit.theme.AppTheme
import healthstack.kit.ui.BottomBarNavigation
import healthstack.kit.ui.BottomNavItem

class EducationView(
    val title: String = "Education",
    val changeNavigation: (AppStage) -> Unit,
) {

    @Composable
    fun Render() {
        var selectedArticle by remember {
            mutableStateOf<Int?>(null)
        }

        Scaffold(
            drawerElevation = 0.dp,
            topBar = {
                if (selectedArticle == null)
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
                if (selectedArticle == null)
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
                        2
                    )
            },
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = AppTheme.colors.background
        ) {
            if (selectedArticle == null) {
                val scrollState = rememberScrollState()

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(scrollState)
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(Modifier.height(24.dp))
                    EducationCard(
                        "Heart Health",
                        "How to Take an ECG",
                        "5:35 min",
                        R.drawable.thum_1,
                    ) {
                        selectedArticle = 1
                    }
                    Spacer(Modifier.height(8.dp))
                    EducationCard(
                        "Heart Health",
                        "Heart Stroke Prevention",
                        "10 min read",
                        R.drawable.thum_2,
                    ) {
                        selectedArticle = 2
                    }
                    Spacer(Modifier.height(8.dp))
                    EducationCard(
                        "Heart Health",
                        "How to Prevent a Heart Stroke? Expert Tips and Tricks",
                        "10 min read",
                        R.drawable.thum_3,
                    ) {
                        selectedArticle = 3
                    }
                    Spacer(Modifier.height(8.dp))
                    EducationCardWithIcon(
                        "Heart Health",
                        "Heart Stroke Prevention",
                        "10 min read",
                        Icons.Default.PictureAsPdf
                    )
                    Spacer(Modifier.height(8.dp))
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    selectedArticle?.let {
                        when (it) {
                            1 -> {
                                FirstEducation(changeNavigation) {
                                    selectedArticle = null
                                }.Render()
                            }

                            2 -> {
                                SecondEducation(changeNavigation) {
                                    selectedArticle = null
                                }.Render()
                            }

                            3 -> {
                                ThirdEducation(changeNavigation) {
                                    selectedArticle = null
                                }.Render()
                            }
                        }
                    }
                }
            }
        }
    }
}
