package com.samsung.healthcare.research.view.eligibility

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.R
import com.samsung.healthcare.research.R.string
import com.samsung.healthcare.research.eligibility.Eligibility
import com.samsung.healthcare.research.eligibility.EligibilityChecker
import com.samsung.healthcare.research.eligibility.EligibilityResult
import com.samsung.healthcare.research.eligibility.EligibilityResultMessage
import com.samsung.healthcare.research.eligibility.EligibilitySection
import com.samsung.healthcare.research.step.QuestionStep
import com.samsung.healthcare.research.survey.ChoiceQuestion
import com.samsung.healthcare.research.theme.AppTheme
import com.samsung.healthcare.research.theme.lightColors
import com.samsung.healthcare.research.view.common.BottomRoundButton
import com.samsung.healthcare.research.view.common.TopBar
import com.samsung.healthcare.research.view.eligibility.EligibilityState.Check
import com.samsung.healthcare.research.view.eligibility.EligibilityState.Overview
import com.samsung.healthcare.research.view.eligibility.EligibilityState.Result
import com.samsung.healthcare.research.view.layout.SurveyStepLayout

@Composable
fun EligibilityScreen(
    eligibility: Eligibility,
    onComplete: () -> Unit
) {
    var state by remember { mutableStateOf(Overview) }

    when (state) {
        Overview -> {
            EligibilityOverviewScreen(eligibility) {
                state = state.next()
            }
        }
        Check -> {
            SurveyStepLayout(
                title = eligibility.title,
                questionSteps = createQuestionStep(eligibility),
                pageable = false,
                onClickBack = { state = state.prev() },
                onCompleted = { state = state.next() }
            )
        }
        Result -> {
            EligibilityResultScreen(
                eligibility.title,
                eligibility.eligibilityResultMessage(),
                eligibility.isEligibility(),
                onClickBack = { state = state.prev() },
                onComplete = { onComplete() }
            )
        }
    }
}

@Composable
private fun createQuestionStep(eligibility: Eligibility) =
    eligibility.questions.map { QuestionStep(it) }

@Composable
private fun EligibilityOverviewScreen(
    eligibility: Eligibility,
    onStart: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(title = eligibility.title) { }
        },
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            eligibility.drawableId?.let { drawableId ->
                Image(
                    painter = painterResource(drawableId),
                    contentDescription = "Eligibility Image",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }

            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    stringResource(string.lorem_ipsum),
                    color = AppTheme.colors.textPrimary
                )
                Spacer(modifier = Modifier.height(12.dp))
                EligibilitySectionForm(eligibility)
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            BottomRoundButton(text = "Check Eligibility") {
                onStart()
            }
        }
    }
}

@Composable
private fun EligibilitySectionForm(eligibility: Eligibility) {
    eligibility.sections.forEach { (sectionTitle, constraints) ->
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            sectionTitle,
            style = AppTheme.typography.title2,
            fontWeight = FontWeight.SemiBold,
            color = AppTheme.colors.textPrimaryAccent
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            constraints.forEach { constraint ->
                // TODO use list dot icon/image
                Text(
                    "- $constraint",
                    color = AppTheme.colors.textPrimary
                )
            }
        }
    }
}

private enum class EligibilityState {
    Overview, Check, Result;

    fun hasNext(): Boolean = this != Result

    fun hasPrevious(): Boolean = this != Overview

    fun prev(): EligibilityState = values()[this.ordinal - 1]

    fun next(): EligibilityState = values()[this.ordinal + 1]
}

@Preview(showBackground = true)
@Composable
fun EligibilityScreenPreview() {
    val eligibility = Eligibility(
        sections = listOf(
            EligibilitySection(
                "Medical eligibility",
                listOf("Condition(s)", "Prescription(s)", "Living in Europe")
            ),
            EligibilitySection(
                "Basic Profile",
                listOf("18 and over", "Living in Europe")
            ),
        ),
        drawableId = R.drawable.sample_image1,
        eligibilityChecker = EligibilityChecker(
            questions = listOf(
                ChoiceQuestion(
                    "1",
                    "Do you have any existing cardiac conditions?",
                    "Examples of cardiac conditions include abnormal heart rhythms, or arrhythmias",
                    listOf("Yes", "No")
                ),
                ChoiceQuestion(
                    "2",
                    "Do you currently own a wearable device?",
                    "Examples of wearable devices include Samsung Galaxy Watch 4, Fitbit, OuraRing, etc.",
                    listOf("Yes", "No")
                ),
            ),
            predicate = { questions ->
                questions.all { q ->
                    (q as ChoiceQuestion).selection == 0
                }
            },
            eligibilityResult = EligibilityResult(
                "Eligibility result",
                successMessage = EligibilityResultMessage(
                    "Great! You’re in!",
                    "Congratulations! You are eligible for the study.",
                    drawableId = R.drawable.sample_image2
                ),
                failMessage = EligibilityResultMessage(
                    "You are not eligible for the study.",
                    "Check back later and stay tuned for more studies coming soon!",
                    drawableId = R.drawable.sample_image3
                ),
            )
        )
    )
    AppTheme(lightColors()) {
        EligibilityScreen(eligibility) { }
    }
}
