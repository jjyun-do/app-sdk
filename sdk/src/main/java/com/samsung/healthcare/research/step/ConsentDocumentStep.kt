package com.samsung.healthcare.research.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.research.consent.ConsentDocument
import com.samsung.healthcare.research.view.ConsentDocumentStepView

class ConsentDocumentStep(
    id: String,
    private val consentDocument: ConsentDocument
) : Step(id) {

    override val composable: @Composable () -> Unit = {
        ConsentDocumentStepView(consentDocument)
    }
}
