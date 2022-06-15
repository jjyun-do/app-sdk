package com.samsung.healthcare.kit.sample.registration

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.kit.common.CallbackCollection
import com.samsung.healthcare.kit.model.question.QuestionModel
import com.samsung.healthcare.kit.step.sub.SubStepHolder
import com.samsung.healthcare.kit.view.View
import com.samsung.healthcare.kit.view.common.BottomRoundButton
import com.samsung.healthcare.kit.view.common.TopBar
import com.samsung.healthcare.kit.view.component.Component
import com.samsung.healthcare.kit.view.component.QuestionComponent.Companion.defaultComponentOf

class RegistrationView : View<RegistrationModel>() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    override fun Render(
        model: RegistrationModel,
        callbackCollection: CallbackCollection,
        holder: SubStepHolder?
    ) {
        val scrollSate = rememberScrollState()
        Scaffold(
            topBar = {
                TopBar(title = model.title)
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

                model.questions.forEach {
                    (defaultComponentOf(it.type) as Component<QuestionModel<*>>).Render(
                        model = it,
                        callbackCollection = callbackCollection
                    )
                    Spacer(modifier = Modifier.height(48.dp))
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                BottomRoundButton(text = "Submit") {
                    // TODO api call
                    callbackCollection.next()
                }
            }
        }
    }
}
