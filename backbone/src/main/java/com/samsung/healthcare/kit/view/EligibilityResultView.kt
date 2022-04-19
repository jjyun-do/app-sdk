package com.samsung.healthcare.kit.view

import androidx.compose.runtime.Composable
import com.samsung.healthcare.kit.common.CallbackCollection
import com.samsung.healthcare.kit.model.EligibilityResultModel
import com.samsung.healthcare.kit.model.ImageArticleModel
import com.samsung.healthcare.kit.step.sub.SubStepHolder
import com.samsung.healthcare.kit.view.layout.ImageArticleLayout

class EligibilityResultView() : View<EligibilityResultModel>() {

    @Composable
    override fun Render(
        model: EligibilityResultModel,
        callbackCollection: CallbackCollection,
        holder: SubStepHolder?,
    ) {
        var resultModel: ImageArticleModel = when {
            callbackCollection.getEligibility() -> model.successModel
            else -> model.failModel
        }
        ImageArticleLayout(
            model.title,
            resultModel,
            "Continue",
            onClickBack = { callbackCollection.prev() },
            onComplete = { callbackCollection.next() }
        )
    }
}
