package healthstack.app.refact

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.InsertChart
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import healthstack.app.R
import healthstack.app.pref.AppStage
import healthstack.app.pref.AppStage.Education
import healthstack.app.pref.AppStage.Home
import healthstack.app.pref.AppStage.Insights
import healthstack.kit.annotation.PreviewGenerated
import healthstack.kit.theme.AppTheme
import healthstack.kit.ui.BottomBarNavigation
import healthstack.kit.ui.BottomNavItem
import healthstack.kit.ui.TopBar

class EducationDetailLayout(
    val title: String = "Education",
    val changeNavigation: (AppStage) -> Unit,
    val onClickBack: () -> Unit,
    val Content: @Composable () -> Unit,
) {
    @Composable
    fun Render() {
        val scrollState = rememberScrollState()
        Scaffold(
            topBar = {
                TopBar(title = title) {
                    onClickBack()
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
                    2
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
                Spacer(Modifier.height(24.dp))
                Content()
            }
        }
    }
}

@Composable
private fun EducationText(title: String, body: String) {
    Column(Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = AppTheme.typography.headline3,
            modifier = Modifier.fillMaxWidth(),
            color = AppTheme.colors.onBackground
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = body,
            style = AppTheme.typography.body2,
            modifier = Modifier.fillMaxWidth(),
            color = AppTheme.colors.onBackground
        )
    }
}

@Composable
fun FirstEducation(
    changeNavigation: (AppStage) -> Unit,
    onClickBack: () -> Unit,
) =
    EducationDetailLayout(
        changeNavigation = changeNavigation,
        onClickBack = onClickBack
    ) {
        Image(
            painter = painterResource(R.drawable.video_player),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = "Device Tutorial",
            style = AppTheme.typography.body2,
            modifier = Modifier.fillMaxWidth(),
            color = AppTheme.colors.onBackground.copy(0.6F)
        )
        EducationText(
            "How to Take an ECG",
            "To take an ECG on a Galaxy Watch, first ensure it's compatible with your model." +
                " Then, wear the watch snugly and open the Samsung Health app. Select ECG from the" +
                " app's homepage, rest your arm on a flat surface, and place your index finger on" +
                " the top button of the watch. Hold still until the test is complete. The results" +
                " will display on the app, which you can share with your doctor.\n"
        )
        Spacer(Modifier.height(60.dp))
    }

@Composable
fun SecondEducation(
    changeNavigation: (AppStage) -> Unit,
    onClickBack: () -> Unit,
) =
    EducationDetailLayout(
        changeNavigation = changeNavigation,
        onClickBack = onClickBack
    ) {
        PdfViewCard(
            "Heart Health",
            "Heart Stroke Prevention",
            "View PDF",
            "/sample/file/path",
            R.drawable.pdf_preview
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = "To prevent a stroke, prioritize a healthy lifestyle. Eat a balanced diet, " +
                "stay active, avoid smoking, and limit alcohol intake. Monitor and manage high" +
                " blood pressure, cholesterol, and diabetes. Maintain a healthy weight, manage " +
                "stress levels, and prioritize good sleep. Regularly see a doctor and take any" +
                " prescribed medications as directed.",
            style = AppTheme.typography.body2,
            modifier = Modifier.fillMaxWidth(),
            color = AppTheme.colors.onBackground
        )
        Spacer(Modifier.height(60.dp))
    }

@Composable
fun ThirdEducation(
    changeNavigation: (AppStage) -> Unit,
    onClickBack: () -> Unit,
) =
    EducationDetailLayout(
        changeNavigation = changeNavigation,
        onClickBack = onClickBack
    ) {
        Image(
            painter = painterResource(R.drawable.edu_article_thum),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = "Heart Health",
            style = AppTheme.typography.body2,
            modifier = Modifier.fillMaxWidth(),
            color = AppTheme.colors.onBackground.copy(0.6F)
        )
        EducationText(
            "How can a stroke be prevented?",
            "The heart is essentially a pump that circulates blood to your entire body. " +
                "First oxygen is absorbed through the lungs. Then oxygen rich blood and nutrients " +
                "(both required as energy) are pumped throughout the body for vital functioning of " +
                "each organ."
        )
        Spacer(Modifier.height(32.dp))
        Text(
            text = "The heart functions as two connected “circuits.” After your body uses up oxygen " +
                "(required by muscles, brain and all your organs to function), blood returns to the" +
                " right atrium, which then pumps blood to fill the right ventricle (blue arrows)." +
                " This right ventricle then pumps the blood into the lungs to pick up oxygen and" +
                " then returns it to the left atrium. The left atrium then pumps the oxygen-rich blood" +
                " to fill the left ventricle (red arrows). The left ventricle then pumps (generating" +
                " a pulse and blood pressure) the oxygen-rich blood to your muscles, brain and other" +
                " organs, where the oxygen is consumed, returning oxygen-depleted blood to the right" +
                " atrium and it starts over again.",
            style = AppTheme.typography.body2,
            modifier = Modifier.fillMaxWidth(),
            color = AppTheme.colors.onBackground
        )
        Spacer(Modifier.height(32.dp))
        Image(
            painter = painterResource(R.drawable.heart_detail),
            contentDescription = null,
            modifier = Modifier.width(300.dp).wrapContentHeight(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(Modifier.height(60.dp))
    }

@Preview(showBackground = true)
@PreviewGenerated
@Composable
fun FirstEducationPreview() =
    FirstEducation({}, {}).Render()

@Preview(showBackground = true)
@PreviewGenerated
@Composable
fun ThirdEducationPreview() =
    ThirdEducation({}, {}).Render()

@Preview(showBackground = true, widthDp = 360, heightDp = 1000)
@PreviewGenerated
@Composable
fun SecondEducationPreview() =
    SecondEducation({}, {}).Render()
