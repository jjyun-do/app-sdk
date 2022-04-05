package com.samsung.healthcare.research.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.research.consent.ConsentDocument
import com.samsung.healthcare.research.consent.ConsentDocument.ConsentResult
import com.samsung.healthcare.research.view.ConsentDocumentStepView

class ConsentDocumentStep(
    id: String,
    private val consentDocument: ConsentDocument,
    onComplete: (ConsentResult) -> Unit
) : Step<ConsentResult>(id, onComplete) {

    override val stepView: @Composable (StepChangedListener) -> Unit = {
        ConsentDocumentStepView(consentDocument)
    }

    override fun getResult(): ConsentResult =
        ConsentResult.Agree
}
