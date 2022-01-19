package com.samsung.healthcare.research.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsung.healthcare.research.R
import com.samsung.healthcare.research.consent.ConsentDocument
import com.samsung.healthcare.research.consent.ConsentSection

@Composable
fun ConsentDocumentStepView(consentDocument: ConsentDocument) {
    var index by remember { mutableStateOf(0) }

    // TODO handle review / signature process
    ConsentSectionView(consentDocument.getSection(index)) {
        index += 1
    }
}

@Composable
private fun ConsentSectionView(
    consentSection: ConsentSection,
    onNextPressed: () -> Unit
) {
    var isDetail by remember { mutableStateOf(false) }
    if (isDetail) {
        ConsentSectionDetailView(consentSection) {
            isDetail = false
        }
    } else {
        ConsentSectionSummaryView(consentSection, onNextPressed) {
            isDetail = true
        }
    }
}

@Composable
fun ConsentSectionDetailView(consentSection: ConsentSection, onBackPressed: () -> Unit) {
    BackHandler {
        onBackPressed()
    }
    Scaffold(
        content = {
            Column {
                Text(
                    text = consentSection.title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = consentSection.content,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    )
}

@Composable
private fun ConsentSectionSummaryView(
    consentSection: ConsentSection,
    onNextPressed: () -> Unit,
    onLearnMorePressed: () -> Unit
) {
    Scaffold(
        content = {
            Column {
                Image(
                    painter = painterResource(consentSection.getImageResourceId()),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = consentSection.title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = consentSection.summary,
                    modifier = Modifier.padding(start = 10.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
                ClickableText(
                    modifier = Modifier.align(CenterHorizontally),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Blue,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic
                            )
                        ) {
                            append("Learn more")
                        }
                    },
                    onClick = {
                        onLearnMorePressed()
                    }
                )
            }
        },
        bottomBar = {
            NextBottomBar {
                onNextPressed()
            }
        }
    )
}

private fun ConsentSection.getImageResourceId(): Int =
    when (this.type) {
        ConsentSection.Type.OVERVIEW -> R.drawable.ic_consent_section_overview
        ConsentSection.Type.DATA_GATHERING -> R.drawable.ic_consent_section_data_gathering
        ConsentSection.Type.PRIVACY -> R.drawable.ic_consent_section_privacy
        ConsentSection.Type.DATA_USE -> R.drawable.ic_consent_section_data_use
        ConsentSection.Type.TIME_COMMITMENT -> R.drawable.ic_consent_section_time_commitment
        ConsentSection.Type.STUDY_TASKS -> R.drawable.ic_consent_section_study_tasks
        ConsentSection.Type.STUDY_SURVEY -> R.drawable.ic_consent_section_study_survey
        ConsentSection.Type.WITHDRAWING -> R.drawable.ic_consent_section_withdrawing
    }
