package com.samsung.healthcare.kit.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.kit.common.CallbackCollection
import com.samsung.healthcare.kit.model.EligibilityCheckerModel
import com.samsung.healthcare.kit.step.sub.SubStepHolder
import com.samsung.healthcare.kit.view.common.BottomBar
import com.samsung.healthcare.kit.view.common.BottomRoundButton
import com.samsung.healthcare.kit.view.common.TopBar

class EligibilityCheckerView(
    private val pageable: Boolean = true,
) : View<EligibilityCheckerModel>() {

    @Composable
    override fun Render(
        model: EligibilityCheckerModel,
        callbackCollection: CallbackCollection,
        subStepHolder: SubStepHolder?,
    ) {
        // TODO: page 관련 설정을 Holder로 변경 예정
        if (pageable)
            MultiPageSurveyLayout(model, callbackCollection, subStepHolder)
        else
            SinglePageSurveyLayout(model, callbackCollection, subStepHolder)
    }
}

@Composable
fun MultiPageSurveyLayout(
    model: EligibilityCheckerModel,
    callbackCollection: CallbackCollection,
    subStepHolder: SubStepHolder?,
) {
    var index by remember { mutableStateOf(0) }
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopBar(title = model.title) {
                callbackCollection.prev()
            }
        },
        bottomBar = {
            // TODO: null 처리..
            BottomBar(
                leftButtonText = "Previous",
                rightButtonText = if (index == subStepHolder!!.size - 1) "Complete" else "Next",
                onClickLeftButton = { index -= 1 },
                onClickRightButton = {
                    if (index == subStepHolder!!.size - 1) {
                        when (subStepHolder.isSufficient()) {
                            true -> callbackCollection.setEligibility(true)
                            else -> callbackCollection.setEligibility(false)
                        }
                        callbackCollection.next()
                        return@BottomBar
                    }
                    index += 1
                },
                leftButtonEnabled = index != 0
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState)
        ) {
            SurveyProgressView(
                "${index + 1}/${subStepHolder!!.size}",
                (index + 1) / subStepHolder!!.size.toFloat()
            )
            Spacer(modifier = Modifier.height(64.dp))
            subStepHolder.subSteps[index].Render(callbackCollection)
        }
    }
}

@Composable
fun SurveyProgressView(progressText: String, progress: Float) {
    Column {
        Text(progressText)
        Spacer(modifier = Modifier.height(15.dp))
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth(1f)
        )
    }
}

@Composable
fun SinglePageSurveyLayout(
    model: EligibilityCheckerModel,
    callbackCollection: CallbackCollection,
    subStepHolder: SubStepHolder?,
) {
    val scrollSate = rememberScrollState()
    Scaffold(
        // TODO should receive callback function(back, more vert) and apply it.
        topBar = {
            TopBar(title = model.title) {
                callbackCollection.prev()
            }
        },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollSate)
        ) {
            Spacer(modifier = Modifier.height(28.dp))
            subStepHolder?.subSteps?.forEachIndexed { _, questionSubStep ->
                questionSubStep.Render(callbackCollection)
                Spacer(modifier = Modifier.height(48.dp))
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            BottomRoundButton(text = "Submit") {
                callbackCollection.next()
            }
        }
    }
}